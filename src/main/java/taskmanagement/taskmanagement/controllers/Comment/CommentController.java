package taskmanagement.taskmanagement.controllers.Comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {
	
	@GetMapping(value = "/comment")
	public String tasks() {
		return "/comment/index";
	}

}
