package taskmanagement.taskmanagement.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("msg", "Mensaje del controlador");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("isAuthenticated", authentication != null);
		return "index";
	}
	
	@GetMapping(value = "/home")
	public String home(Model model) {
		model.addAttribute("msg", "Mensaje del controlador");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("isAuthenticated", authentication != null);
		return "home";
	}

	@GetMapping(value = "/base")
	public String in() {
		// model.addAttribute("msg", "Mensaje del controlador");
		return "/templateBase/BaseAdminLTE";
	}

	@GetMapping(value = "/login")
	public String login() {

		return "login";
	}

	@GetMapping(value = "/error/403")
	public String accessDeniedPage(Model model) {
		model.addAttribute("title", messageSource.getMessage("accessDeniedPage", null, Locale.getDefault()));
		return "/error/403";
	}



}
