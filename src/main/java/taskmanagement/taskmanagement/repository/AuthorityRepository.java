package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import taskmanagement.taskmanagement.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
