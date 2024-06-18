package taskmanagement.taskmanagement.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import taskmanagement.taskmanagement.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserServiceImp userServiceImp;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userServiceImp.findByUsername(username);
	}

}
