package com.example.vantazii.chat.enity;

import lombok.Data;
import java.util.UUID;




@Data
public  class  SendMessageDto{

    private  String to;
    private  String message;

}