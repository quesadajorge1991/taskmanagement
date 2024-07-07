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
import taskmanagement.taskmanagement.entity.Category;
import taskmanagement.taskmanagement.entity.Task;
import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.CategoryRepository;
import taskmanagement.taskmanagement.repository.GroupAuthoritiesRepository;
import taskmanagement.taskmanagement.repository.GroupRepository;
import taskmanagement.taskmanagement.repository.GroupsMembersRepository;
import taskmanagement.taskmanagement.repository.TaskRepository;
import taskmanagement.taskmanagement.repository.UserRepository;
import taskmanagement.taskmanagement.service.Group.GroupServiceImp;
import taskmanagement.taskmanagement.service.Task.TaskService;
import taskmanagement.taskmanagement.service.User.UserServiceImp;

@SpringBootTest
class TaskmanagementApplicationTests {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TaskService taskService;


	@Autowired
	TaskRepository taskRepository2;

	@Autowired
	UserServiceImp userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GroupServiceImp serviceImp;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	GroupServiceImp groupServiceImp;

	@Autowired
	GroupAuthoritiesRepository authoritiesRepository;

	@Autowired
	GroupsMembersRepository groupsMembersRepository;

	@Test
	void contextLoads() {

	/*	Task task=new Task();
		task.setTaskName("pruebaaatask");
		
		Category category=new Category();
		category.setCategoryName("categoria1");
	
		
		List<Task> list=new ArrayList<>();
		list.add(task);
		category.setTasks(list);
		
		
		
		categoryRepository.save(category);*/
		
		
		//System.out.println(userService.encodePassword("admin"));
		
		
		Task task=new Task();
		task.setTaskName("pruebaaatask");
		
		
		Category category=new Category();
		category.setCategoryName("categoriaInversa");
		
		List<Category> list=new ArrayList<>();
		list.add(category);
		
		task.setCategories(list);

	/* List<String> temp=new ArrayList<>();
		temp.add("pruebaCATGORIGA");
		temp.add("pruebaCATGORIGA1");
		temp.add("pruebaCATGORIGA2");*/

		taskService.save(task);

		
		
	//taskService.addTaskWithCategories(task, temp);


		
		
		

	}

}
