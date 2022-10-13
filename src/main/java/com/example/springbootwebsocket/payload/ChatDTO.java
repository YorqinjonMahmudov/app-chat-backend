package com.example.springbootwebsocket.payload;

import com.example.springbootwebsocket.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {

    private Long id;

    private String username;

    public static ChatDTO mapChatToChatDTO(Chat chat,String username){


        return new ChatDTO(chat.getId(),
                username.equals(chat.getToUser().getUsername())?
                        chat.getFromUser().getUsername():chat.getToUser().getUsername());
    }
}
