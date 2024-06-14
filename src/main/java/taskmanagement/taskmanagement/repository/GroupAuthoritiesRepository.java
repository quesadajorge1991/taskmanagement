package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskmanagement.taskmanagement.entity.GroupAuthorities;

public interface GroupAuthoritiesRepository extends JpaRepository<GroupAuthorities, Integer> {

}
