package taskmanagement.taskmanagement.service.IUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public String encodePassword(String password) {
		/*PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);*/
		return "sdfdf";
	}

}
