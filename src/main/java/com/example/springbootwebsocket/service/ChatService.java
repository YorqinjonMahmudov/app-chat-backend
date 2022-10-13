package com.example.springbootwebsocket.service;

import com.example.springbootwebsocket.entity.Chat;
import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.payload.ChatDTO;
import com.example.springbootwebsocket.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public ApiResult<?> getAllChats(String username) {
        List<Chat> allChats = chatRepository.findAllByFromUser_UsernameEqualsOrToUser_UsernameEquals(username, username);

        List<ChatDTO> chatDTOS = mapChatsToCHatDTOs(allChats, username);
        return ApiResult.successResponse(chatDTOS);
    }


    private List<ChatDTO> mapChatsToCHatDTOs(List<Chat> allChats, String username) {
        return allChats.stream()
                .map(chat -> ChatDTO.mapChatToChatDTO(chat, username))
                .collect(Collectors.toList());
    }


}
