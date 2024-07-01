package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taskmanagement.taskmanagement.entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
