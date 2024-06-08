package taskmanagement.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import taskmanagement.taskmanagement.repository.UserRepository;



@SpringBootApplication
//@ComponentScan(basePackages = {"taskmanagement.taskmanagement"})
public class TaskmanagementApplication {
	
	@Autowired
	UserRepository usersRepository;
	
	
	///@PostConstruct
	public void addAuthorities() {
		
	//	System.out.println("SIZE " +  usersRepository.findAll().size());
	}
	
	

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
