package taskmanagement.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taskmanagement.taskmanagement.entity.GroupMembers;

public interface GroupsMembersRepository extends JpaRepository<GroupMembers, Integer>{

}
