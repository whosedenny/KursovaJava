package com.hotel.roomreserv.Repository.Abstract;



import com.hotel.roomreserv.Models.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IHotelRepository extends MongoRepository<Hotel, String>
{

}
