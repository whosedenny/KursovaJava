package com.hotel.roomreserv.Repository.Abstract;

import com.hotel.roomreserv.Models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
