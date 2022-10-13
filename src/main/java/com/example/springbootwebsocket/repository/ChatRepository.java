package com.example.springbootwebsocket.repository;


import com.example.springbootwebsocket.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByFromUser_username(String user_username);
    Optional<Chat> findByToUser_Username(String user_username);
    List<Chat> findAllByFromUser_UsernameEqualsOrToUser_UsernameEquals(String fromUserUsername, String toUserUsername);

    Optional<Chat> findByFromUser_UsernameEqualsAndToUser_UsernameEquals(String fromUser_username, String toUser_username);

    Optional<Chat> findByToUser_UsernameEqualsAndFromUser_UsernameEquals(String fromUser_username, String toUser_username);
//    List<Chat> findAllByFromUser_UsernameEqualsOrToUser_UsernameEquals(String fromUser_username, String toUser_username);
}
