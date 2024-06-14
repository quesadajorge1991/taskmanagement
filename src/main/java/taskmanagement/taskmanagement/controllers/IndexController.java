package taskmanagement.taskmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("msg", "Mensaje del controlador");
		return "index";
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

}
