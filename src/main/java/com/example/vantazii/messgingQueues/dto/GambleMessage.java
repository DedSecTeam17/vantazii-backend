package com.example.vantazii.messgingQueues.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GambleMessage {
    private UUID matchID;
    private UUID gampleID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String expectedResult;


}
