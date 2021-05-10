package miu.edu.ecommerce.service;


import miu.edu.ecommerce.domain.*;
import miu.edu.ecommerce.repository.*;
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
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	BuyerRepository buyerRepository;
	@Autowired
	AdminRepository adminRepository;

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
			Role role = roleRepository.findRoleById(newUser.getRole());
			user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findRoleById(newUser.getRole()))));
			final User createdUser = userRepository.save(user);
			switch (newUser.getRole().intValue()){
				case 1:
					Admin admin = new Admin();
					user.setEnabled(true);
					admin.setUser(user);
					admin.setLevel("1");
					adminRepository.save(admin);
					break;
				case 2:
					Seller seller = new Seller();
					seller.setApproved(false);
					seller.setUser(user);
					sellerRepository.save(seller);
					break;
				case 3:
					Buyer buyer = new Buyer();
					buyer.setAccumulatedPoints(0);
					buyer.setUser(user);
					buyerRepository.save(buyer);
					break;
			}

			//final ConfirmationToken confirmationToken = new ConfirmationToken(user);

			//confirmationTokenService.saveConfirmationToken(confirmationToken);
			return "Register successfully.";
		}catch (Exception ex){
			return ex.getMessage();
		}
	}

}