package com.hotel.roomreserv.Repository.Abstract;

import com.hotel.roomreserv.Models.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRoomRepository  extends MongoRepository<Room, String> {

}
