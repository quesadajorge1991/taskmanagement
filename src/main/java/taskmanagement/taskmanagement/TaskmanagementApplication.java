package taskmanagement.taskmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

import jakarta.annotation.PostConstruct;
import taskmanagement.taskmanagement.controllers.util.Permisos;
import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.entity.Groups;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.UserRepository;
import taskmanagement.taskmanagement.service.Group.GroupService;
import taskmanagement.taskmanagement.service.User.UserService;
import taskmanagement.taskmanagement.service.User.UserServiceImp;

@SpringBootApplication
// @ComponentScan(basePackages = {"taskmanagement.taskmanagement"})
public class TaskmanagementApplication {

	@Autowired
	UserRepository usersRepository;

	@Autowired
	GroupService groupService;

	@Autowired
	UserServiceImp userService;

	@PostConstruct
	public void addAuthorities() {

		/* preguntar si existe el usuario por default admin */
		if (userService.findByUsername("admin") == null) {
			User user = new User("admin", userService.encodePassword("admin"), "admin@admin.com", true, "Admin full");
			user.setUsername("admin");
			user.setPassword(userService.encodePassword("admin"));
			user.setEmail("admin@admin.com");
			user.setEnabled(true);
			user.setDescription("Full Admin");

			Groups group = new Groups();
			group.setGroupName("ADMINISTRADOR");
			group.setDescription("ADMINISTRADOR DEL SISTEMA");
			groupService.save(group);
			userService.save(user);

			HashMap<String, String> listauthorities = Permisos.getListauthorities();

			for (String authority : listauthorities.keySet()) {

				groupService.addGroupAuthority(group.getGroupName(), new Authority(authority));

			}
			String array[] = { group.getGroupName() };
			groupService.addUserToGroup(user.getUsername(), array);

		}

		// System.out.println("SIZE " + usersRepository.findAll().size());
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
