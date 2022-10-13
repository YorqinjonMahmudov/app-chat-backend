package com.example.springbootwebsocket.controller;

import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public ApiResult<?> register(@RequestHeader("username") String username) {
        return userService.register(username);
    }


    @GetMapping("/search/{username}")
    public ApiResult<?> searchUsers(
            @PathVariable String username) {
        return userService.getAllUsers(username);
    }
}
