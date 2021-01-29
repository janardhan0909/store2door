package com.store2door.api.controller;


import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store2door.api.dto.EmailDetailsDTO;
import com.store2door.api.exceptions.AppException;
import com.store2door.api.model.Role;
import com.store2door.api.model.RoleName;
import com.store2door.api.model.User;
import com.store2door.api.payload.ApiResponse;
import com.store2door.api.payload.FacebookLoginRequest;
import com.store2door.api.payload.GoogleLoginRequest;
import com.store2door.api.payload.JwtAuthenticationResponse;
import com.store2door.api.payload.LoginRequest;
import com.store2door.api.payload.SignUpRequest;
import com.store2door.api.repository.RoleRepository;
import com.store2door.api.repository.UserRepository;
import com.store2door.api.security.JwtTokenProvider;
import com.store2door.api.service.MailerService;
import com.store2door.api.utils.JString;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
	private MailerService mailerServices;
    
    @GetMapping("/welcome")
	public String testApplication() {
	return "Hello! Welcome to Store2Door";
	}
    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody String loginRequestData)throws Exception {
		String jwt = null;
		try {
			LoginRequest loginRequest = new ObjectMapper().readValue(loginRequestData, LoginRequest.class);
			Optional<User> findByUsernameOrEmail = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(),loginRequest.getUsernameOrEmail());
			if(findByUsernameOrEmail.isPresent()) {
				jwt=authentication(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
				saveDeviceId(findByUsernameOrEmail.get(),loginRequest.getDeviceId());
			}
		} catch (Exception exception) {
			if ("Bad credentials".contains(exception.getMessage())) {
				jwt = null;
			} else {
				throw exception;
			}
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

    @PostMapping("/signup")
	public ApiResponse registerUser(@RequestBody String signUpRequestData)throws Exception {
		ApiResponse response = null;
		try {
			SignUpRequest signUpRequest = new ObjectMapper().readValue(signUpRequestData, SignUpRequest.class);
			if (userRepository.existsByUsername(signUpRequest.getUsername())) 
				return new ApiResponse(false, "Username is already taken!");
			if (userRepository.existsByEmail(signUpRequest.getEmail())) 
				return new ApiResponse(false, "Email Address already in use!");
			if (userRepository.existsBymobileNumber(Long.valueOf(signUpRequest.getPhoneNumber())))
				return new ApiResponse(false, "Mobile Number already in use!");
			User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(), 
					Long.valueOf(signUpRequest.getPhoneNumber()), new Date(), new Date(), null, 'N');
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new AppException("User Role not set."));
			user.setRoles(Collections.singleton(userRole));
		    userRepository.save(user);
			response = new ApiResponse(true, "User registered successfully");
		} catch (Exception exception) {
            response = new ApiResponse(false,exception.getMessage());
            throw exception;
		}
		return response;
	}
    @PostMapping("/facebook")
	public ResponseEntity<?> facebookLogin(@RequestBody String data)throws Exception {
    	String jwt = null;
		try {
			FacebookLoginRequest loginRequest = new ObjectMapper().readValue(data, FacebookLoginRequest.class);
			if (!userRepository.existsByEmail(loginRequest.getEmail())) {
				User user = new User(loginRequest.getUsername(), loginRequest.getUserID(), loginRequest.getEmail(), loginRequest.getUserID(), 
						new Date(), new Date(), null, 'N',loginRequest.getDeviceId());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new AppException("User Role not set."));
				user.setRoles(Collections.singleton(userRole));
			    user = userRepository.save(user);
			}else {
				Optional<User> findByEmail = userRepository.findByEmail(loginRequest.getEmail());
				if(findByEmail.isPresent())
					saveDeviceId(findByEmail.get(),loginRequest.getDeviceId());
			}
			jwt=authentication(loginRequest.getEmail(), loginRequest.getUserID());
		} catch (Exception exception) {
                 throw exception;
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/google")
	public ResponseEntity<?> googleLogin(@RequestBody String data) throws Exception{
		String jwt = null;
		try {
			GoogleLoginRequest loginRequest = new ObjectMapper().readValue(data, GoogleLoginRequest.class);
			
			if (!userRepository.existsByEmail(loginRequest.getEmail())) {
				
				User user = new User(loginRequest.getDisplayName(), loginRequest.getUserId(), loginRequest.getEmail(), loginRequest.getUserId(), 
						new Date(), new Date(), null, 'N',loginRequest.getDeviceId());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new AppException("User Role not set."));
				user.setRoles(Collections.singleton(userRole));
			    user = userRepository.save(user);
			}else {
				Optional<User> findByEmail = userRepository.findByEmail(loginRequest.getEmail());
				if(findByEmail.isPresent())
					saveDeviceId(findByEmail.get(),loginRequest.getDeviceId());
			}
			jwt=authentication(loginRequest.getEmail(), loginRequest.getUserId());
		} catch (Exception exception) {
                 throw exception;
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	private String authentication(String userName, String Password) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, Password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return tokenProvider.generateToken(authentication);
		}catch (Exception e) {
			throw e;
		}
	}

	private void saveDeviceId(User user, String deviceId) {
		try {
			user.setDeviceId(deviceId);
			userRepository.save(user);
		}catch (Exception e) {
			throw e;
		}
	}
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody String data)throws Exception {
		ApiResponse apiResponse=null;
		try {
			Optional<User> findByEmail = userRepository.findByEmail(data.replaceAll("^\"|\"$", ""));
			if(findByEmail.isPresent()) {
				User user = findByEmail.get();
				String randomPassword = JString.getRandomString();
				user.setPassword(passwordEncoder.encode(randomPassword));
				user=userRepository.save(user);
				user.setPassword(randomPassword);
				String emailTemplateForWelcome = mailerServices.getEmailTemplateForWelcome(user);
				mailerServices.sendHTMLMail(new EmailDetailsDTO("Forgot Password",new String[]{user.getEmail()},emailTemplateForWelcome));
				apiResponse=new ApiResponse(true, "Email Sent Successfully");
			}else {
				apiResponse=new ApiResponse(false, "Email doesn't exists our database");
			}
		} catch (Exception e) {
			apiResponse=new ApiResponse(false, "Failed");
			throw e;
		}
		return ResponseEntity.ok(apiResponse);
	}
}
