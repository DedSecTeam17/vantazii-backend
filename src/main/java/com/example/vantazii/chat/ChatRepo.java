package com.example.vantazii.chat;

import com.example.vantazii.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
interface ChatRepo extends JpaRepository<Chat,UUID> {

    List<Chat> findChatBySenderInAndReciverInOrderByCreatedAtAsc(List<Customer> senderIn,List<Customer> reciverIn);
}

