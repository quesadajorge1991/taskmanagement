package taskmanagement.taskmanagement.service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.User;
import taskmanagement.taskmanagement.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

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

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);

	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean checkPasswordOld(String passwordIngresada, String passwordOldEncrypted) {
		return bCryptPasswordEncoder.matches(passwordIngresada, passwordOldEncrypted);

	}

}
