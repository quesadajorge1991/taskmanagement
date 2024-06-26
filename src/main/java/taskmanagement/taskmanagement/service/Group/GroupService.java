package taskmanagement.taskmanagement.service.Group;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.GroupManager;

import taskmanagement.taskmanagement.entity.GroupAuthorities;
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
	
	
	void deleteByGroupId(int id, String authority);
	
	List<GroupAuthorities> findByAuthority(String authority);
	
	
	List<String> findGroupAuthorities(int idgroup);
	
	
	List<GroupAuthorities> findByGroupId(int idgroup);
	
	GroupMembers findByUsernameGroup(String username,int idgroup);
	
	List<String> findUsersInGroup(int idgroup);
	
	List<GroupMembers> findByUsername(String username);
	
	void addUserToGroup(String username,String selectedgroups[]);
	

}
