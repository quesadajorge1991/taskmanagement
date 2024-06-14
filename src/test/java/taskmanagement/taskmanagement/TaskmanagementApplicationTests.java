package taskmanagement.taskmanagement;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.GroupManager;

import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.GroupRepository;
import taskmanagement.taskmanagement.service.Group.GroupServiceImp;
import taskmanagement.taskmanagement.service.User.UserServiceImp;

@SpringBootTest
class TaskmanagementApplicationTests {

	@Autowired
	UserServiceImp userService;

	@Autowired
	GroupServiceImp serviceImp;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	GroupServiceImp groupServiceImp;

	@Test
	void contextLoads() {

		// userService.save(new User("email", "descripcion", "pass", "admin"));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new Authority("READ"));
		authorities.add(new Authority("WRITE"));
		authorities.add(new Authority("UPDATE"));

		// groupServiceImp.createGroup("MIGRUPO", authorities);
		// groupServiceImp.deleteGroup("MIGRUPO");
		GrantedAuthority authority = new GrantedAuthority() {

			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "READ";
			}
		};
		groupServiceImp.removeGroupAuthority("erwerwex", authority);
		// System.out.println("SIZE " + );

	}

}
