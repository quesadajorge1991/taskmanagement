package taskmanagement.taskmanagement.controllers.Task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
	
	@GetMapping(value = "/tasks")
	public String tasks() {
		return "/task/index";
	}

}
