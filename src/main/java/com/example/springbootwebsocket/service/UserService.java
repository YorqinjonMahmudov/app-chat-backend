package com.example.springbootwebsocket.service;

import com.example.springbootwebsocket.entity.User;
import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.payload.UserDTO;
import com.example.springbootwebsocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResult<?> register(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent())
            return ApiResult.successResponse(optionalUser.get());

        User user= new User();
        user.setUsername(username);

        userRepository.save(user);
        return ApiResult.successResponse(user);
    }

    public ApiResult<?> getAllUsers(String username) {

        List<User> users = userRepository.findAllByUsernameStartingWith(username);

        List<UserDTO> userDTOS = users.stream().map(UserDTO::mapUserToUserDTO).collect(Collectors.toList());
        return ApiResult.successResponse(userDTOS);

    }
}
