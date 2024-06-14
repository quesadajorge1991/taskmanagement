package taskmanagement.taskmanagement.service.Group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.GroupAuthorities;
import taskmanagement.taskmanagement.entity.GroupMembers;
import taskmanagement.taskmanagement.entity.Groups;
import taskmanagement.taskmanagement.repository.AuthorityRepository;
import taskmanagement.taskmanagement.repository.GroupAuthoritiesRepository;
import taskmanagement.taskmanagement.repository.GroupRepository;
import taskmanagement.taskmanagement.repository.GroupsMembersRepository;
import taskmanagement.taskmanagement.repository.UserRepository;
import taskmanagement.taskmanagement.service.ServiceBase;

@Service
public class GroupServiceImp implements GroupService, ServiceBase<Groups> {

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	GroupAuthoritiesRepository groupAuthoritiesRepository;

	@Autowired
	GroupsMembersRepository groupsMembersRepository;

	public List<Groups> findAll() {
		return groupRepository.findAll();
	}

	public void save(Groups group) {
		groupRepository.save(group);
	}

	public void deleteById(int id) {
		groupRepository.deleteById(id);
	}

	public void delete(Groups group) {
		groupRepository.delete(group);
	}

	public Groups findById(int id) {
		return groupRepository.findById(id).get();
	}

	@Override
	public void addGroupAuthority(String groupName, GrantedAuthority authority) {
		Groups group = findByGroupName(groupName);
		groupAuthoritiesRepository.save(new GroupAuthorities(authority.getAuthority(), new Groups(group.getId())));

	}

	@Override
	public void addUserToGroup(String username, String group) {
		Groups gr = findByGroupName(group);
		groupsMembersRepository.save(new GroupMembers(username, new Groups(gr.getId())));

	}

	@Override
	public void createGroup(String groupName, List<GrantedAuthority> authorities) {
		groupRepository.save(new Groups(groupName)); /* crea un grupo */
		Groups group = findByGroupName(groupName); /* busca ese grupo por su nombre */
		for (int i = 0; i < authorities.size(); i++) {
			groupAuthoritiesRepository
					.save(new GroupAuthorities(authorities.get(i).getAuthority(), new Groups(group.getId())));

		}

	}

	@Override
	public void deleteGroup(String groupName) {
		Groups group = findByGroupName(groupName);
		groupRepository.deleteById(group.getId());

	}

	@Override
	public List<String> findAllGroups() {
		return groupRepository.findAllGroups();
	}

	@Override
	public List<GrantedAuthority> findGroupAuthorities(String groupName) {
		/* List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(); */
		return null;
	}

	@Override
	public List<String> findUsersInGroup(String groupName) {
		/* List<String> listuser = new ArrayList<String>(); */

		return null;
	}

	@Override
	public void removeGroupAuthority(String groupName, GrantedAuthority authority) {
		Groups groups = findByGroupName(groupName);
		groupAuthoritiesRepository.delete(new GroupAuthorities(authority.getAuthority(), new Groups(groups.getId())));
		// groupAuthoritiesRepository.delete(new );

	}

	@Override
	public void removeUserFromGroup(String username, String groupName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameGroup(String oldName, String newName) {
		Groups group = findByGroupName(oldName);
		groupRepository.save(new Groups(group.getId(), newName));

	}

	@Override
	public Groups findByGroupName(String findByGroupName) {
		return groupRepository.findByGroupName(findByGroupName);
	}

}
