package com.store2door.api.service;

import com.store2door.api.dto.UserDTO;
import com.store2door.api.payload.ApiResponse;
import com.store2door.api.payload.SignUpRequest;

public interface UserService {
    
	public UserDTO loadUserData(long userId)throws Exception;
	public ApiResponse changePassword(SignUpRequest signUpRequest) throws Exception;
}
