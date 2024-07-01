package taskmanagement.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.annotation.PostConstruct;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.UserRepository;
import taskmanagement.taskmanagement.service.User.UserService;
import taskmanagement.taskmanagement.service.User.UserServiceImp;



@SpringBootApplication
//@ComponentScan(basePackages = {"taskmanagement.taskmanagement"})
public class TaskmanagementApplication {
	
	@Autowired
	UserRepository usersRepository;

	@Autowired
	UserServiceImp serviceImp;



	
	
	@PostConstruct
	public void addAuthorities() {

//serviceImp.save(new User("admin", serviceImp.encodePassword("admin"), "admin@admin.com", true, "Admin full"));


		
	//	System.out.println("SIZE " +  usersRepository.findAll().size());
	}
	
	

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
