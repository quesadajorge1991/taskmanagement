package taskmanagement.taskmanagement.controllers.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/user/users";
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "/user/add";
	}

	@PostMapping(value = "/addUser")
	public String add_Usuario(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

		try {

			User userr = new User(user.getUsername(), user.getPassword(), user.getEmail(), user.isEnabled(),
					user.getDescription());

			userService.save(userr);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario agregado correctamente ");
			return "redirect:/user/users";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/user/users";
	}

	@GetMapping(value = "/deleteuser/{userId}")
	public String delete(@PathVariable(value = "userId") int id, RedirectAttributes redirectAttributes) {

		try {
			userService.delete(userService.findById(id));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario eleminado correctamente ");
			return "redirect:/user/users";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/user/users";
	}

}
