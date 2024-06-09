package taskmanagement.taskmanagement.controllers.UserController;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value = "/getUsers")
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "templateBase/Components/user/comboSelect2 :: users";

	}
	
	@GetMapping(value = "/resetpass")
	public String resetpass(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/user/resetpass";
	}
	
	@PostMapping("/resetPassword")
	public @ResponseBody String resetPassword(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "password") String password) {
		JSONObject jsono = new JSONObject();

		User user = userService.findById(userId);

		System.err.println(user.getUsername());

		try {
			User usuario = userService.findById(userId);
			usuario.setPassword(userService.encodePassword(user.getPassword()));
			usuario.setEnabled(user.isEnabled());
			usuario.setDescription(user.getDescription());

			userService.save(usuario);

			jsono.put("msgtype", "success");
			jsono.put("msgtitle", "Información");
			jsono.put("msgbody", "Se actualizó la contraseña al usuario" + user.getUsername());
		} catch (Exception e) {
			// TODO: handle exception
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "Error al actualizar la contraseña al usuario" + user.getUsername());
		}

		return jsono.toString();
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

	@GetMapping(value = "/update/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		User usuarios = userService.findById(id);
		model.addAttribute("user", usuarios);
		return "/user/update";
	}

	@PostMapping(value = "/update")
	public String updateuser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

		try {

			System.err.println(user.getUserId());
			
			userService.save(new User(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), user.isEnabled(),
					user.getDescription()));
			redirectAttributes.addFlashAttribute("msgbody",
					"Usuario " + user.getUsername() + " modificado correctamente");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Error al guardar los datos");
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
		}

		return "redirect:/user/users";
	}

	/* PRUEBAS CON AJAX */

	@GetMapping(value = "/delete_user/{userId}")
	public @ResponseBody String delete_user(@PathVariable(value = "userId") int id) {

		// JSONObject jsono = new JSONObject();
		System.out.println(id);
		try {
			userService.delete(userService.findById(id));

			// jsono.put("msgtipo", "success");
			// jsono.put("msgtitu", "Información");
			// jsono.put("msgbody", "Usuario eleminado correctamente ");

			return "redirect:/user/users";

		} catch (Exception e) {

			/// jsono.put("msgtipo", "error");
			// jsono.put("msgtitu", "Error");
			// jsono.put("msgbody", "Error al eleminar el usuario " +
			/// e.getLocalizedMessage());

		}

		return "resultado";
	}

	@GetMapping(value = "/userss")
	public String userss(Model model) {
		model.addAttribute("users", userService.findAll());
		return "templateBase/Components/components :: tableUser";
	}

	/* FIN VPRUEBAS CON AJAX */

}
