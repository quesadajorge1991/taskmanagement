package taskmanagement.taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import taskmanagement.taskmanagement.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
