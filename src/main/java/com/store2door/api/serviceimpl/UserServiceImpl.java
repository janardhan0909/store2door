package com.store2door.api.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.RoleDTO;
import com.store2door.api.dto.UserDTO;
import com.store2door.api.model.User;
import com.store2door.api.payload.ApiResponse;
import com.store2door.api.payload.SignUpRequest;
import com.store2door.api.repository.UserRepository;
import com.store2door.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO loadUserData(long userId) throws Exception {
		UserDTO userDTO = null;
		try {
			Optional<User> dbData = userRepository.findById(userId);
			if(dbData.isPresent()) {
				User user = dbData.get();
				userDTO = modelMapper.map(user, UserDTO.class);
				userDTO.setRole(modelMapper.map(user.getRoles().iterator().next(), RoleDTO.class));
				
				//userDTO = new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getMobileNumber(), "", user.getDeleteFlag(), user.getRoles(), userDTO.get, isAdmin, isUser)
			}
		}catch(Exception  exception) {
			throw exception;
		}
		return userDTO;
	}

	@Override
	public ApiResponse changePassword(SignUpRequest signUpRequest) throws Exception {
		ApiResponse apiResponse=null;
		try {
			Optional<User> findByEmail = userRepository.findById(signUpRequest.getId());
			if(findByEmail.isPresent()) {
				User user = findByEmail.get();
				if(passwordEncoder.matches(signUpRequest.getPassword(), user.getPassword())) {
					user.setPassword(passwordEncoder.encode(signUpRequest.getNewPassword()));
					userRepository.save(user);
					apiResponse=new ApiResponse(true, "Password changed Successfully");
				}else {
					apiResponse=new ApiResponse(false, "Invalid Old Password");
				}
			}
		} catch (Exception e) {
			apiResponse=new ApiResponse(false, e.getMessage());
			throw e;
		}
		return apiResponse;
	}

}
