package com.example.vantazii.chat;

import com.example.vantazii.DTO.GlobalResponseBody;
import com.example.vantazii.chat.enity.SendMessageDto;
import com.example.vantazii.core.security.auth.DTO.GenerateOtpDto;
import com.example.vantazii.core.security.auth.DTO.NewCustomerResponse;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.dto.CustomerDto;
import com.example.vantazii.role.enums.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/chat")
@AllArgsConstructor
class ChatController {




    private  ChatService chatService;

    @GetMapping(path = "/all")
    public List<Customer> allCustomers(){
        return  chatService.allCustomers();
    }

    @GetMapping(path = "/messages")
    public  ResponseEntity<?> chatMessages(){

        GlobalResponseBody<List<Chat>> globalResponseBody = new GlobalResponseBody<List<Chat>>();
        globalResponseBody.setData( chatService.allMessages("+249555555555"));
        globalResponseBody.setStatus(true);

        return new ResponseEntity(globalResponseBody,HttpStatus.OK);

    }





    @PostMapping(path = "send")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")

    public  ResponseEntity<?> sendMessage(@RequestBody SendMessageDto sendMessageDto){
        GlobalResponseBody<Chat> globalResponseBody = new GlobalResponseBody<Chat>();
        globalResponseBody.setData(chatService.sendMessage(sendMessageDto));
        globalResponseBody.setStatus(true);
        return new ResponseEntity(globalResponseBody,HttpStatus.OK);
    }








}

