package taskmanagement.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(value = "controllers")
public class TaskmanagementApplication {
	
	//@Autowired
	//UsersRepository usersRepository;
	
	
	///@PostConstruct
	public void addAuthorities() {
		
	//	System.out.println("SIZE " +  usersRepository.findAll().size());
	}
	
	

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
