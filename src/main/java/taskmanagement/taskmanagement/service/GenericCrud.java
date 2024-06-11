package taskmanagement.taskmanagement.service;

import java.util.List;

public interface GenericCrud {
	
	public List<Object> findAll();
	
	public Object findById(long id);
	
	

}
