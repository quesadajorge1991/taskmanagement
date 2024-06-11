package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskmanagement.taskmanagement.entity.Groups;

public interface GroupRepository extends JpaRepository<Groups, Integer> {

}
