package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import taskmanagement.taskmanagement.entity.Task;



public interface TaskRepository extends JpaRepository<Task, Integer> {

}
