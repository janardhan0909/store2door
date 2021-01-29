package com.store2door.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store2door.api.dto.UserDTO;
import com.store2door.api.payload.ApiResponse;
import com.store2door.api.payload.SignUpRequest;
import com.store2door.api.repository.UserRepository;
import com.store2door.api.security.CurrentUser;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
    private UserServiceImpl userService;
	
	@Autowired
	UserRepository userRepository;
	
    @PostMapping("/me")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    	UserDTO userDTO = new UserDTO();
    	try {
        userDTO = userService.loadUserData(currentUser.getId());
    	}catch(Exception exception) {
    		
    	}
    	return userDTO;
    }
    @PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody String data)throws Exception {
		ApiResponse apiResponse=null;
		try {
			apiResponse=userService.changePassword(new ObjectMapper().readValue(data, SignUpRequest.class));
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.ok(apiResponse);
	}
}