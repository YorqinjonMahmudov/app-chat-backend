package com.example.springbootwebsocket.payload;

import com.example.springbootwebsocket.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String text;


    private Long chatId;

    private String toUser;
    private String createdBy;

    public static MessageDTO mapMessageToMessageDTO(Message message, String toUser) {
        return new MessageDTO(message.getText(), message.getChat().getId(), toUser, message.getCreatedBy());
    }

}
