package com.hotel.roomreserv.Models;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "Reservation")
public class Reservation {
    @Id
    private String id;
    private String UserId;
    private String RoomId;
    private LocalDate DateStart;
    private LocalDate DateEnd;
    @Transient
    private User User;
    @Transient
    private  Room Room;
}
