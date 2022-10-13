package com.example.springbootwebsocket.repository;


import com.example.springbootwebsocket.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChat_Id(Long chat_id);
}
