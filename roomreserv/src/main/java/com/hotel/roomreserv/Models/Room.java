package com.hotel.roomreserv.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    private int Number;
    private  int Places;
    private  double PricePerDay;
    private  boolean IsFree;
    private String LinkMainPhoto;
    private String LinkAllPhoto;
    private String HotelId;

    @Transient
    private Hotel Hotel;
}
