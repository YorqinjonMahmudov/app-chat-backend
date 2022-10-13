package com.example.springbootwebsocket.controller;

import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.payload.MessageDTO;
import com.example.springbootwebsocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send-message")
    public ApiResult<?> sendMessage(@RequestHeader("username") String username,
                                    @RequestBody MessageDTO messageDTO) {
        return messageService.sendMessage(username, messageDTO);
    }

    @GetMapping("/get-messages")
    public ApiResult<?> getAllChats(@RequestHeader("username") String username,
                                    @RequestParam(required = false) Long chatId) {
        return messageService.getAllMessages(username, chatId);
    }



}
