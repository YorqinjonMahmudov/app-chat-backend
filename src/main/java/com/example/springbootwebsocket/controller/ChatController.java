package com.example.springbootwebsocket.controller;

import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/get-chats")
    public ApiResult<?> getAllChats(@RequestHeader("username") String username) {
        return chatService.getAllChats(username);
    }




}
