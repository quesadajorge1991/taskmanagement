package taskmanagement.taskmanagement.controllers.Category;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import taskmanagement.taskmanagement.entity.Category;
import taskmanagement.taskmanagement.entity.Task;
import taskmanagement.taskmanagement.service.Category.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/categories")
	public String tasks(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "/category/categories";
	}

	/* devuelve un componente en un select con las categorias */
	@GetMapping(value = "/getCategories")
	public String getUsers(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		new Task(null, null, null, null, null);
		return "templateBase/Components/category/comboCategory :: categories";

	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "/category/add";
	}

	@GetMapping(value = "/update/{categoryId}")
	public String add(Model model, @PathVariable("categoryId") int id) {
		model.addAttribute("category", categoryService.findById(id));
		return "/category/update";
	}

	@PostMapping(value = "/addCategory")
	public @ResponseBody String addCategory(@RequestBody Category categoryName) {
		JSONObject jsono = new JSONObject();

		try {
			if (categoryName.getCategoryId() == 0) {
				categoryService.save(new Category(categoryName.getCategoryName()));
				jsono.put("msgtype", "success");
				jsono.put("msgtitle", "Información");
				jsono.put("msgbody", "Se insertó correctamente la categoria " + categoryName.getCategoryName());
			} else {
				categoryService.save(new Category(categoryName.getCategoryId(), categoryName.getCategoryName()));
				jsono.put("msgtype", "success");
				jsono.put("msgtitle", "Información");
				jsono.put("msgbody", "Se modificó correctamente la categoria " + categoryName.getCategoryName());
			}

		} catch (Exception e) {
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "Error al insertar la categoria " + categoryName.getCategoryName());
		}
		return jsono.toString();
	}

	@GetMapping(value = "/delete/{categoryId}")
	public String delete(@PathVariable(value = "categoryId") int categoryId, RedirectAttributes redirectAttributes) {
		try {
			categoryService.delete(categoryService.findById(categoryId));
			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Categoría eleminada correctamente ");
			return "redirect:/category/categories";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody",
					"Error al eleminar la categoría " + e.getLocalizedMessage());
		}
		return "redirect:/category/categories";
	}

}
