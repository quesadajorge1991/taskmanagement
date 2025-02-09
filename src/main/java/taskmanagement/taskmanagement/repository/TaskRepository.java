package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import taskmanagement.taskmanagement.entity.Task;



public interface TaskRepository extends JpaRepository<Task, Integer> {


   @Query(value = "DELETE FROM tasks WHERE user_id=?1",nativeQuery = true)
   @Modifying
   void deleteByUser(int id);

}
