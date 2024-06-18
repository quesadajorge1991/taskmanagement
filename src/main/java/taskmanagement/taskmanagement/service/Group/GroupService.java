package taskmanagement.taskmanagement.service.Group;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.GroupManager;

import jakarta.transaction.Transactional;
import taskmanagement.taskmanagement.entity.GroupMembers;
import taskmanagement.taskmanagement.entity.Groups;

public interface GroupService extends GroupManager {

	public List<Groups> findAll();

	public void save(Groups group);

	public void deleteById(int id);

	public void delete(Groups group);

	public Groups findById(int id);

	public Groups findByGroupName(String findByGroupName);

	List<String> findAllGroups();

	void removeGroupAuthority(String groupName, GrantedAuthority authority);
	
	void removeGroupAuthority(String groupName);
	
	List<GroupMembers> findGroupsByUsername(String username);
	
	List<String> getKeys(HashMap<String, String> map);
	
	List<String> findNamesGroupsByUsername(String username);
	 
	 

}
