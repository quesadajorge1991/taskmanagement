package taskmanagement.taskmanagement.service.User;

import taskmanagement.taskmanagement.entity.User;

public interface IUserService {
	
	public String encodePassword(String password);
	
	public User findByUsername(String username);
	

}
