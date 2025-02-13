package taskmanagement.taskmanagement.controllers.User;

import java.util.Locale;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import taskmanagement.taskmanagement.controllers.util.Permisos;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.Group.GroupServiceImp;
import taskmanagement.taskmanagement.service.User.UserServiceImp;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserServiceImp userService;

	@Autowired
	GroupServiceImp groupService;
	
	@Autowired
    private MessageSource messageSource;

	// @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/users")
	public String users(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.user.users", null, Locale.getDefault()));
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
		model.addAttribute("title", messageSource.getMessage("N.user.resetPass", null, Locale.getDefault()));
		model.addAttribute("users", userService.findAll());
		return "/user/resetpass";
	}

	@GetMapping(value = "/profile")
	public String changePass(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.user.profile", null, Locale.getDefault()));
		model.addAttribute("user", new User());
		return "/user/profile";
	}

	@PostMapping("/resetPassword")
	public @ResponseBody String resetPassword(@RequestParam(value = "userId") int userId,
			@RequestParam(value = "password") String password) {
		JSONObject jsono = new JSONObject();

		User user = userService.findById(userId);

		try {

			userService.save(new User(user.getUserId(), user.getUsername(), userService.encodePassword(password),
					user.getEmail(), user.isEnabled(), user.getDescription()));

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

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/changePassword")
	public @ResponseBody String changePassword(@RequestParam(value = "username") String username,
			@RequestParam(value = "passwordold") String passwordold,@RequestParam(value = "passwordnew") String passwordnew) {
		JSONObject jsono = new JSONObject();
		User user = userService.findByUsername(username);
		
		/*verificar que la pass anterior coincida con la proporcionada en el campo pass anterior*/
		if (userService.checkPasswordOld(passwordold, user.getPassword())) { 
			try {

				user.setPassword(userService.encodePassword(passwordnew));
				user.setEnabled(user.isEnabled());
				user.setDescription(user.getDescription());

				userService.save(user);

				jsono.put("msgtype", "success");
				jsono.put("msgtitle", "Información");
				jsono.put("msgbody", "El usuario " + user.getUsername() + " cambió la contraseña correctamente");
			} catch (Exception e) {
				// TODO: handle exception
				jsono.put("msgtype", "error");
				jsono.put("msgtitle", "Error");
				jsono.put("msgbody", "Error al actualizar la contraseña al usuario" + user.getUsername());
			}
		}else {
			/*  */
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "La contraseña anterior es incorrecta");
		}
		
		
	

		return jsono.toString();
	}

	@GetMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("title", messageSource.getMessage("N.user.adduser", null, Locale.getDefault()));
		model.addAttribute("user", new User());
		model.addAttribute("groups", groupService.findAll());
		return "/user/add";
	}

	@PostMapping("/addUser")
	public String add_Usuario(@ModelAttribute("user") @Valid User user, BindingResult result, Model model,
			@RequestParam(value = "selectedgroups", required = false) String selectedgroups[],
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("groups", groupService.findAll());
			model.addAttribute("groupsName", groupService.findAllGroups());
			model.addAttribute("groupNameByUser", groupService.findNamesGroupsByUsername(user.getUsername()));
			return "/user/update";
		}

		try {
	    		if (user.getUserId() == 0) { /* si el id es 0 se add */
	    			if(userService.findByUsername(user.getUsername())==null) {
	    				userService.save(new User(user.getUsername(), userService.encodePassword(user.getPassword()),
								user.getEmail(), user.isEnabled(), user.getDescription()));
						groupService.addUserToGroup(user.getUsername(),
								selectedgroups); /* se le agrega todos los grupos seleccionados al usuario */
						redirectAttributes.addFlashAttribute("msgtype", "success");
						redirectAttributes.addFlashAttribute("msgtitle", "Información");
						redirectAttributes.addFlashAttribute("msgbody", "Usuario agregado correctamente ");
						return "redirect:/user/users";
	    			}else {
	    				redirectAttributes.addFlashAttribute("msgtype", "error");
						redirectAttributes.addFlashAttribute("msgtitle", "Error");
						redirectAttributes.addFlashAttribute("msgbody", "El usuario que intenta agregar ya existe ");
	    			}
				
				} else {
					User usuario = userService.findById(user.getUserId());
					userService.save(new User(usuario.getUserId(), user.getUsername(), usuario.getPassword(),
							user.getEmail(), user.isEnabled(), user.getDescription()));

					groupService.addUserToGroup(user.getUsername(), selectedgroups);

					redirectAttributes.addFlashAttribute("msgbody",
							"Usuario " + user.getUsername() + " modificado correctamente");
					redirectAttributes.addFlashAttribute("msgtype", "success");
					redirectAttributes.addFlashAttribute("msgtitle", "Información");
				}
		

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el usuario " + e.getLocalizedMessage());
			System.out.println(e.getLocalizedMessage());

		}

		return "redirect:/user/users";
	}

	@GetMapping(value = "/deleteuser/{userId}")
	public String delete(@PathVariable(value = "userId") int id, RedirectAttributes redirectAttributes) {
		try {
			/* Eliminar todas las tareas asociadas a dicho usuario */
			userService.deleteAllTaskByUser(id);
			userService.delete(userService.findById(id));
			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario eleminado correctamente ");
			return "redirect:/user/users";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el usuario " + e.getLocalizedMessage());
		}
		return "redirect:/user/users";
	}

	@GetMapping(value = "/update/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		model.addAttribute("title", messageSource.getMessage("N.user.updateuser", null, Locale.getDefault()));
		User usuarios = userService.findById(id);
		model.addAttribute("user", usuarios);
		model.addAttribute("groups", groupService.findAll());
		model.addAttribute("groupsName", groupService.findAllGroups());
		model.addAttribute("groupNameByUser", groupService.findNamesGroupsByUsername(usuarios.getUsername()));
		return "/user/update";
	}

	@PostMapping(value = "/update")
	public String updateuser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

		try {

			userService.save(new User(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(),
					user.isEnabled(), user.getDescription()));

			redirectAttributes.addFlashAttribute("msgbody",
					"Usuario " + user.getUsername() + " modificado correctamente");
			redirectAttributes.addFlashAttribute("msgtype", "success");
			redirectAttributes.addFlashAttribute("msgtitle", "Información");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Error al guardar los datos");
			redirectAttributes.addFlashAttribute("msgtype", "error");
			redirectAttributes.addFlashAttribute("msgtitle", "Error");
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
