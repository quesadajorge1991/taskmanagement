package taskmanagement.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import taskmanagement.taskmanagement.entity.GroupMembers;

public interface GroupsMembersRepository extends JpaRepository<GroupMembers, Integer>{

	@Query(value = "FROM GroupMembers gm WHERE gm.username=?1 and gm.groupId.id=?2")
	GroupMembers findByUsernameGroup(String username,int idgroup);
	
	@Query(value = "SELECT username FROM GroupMembers WHERE groupId.id=?1")
	List<String> findUsersInGroup(int idgroup);
	
	List<GroupMembers> findByUsername(String username);
	
	
	

	
	
	
	


	
}
