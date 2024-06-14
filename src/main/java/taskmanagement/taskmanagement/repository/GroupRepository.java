package taskmanagement.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;

import taskmanagement.taskmanagement.entity.Groups;

public interface GroupRepository extends JpaRepository<Groups, Integer> {
	
	Groups findByGroupName(String groupName);
	
	@Query(value = "SELECT groupName FROM Groups")
	List<String> findAllGroups();
	
	
	
}
