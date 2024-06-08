package taskmanagement.taskmanagement.controllers.Category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

	
	@GetMapping(value = "/category")
	public String tasks() {
		return "/category/index";
	}
	
}
