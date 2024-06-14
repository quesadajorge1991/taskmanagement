package taskmanagement.taskmanagement.service.Authority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.repository.AuthorityRepository;
import taskmanagement.taskmanagement.service.ServiceBase;

@Service
public class AuthorityServiceImp implements ServiceBase<Authority> {

	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public List<Authority> findAll() {

		return authorityRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		authorityRepository.deleteById(id);

	}

	@Override
	public void save(Authority entity) {
		authorityRepository.save(entity);

	}

	@Override
	public void delete(Authority entity) {
		authorityRepository.delete(entity);

	}

}
