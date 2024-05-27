package com.hotel.roomreserv.Repository.Abstract;

import com.hotel.roomreserv.Models.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IReservationRepository extends MongoRepository<Reservation, String> {
}
