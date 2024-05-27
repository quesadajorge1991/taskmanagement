package controllers.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
	
	@GetMapping(value = "/project/projects")
	public String projects() {
		return "/project/projects";
	}

}
