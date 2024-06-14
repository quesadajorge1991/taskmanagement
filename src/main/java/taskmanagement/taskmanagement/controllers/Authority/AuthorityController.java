package taskmanagement.taskmanagement.controllers.Authority;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.IUserService.UserService;

@Controller
@RequestMapping(value = "/authority")
public class AuthorityController {
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping(value = "/addAutority")
	public String addAuthority(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/authority/addAutority";
	}
	
	
	

}
