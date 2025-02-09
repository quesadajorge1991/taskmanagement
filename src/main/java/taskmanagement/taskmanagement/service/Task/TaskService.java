package taskmanagement.taskmanagement.service.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.Category;
import taskmanagement.taskmanagement.entity.Task;
import taskmanagement.taskmanagement.repository.CategoryRepository;
import taskmanagement.taskmanagement.repository.TaskRepository;
import taskmanagement.taskmanagement.service.ServiceBase;

@Service
public class TaskService implements ServiceBase<Task> {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		taskRepository.deleteById(id);

	}

	@Override
	public void delete(Task entity) {
		taskRepository.delete(entity);

	}

	@Override
	public void save(Task entity) {
		taskRepository.save(entity);

	}

	/* devuelve una lista de tipo categories a traves de un array de parametro */
	public List<Category> getListCategory(String categories[]) {
		List<Category> listcategories = new ArrayList<>();
		for (String catetogory : categories) {
			listcategories.add(new Category(catetogory));
		}
		return listcategories;

	}

	public void addTaskWithCategories(Task task, String categoryNames[]) {

		List<Category> categories = new ArrayList<>();
		for (String categoryName : categoryNames) {
			Category category = categoryRepository.findByCategoryName(categoryName);
			if (category == null) {
				Category newCategory = new Category();
				newCategory.setCategoryName(categoryName);
				categoryRepository.save(newCategory);
			}
			categories.add(category);
		}
		task.setCategories(categories);
		taskRepository.save(task);

	}

	@Override
	public Task findById(int id) {
		return taskRepository.findById(id).get();
	}

	public void deleteByUser(int userId) {
		taskRepository.deleteByUser(userId);
	}
}
