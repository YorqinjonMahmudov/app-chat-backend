package com.example.springbootwebsocket.service;

import com.example.springbootwebsocket.entity.Chat;
import com.example.springbootwebsocket.entity.Message;
import com.example.springbootwebsocket.entity.User;
import com.example.springbootwebsocket.exceptions.RestException;
import com.example.springbootwebsocket.payload.ApiResult;
import com.example.springbootwebsocket.payload.MessageDTO;
import com.example.springbootwebsocket.repository.ChatRepository;
import com.example.springbootwebsocket.repository.MessageRepository;
import com.example.springbootwebsocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ApiResult<?> sendMessage(String username, MessageDTO messageDTO) {
        Chat chat;

        Optional<Chat> fromUserChat = chatRepository
                .findByFromUser_UsernameEqualsAndToUser_UsernameEquals(username, messageDTO.getToUser());

        Optional<Chat> toUserChat = chatRepository
                .findByToUser_UsernameEqualsAndFromUser_UsernameEquals(messageDTO.getToUser(), username);
        if (fromUserChat.isPresent())
            chat = fromUserChat.get();
        else if (toUserChat.isPresent())
            chat = toUserChat.get();
        else {
            chat = new Chat();
            User fromUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> RestException.restThrow("USER NOT FOUND", HttpStatus.NOT_FOUND));

            User toUser = userRepository.findByUsername(messageDTO.getToUser())
                    .orElseThrow(() -> RestException.restThrow("USER NOT FOUND", HttpStatus.NOT_FOUND));

            chat.setToUser(toUser);
            chat.setFromUser(fromUser);
            chat = chatRepository.save(chat);
        }

        Message message = new Message();
        message.setChat(chat);
        message.setText(messageDTO.getText());
        message.setCreatedBy(username);
        messageRepository.save(message);

        MessageDTO messageDTO1 = MessageDTO.mapMessageToMessageDTO(messageRepository.save(message), messageDTO.getToUser());
        return ApiResult.successResponse(messageDTO1);
    }


    public ApiResult<?> getAllMessages(String username, Long chatId) {

        Chat chat;
        if (chatId != null)
            chat = chatRepository.findById(chatId).orElseThrow(
                    () -> RestException.restThrow("chat not found by id",HttpStatus.NOT_FOUND));
        else {
            try {
                chat = chatRepository
                        .findByFromUser_username(username)
                        .orElse(chatRepository.findByToUser_Username(username)
                                .orElseThrow());
            } catch (IncorrectResultSizeDataAccessException e) {
                throw RestException.restThrow("oka siz bilan oldin chatlashganmizku idni bering",HttpStatus.CONFLICT);
            }
        }
        List<Message> allMessages = messageRepository.findAllByChat_Id(chat.getId());

        List<MessageDTO> messageDTOS = mapMessagesToMessageDTOs(allMessages, username);

        return ApiResult.successResponse(messageDTOS);
    }

    private List<MessageDTO> mapMessagesToMessageDTOs(List<Message> allMessages, String username) {
        return allMessages.stream().map(
                message -> MessageDTO.mapMessageToMessageDTO(message, username)).collect(Collectors.toList());
    }

}
