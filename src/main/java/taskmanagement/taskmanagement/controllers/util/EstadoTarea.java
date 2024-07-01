package taskmanagement.taskmanagement.controllers.util;

import java.util.ArrayList;
import java.util.List;

public class EstadoTarea {
	
	

	public static List<String> getEstadoTarea() {
		List<String> estadoTarea=new ArrayList<>();
		estadoTarea.add("ToDo");
		estadoTarea.add("InProgress");
		estadoTarea.add("Done");
		return estadoTarea;
	}


	
	
}
