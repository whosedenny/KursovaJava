package com.hotel.roomreserv.Repository;

import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Repository.Abstract.IRoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

@DataMongoTest
public class RoomRepositoryTest {

    @Autowired
    private IRoomRepository roomRepository;

    @Test
    public void RoomRepository_AddRoom_ReturnAddedRoom() {
        Room room = new Room();
        room.setNumber(101);
        room.setPlaces(2);
        room.setPricePerDay(200.0);
        room.setIsFree(true);

        Room savedRoom = roomRepository.save(room);

        String roomId = savedRoom.getId();

        Optional<Room> retrievedRoomOptional = roomRepository.findById(roomId);

        Assertions.assertTrue(retrievedRoomOptional.isPresent());
        Room retrievedRoom = retrievedRoomOptional.get();
        Assertions.assertEquals(101, retrievedRoom.getNumber());
        Assertions.assertEquals(2, retrievedRoom.getPlaces());
        Assertions.assertEquals(200.0, retrievedRoom.getPricePerDay());
        Assertions.assertTrue(retrievedRoom.isIsFree());
    }

    @Test
    public void RoomRepository_FindAllRooms_ReturnAllRooms() {
        Room room1 = new Room();
        room1.setNumber(101);
        room1.setPlaces(2);
        room1.setPricePerDay(200.0);
        room1.setIsFree(true);
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setNumber(102);
        room2.setPlaces(3);
        room2.setPricePerDay(250.0);
        room2.setIsFree(false);
        roomRepository.save(room2);

        List<Room> allRooms = roomRepository.findAll();

        Assertions.assertEquals(5, allRooms.size());
    }

    @Test
    public void RoomRepository_DeleteRoomById_RoomNotExists() {
        Room room = new Room();
        room.setNumber(101);
        room.setPlaces(2);
        room.setPricePerDay(200.0);
        room.setIsFree(true);
        Room savedRoom = roomRepository.save(room);

        roomRepository.deleteById(savedRoom.getId());

        Optional<Room> deletedRoom = roomRepository.findById(savedRoom.getId());
        Assertions.assertFalse(deletedRoom.isPresent());
    }

    @Test
    public void RoomRepository_UpdateRoom_ReturnUpdatedRoom() {
        Room room = new Room();
        room.setNumber(101);
        room.setPlaces(2);
        room.setPricePerDay(200.0);
        room.setIsFree(true);
        Room savedRoom = roomRepository.save(room);

        savedRoom.setPricePerDay(250.0);
        Room updatedRoom = roomRepository.save(savedRoom);

        Optional<Room> retrievedRoomOptional = roomRepository.findById(updatedRoom.getId());

        Assertions.assertTrue(retrievedRoomOptional.isPresent());
        Room retrievedRoom = retrievedRoomOptional.get();
        Assertions.assertEquals(250.0, retrievedRoom.getPricePerDay());
    }
}
