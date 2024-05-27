package com.hotel.roomreserv.Services.Abstract;


import com.hotel.roomreserv.Models.Hotel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface IHotelService {

    public List<Hotel> GetAllHotels();

    public  void  AddHotel(Hotel hotel);

    public void UpdateHotel( Hotel hotel);

    public  void RemoveHotel(Hotel hotel);

    public  Hotel GetHotelByName(String Name);

    public Hotel GetHotelById(String id);

    public  List<Hotel> GetHotelsByCity(String city);

    public List<Hotel> GetHotelsByStar(int star);
}
