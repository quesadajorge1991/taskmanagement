package taskmanagement.taskmanagement.service.User;

import taskmanagement.taskmanagement.entity.User;

public interface UserService {
	
	public String encodePassword(String password);
	
	public User findByUsername(String username);
	
	public boolean checkPasswordOld(String username,String passwordOld);
	
	
	
	

}
