package taskmanagement.taskmanagement.controllers.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Permisos {

	public enum Authorties {

		ROLE_CREATE, ROLE_UPDATE, ROLE_DELETE, ROLE_ADMIN, ROLE_CATEGORY, ROLE_COMMENT, ROLE_TASK,

	}

	public static HashMap<String, String> getListauthorities() {
		/*
		 * List<String> listauthorities = new ArrayList<String>();
		 * listauthorities.add("ROLE_ADMIN"); listauthorities.add("ROLE_CREATE");
		 * listauthorities.add("ROLE_UPDATE"); listauthorities.add("ROLE_DELETE");
		 * listauthorities.add("ROLE_CATEGORY"); listauthorities.add("ROLE_COMMENT");
		 * listauthorities.add("ROLE_TASK");
		 */

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("ADMIN", "Permiso Administrador full del sistema");
		hashMap.put("READ", "PermisoTarea");
		hashMap.put("CREATE", "Permiso Crear");
		hashMap.put("UPDATE", "Permiso Modificar");
		hashMap.put("DELETE", "Permiso Eliminar");
		hashMap.put("CATEGORY", "Permiso categoria");
		hashMap.put("COMMENT", "Permiso Comentario");
		hashMap.put("TASK", "PermisoTarea");
	
		
		
		
		//hashMap.get(hashMap)

		return hashMap;
	}

}
