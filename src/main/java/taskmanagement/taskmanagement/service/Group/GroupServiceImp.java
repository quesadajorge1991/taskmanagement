package taskmanagement.taskmanagement.service.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.Authority;
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
		/* verificar que el grupo no exista */
		if (findByGroupName(group.getGroupName()) == null) {
			groupRepository.save(group);
		}

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
		List<String> autoritieStrings = groupAuthoritiesRepository
				.findGroupAuthorities(findByGroupName(groupName).getId());
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (int i = 0; i < autoritieStrings.size(); i++) {
			grantedAuthorities.add(new Authority(autoritieStrings.get(i)));
		}
		return grantedAuthorities;

	}

	public List<String> findGroupAuthoritiesByGroup(String groupName) {
		return groupAuthoritiesRepository.findGroupAuthorities(findByGroupName(groupName).getId());

	}

	@Override
	public List<String> findUsersInGroup(String groupName) {
		return groupsMembersRepository.findUsersInGroup(findByGroupName(groupName).getId());
	}

	@Override
	public void removeGroupAuthority(String groupName, GrantedAuthority authority) {
		/* no implementado */

	}

	@Override
	public void removeUserFromGroup(String username, String groupName) {
		List<GroupMembers> groupMembers = groupsMembersRepository.findByUsername(username);
		for (GroupMembers gm : groupMembers) {
			groupsMembersRepository.delete(gm);
		}

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

	@Override
	public void removeGroupAuthority(String groupName) {
		Groups group = findByGroupName(groupName);
		List<GroupAuthorities> listGroupAuthorityByGroup = groupAuthoritiesRepository.findByGroupId(group.getId());
		for (int i = 0; i < listGroupAuthorityByGroup.size(); i++) {
			groupAuthoritiesRepository.delete(listGroupAuthorityByGroup.get(i));
		}

	}

	@Override
	public List<GroupMembers> findGroupsByUsername(String username) {
		return groupsMembersRepository.findByUsername(username);
	}

	@Override
	public List<String> getKeys(HashMap<String, String> map) {
		List<String> listpermisoStrings = new ArrayList<String>();
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			listpermisoStrings.add(string);
		}
		return listpermisoStrings;
	}

	@Override
	public List<String> findNamesGroupsByUsername(String username) {
		return groupRepository.findNamesGroupsByUsername(username);
	}

	@Override
	public void deleteByGroupId(int id, String authority) {
		groupAuthoritiesRepository.deleteByGroupId(id, authority);

	}

	@Override
	public List<GroupAuthorities> findByAuthority(String authority) {
		return groupAuthoritiesRepository.findByAuthority(authority);
	}

	@Override
	public List<String> findGroupAuthorities(int idgroup) {
		return groupAuthoritiesRepository.findGroupAuthorities(idgroup);

	}

	@Override
	public List<GroupAuthorities> findByGroupId(int idgroup) {
		return groupAuthoritiesRepository.findByGroupId(idgroup);

	}

	@Override
	public GroupMembers findByUsernameGroup(String username, int idgroup) {
		return groupsMembersRepository.findByUsernameGroup(username, idgroup);
	}

	@Override
	public List<String> findUsersInGroup(int idgroup) {
		return groupsMembersRepository.findUsersInGroup(idgroup);
	}

	@Override
	public List<GroupMembers> findByUsername(String username) {
		return groupsMembersRepository.findByUsername(username);
	}

	public void removeGroupByUsername(String username) {
		List<String> grupoDeUsuarioString = findNamesGroupsByUsername(username);
		for (String groupName : grupoDeUsuarioString) {
			removeUserFromGroup(username, groupName);
		}

	}

	@Override
	public void addUserToGroup(String username, String selectedgroups[]) {
		/* eliminar todos los grupos de un usuario */

		/* validar que el username pertenesca al menos un grupo */
		removeGroupByUsername(username);

		/* agregar los permisos seleccionados al usuario */
		if (selectedgroups != null) {
			for (String groupName : selectedgroups) {
				addUserToGroup(username, groupName);
			}
		}

	}

	@Override
	public void addGroupAuthority(String groupName, String[] autorities) {
		if (autorities != null) {
			for (String authority : autorities) {
				addGroupAuthority(groupName, new Authority(authority));
			}

		}
	}

}
