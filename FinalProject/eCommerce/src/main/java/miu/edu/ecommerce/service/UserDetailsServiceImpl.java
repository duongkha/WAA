package miu.edu.ecommerce.service;


import miu.edu.ecommerce.domain.ConfirmationToken;
import miu.edu.ecommerce.domain.NewUser;
import miu.edu.ecommerce.domain.Role;
import miu.edu.ecommerce.domain.User;
import miu.edu.ecommerce.repository.RoleRepository;
import miu.edu.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new UserDetailsImpl(user);
	}

	public String signUpUser(NewUser newUser) {
		try {
			if (userRepository.getUserByUsername(newUser.getUsername()) != null) {
				return "User name is existing.";
			}
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			final String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			User user = new User();
			user.setUsername(newUser.getUsername());
			user.setPassword(encryptedPassword);
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPhoneNumber(newUser.getPhone());
			Role role = roleRepository.getRoleById(newUser.getRole());
			user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getRoleById(newUser.getRole()))));
			final User createdUser = userRepository.save(user);

			//final ConfirmationToken confirmationToken = new ConfirmationToken(user);

			//confirmationTokenService.saveConfirmationToken(confirmationToken);
			return "Register successfully.";
		}catch (Exception ex){
			return ex.getMessage();
		}
	}

}