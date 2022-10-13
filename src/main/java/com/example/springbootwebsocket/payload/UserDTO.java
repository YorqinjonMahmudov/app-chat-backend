package com.example.springbootwebsocket.payload;

import com.example.springbootwebsocket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    public static UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
