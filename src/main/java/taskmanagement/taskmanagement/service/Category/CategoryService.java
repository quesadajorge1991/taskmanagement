package taskmanagement.taskmanagement.service.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.Category;
import taskmanagement.taskmanagement.repository.CategoryRepository;
import taskmanagement.taskmanagement.service.ServiceBase;

@Service
public class CategoryService implements ServiceBase<Category> {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		categoryRepository.deleteById(id);

	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);

	}

	@Override
	public void save(Category entity) {
		categoryRepository.save(entity);

	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id).get();
	}

}
