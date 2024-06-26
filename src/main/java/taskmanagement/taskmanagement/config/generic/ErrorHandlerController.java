package taskmanagement.taskmanagement.config.generic;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandlerController {
	
	@Autowired
	private MessageSource messageSource;
	
	  @ExceptionHandler(NoHandlerFoundException.class)
	    public String handleNoHandlerFoundException(HttpServletRequest request, Model model) {
	        // Aquí puedes agregar cualquier lógica adicional si es necesario
		  model.addAttribute("title", messageSource.getMessage("N.notFound", null, Locale.getDefault()));
	        return "error/404"; // Asegúrate de tener esta vista en tus recursos de plantilla
	    }

}
