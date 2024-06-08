package taskmanagement.taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import taskmanagement.taskmanagement.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
