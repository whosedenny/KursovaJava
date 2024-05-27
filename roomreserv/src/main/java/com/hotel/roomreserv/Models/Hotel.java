package com.hotel.roomreserv.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Hotel")
public class Hotel {

    @Id
    private String id;
    private String Name;
    private String Address;
    private int Stars;
    private String City;
    private String Country;
    private String LinkMainPhoto;
}
