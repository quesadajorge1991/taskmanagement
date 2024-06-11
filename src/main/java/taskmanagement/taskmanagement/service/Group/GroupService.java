package taskmanagement.taskmanagement.service.Group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.Groups;
import taskmanagement.taskmanagement.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	GroupRepository groupRepository;

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
	
	
	
}
