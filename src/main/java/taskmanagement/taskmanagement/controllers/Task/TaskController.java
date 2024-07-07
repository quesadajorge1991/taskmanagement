package taskmanagement.taskmanagement.controllers.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import taskmanagement.taskmanagement.controllers.util.EstadoTarea;
import taskmanagement.taskmanagement.entity.Category;
import taskmanagement.taskmanagement.entity.Task;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.Category.CategoryService;
import taskmanagement.taskmanagement.service.Task.TaskService;
import taskmanagement.taskmanagement.service.User.UserServiceImp;

@Controller
@RequestMapping(value = "/task")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	UserServiceImp userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/tasks")
	public String tasks(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.task.tasks", null, Locale.getDefault()));
		model.addAttribute("tasks", taskService.findAll());
		return "/task/tasks";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.task.add", null, Locale.getDefault()));
		model.addAttribute("estatustasks", EstadoTarea.getEstadoTarea());
		model.addAttribute("task", new Task());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("categories", categoryService.findAll());

		return "/task/add";
	}

	@PostMapping("/addTask")
	public String add_Usuario(@ModelAttribute("task") @Valid Task task, BindingResult result, Model model,
			@RequestParam(value = "catetogories", required = false) String catetogories[],
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("title", messageSource.getMessage("N.task.add", null, Locale.getDefault()));
			model.addAttribute("estatustasks", EstadoTarea.getEstadoTarea());
			model.addAttribute("users", userService.findAll());
			model.addAttribute("user", new User());
			return "/task/add";
		}

		try {
			if (task.getTaskId() == 0) { /* si el id es 0 se add */

			/* 	List<String> tempcate = new ArrayList<>();
				for (String categ : catetogories) {
					tempcate.add(categ);
				}*/
				taskService.addTaskWithCategories(task, catetogories);

				/*
				 * taskService.save(new Task(task.getDeadline(), task.getDescription(),
				 * task.getStatus(),
				 * task.getTaskName(), task.getUser()));
				 */

				redirectAttributes.addFlashAttribute("msgtype", "success");
				redirectAttributes.addFlashAttribute("msgtitle", "Información");
				redirectAttributes.addFlashAttribute("msgbody", "Tarea agrega correctamente ");
				return "redirect:/task/tasks";

			} else {
				taskService.save(new Task(task.getTaskId(), task.getDeadline(), task.getDescription(), task.getStatus(),
						task.getTaskName(), task.getUser()));

				redirectAttributes.addFlashAttribute("msgbody",
						"Tarea " + task.getTaskName() + " modificado correctamente");
				redirectAttributes.addFlashAttribute("msgtype", "success");
				redirectAttributes.addFlashAttribute("msgtitle", "Información");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el usuario " + e.getLocalizedMessage());
			System.out.println(e.getLocalizedMessage());

		}

		return "redirect:/task/task";
	}

	@GetMapping("/delete/{taskId}")
	public String delete(@PathVariable("taskId") int taskId, RedirectAttributes redirectAttributes) {
		try {

			taskService.deleteById(taskId);
			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Tarea agrega correctamente ");
			return "redirect:/task/tasks";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar la tarea " + e.getMessage());
		}
		return "redirect:/task/tasks";
	}

	@GetMapping("/update/{taskId}")
	public String update(Model model,@PathVariable("taskId") int taskId) {
		Task task=taskService.findById(taskId);
		model.addAttribute("title", messageSource.getMessage("N.task.update", null, Locale.getDefault()));
		model.addAttribute("task", task);
		model.addAttribute("estatustasks", EstadoTarea.getEstadoTarea());
		model.addAttribute("task", new Task());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		return "/task/update";

	}
	

}
