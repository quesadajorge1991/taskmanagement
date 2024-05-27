package taskmanagement.taskmanagement;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import entity.Users;
import repository.UsersRepository;

@SpringBootTest
class TaskmanagementApplicationTests {
	
	@Autowired
	UsersRepository usersRepository;
	

	@Test
	void contextLoads() {
		System.err.println("dsfsdfsdfsdfdf");
		
		System.out.println("SIZE " +  usersRepository.findAll().size());
		
		
		//JOptionPane.showMessageDialog(null, "Prueba", "Titulo", JOptionPane.INFORMATION_MESSAGE);
	}

}
