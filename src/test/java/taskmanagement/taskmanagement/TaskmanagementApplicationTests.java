package taskmanagement.taskmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.service.IUserService.UserService;

@SpringBootTest
class TaskmanagementApplicationTests {

	@Autowired
	UserService userService;

//	@Autowired
//	private ApplicationContext context;

	@Test
	void contextLoads() {
		System.err.println("dsfsdfsdfsdfdf");
		
		//userService.save(new User("email", "descripcion", "pass", "admin"));

		 System.out.println("SIZE " + userService.findAll().size());

	}

}
