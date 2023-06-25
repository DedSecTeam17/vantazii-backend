package com.example.vantazii.chat;

import com.example.vantazii.chat.enity.SendMessageDto;
import com.example.vantazii.chat.socket.PubnubHelper;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.security.auth.ApplicationUserDetailsService;
import com.example.vantazii.core.security.auth.facade.IAuthenticationFacade;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerRepo;
import com.example.vantazii.customer.CustomerService;
import com.pubnub.api.PubNubException;
import com.pubnub.api.models.consumer.PNPublishResult;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public  class ChatService {





    private ChatRepo chatRepo;
    private CustomerRepo customerRepo;
    private CustomerService customerService;
    private ApplicationUserDetailsService applicationUserDetailsService;

    private IAuthenticationFacade authenticationFacade;

    private PubnubHelper pubnubHelper;



    public List<Customer> allCustomers(){
        return  this.customerService.allCustomers();
    }


    public  Chat sendMessage(SendMessageDto sendMessageDto){
        Chat chat = new Chat();
        Customer reciver = customerService.findCustomerByPhoneNumber(sendMessageDto.getTo());
        Customer sender = getCustomer();
        chat.setReciver(reciver);
        chat.setMessage(sendMessageDto.getMessage());
        chat.setSender(sender);
        chat.setCreatedAt(LocalDateTime.now());

        String channelName = sender.getPhoneNumber()+"x"+reciver.getPhoneNumber();

        Chat savedChat =  chatRepo.save(chat);

        try {
            PNPublishResult result = pubnubHelper.sendMessage(chat,channelName);
            System.out.println(result.getTimetoken());
           return  chat;
        } catch (PubNubException e) {
            throw new ApiRequestException("Unable to send message through sockets", new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
        }
    }

    public  List<Chat> allMessages(String phoneNumber){
        List<Customer> customers = new ArrayList<>();
        customers.add(getCustomer());
        customers.add(customerService.findCustomerByPhoneNumber(phoneNumber));
        return  chatRepo.findChatBySenderInAndReciverInOrderByCreatedAtAsc(customers,customers);
    }

    private Customer getCustomer() {
        System.out.println(authenticationFacade.getAuthentication().getName());
        Optional<Customer> customer = customerRepo.findCustomerByPhoneNumber(authenticationFacade.getAuthentication().getName());

        if (customer.isPresent())
            return customer.get();
        else
            throw new ApiRequestException("Customer not found", new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
    }


}