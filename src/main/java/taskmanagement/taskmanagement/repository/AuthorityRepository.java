package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskmanagement.taskmanagement.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
