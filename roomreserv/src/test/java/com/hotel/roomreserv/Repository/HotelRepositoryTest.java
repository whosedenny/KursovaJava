package com.hotel.roomreserv.Repository;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Repository.Abstract.IHotelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

@DataMongoTest
public class HotelRepositoryTest {

    @Autowired
    private IHotelRepository hotelRepository;

    @Test
    public void HotelRepository_AddHotel_ReturnAddedHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Тестова назва");
        hotel.setAddress("Тестова адреса");
        hotel.setStars(4);
        hotel.setCity("Тестовий місто");
        hotel.setCountry("Тестова країна");

        Hotel savedHotel = hotelRepository.save(hotel);

        String hotelId = savedHotel.getId();

        Optional<Hotel> retrievedHotelOptional = hotelRepository.findById(hotelId);

        Assertions.assertTrue(retrievedHotelOptional.isPresent());
        Hotel retrievedHotel = retrievedHotelOptional.get();
        Assertions.assertEquals("Тестова назва", retrievedHotel.getName());
        Assertions.assertEquals("Тестова адреса", retrievedHotel.getAddress());
        Assertions.assertEquals(4, retrievedHotel.getStars());
        Assertions.assertEquals("Тестовий місто", retrievedHotel.getCity());
        Assertions.assertEquals("Тестова країна", retrievedHotel.getCountry());
    }

    @Test
    public void HotelRepository_DeleteHotelById_HotelNotExists() {
        Hotel hotel = new Hotel();
        hotel.setName("Тестова назва");
        hotel.setAddress("Тестова адреса");
        Hotel savedHotel = hotelRepository.save(hotel);

        hotelRepository.deleteById(savedHotel.getId());

        Optional<Hotel> deletedHotel = hotelRepository.findById(savedHotel.getId());
        Assertions.assertFalse(deletedHotel.isPresent());
    }

    @Test
    public void HotelRepository_UpdateHotel_ReturnUpdatedHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Тестова назва");
        hotel.setAddress("Тестова адреса");
        Hotel savedHotel = hotelRepository.save(hotel);

        savedHotel.setName("Оновлена назва");
        savedHotel.setAddress("Оновлена адреса");
        Hotel updatedHotel = hotelRepository.save(savedHotel);

        Optional<Hotel> retrievedHotelOptional = hotelRepository.findById(updatedHotel.getId());

        Assertions.assertTrue(retrievedHotelOptional.isPresent());
        Hotel retrievedHotel = retrievedHotelOptional.get();
        Assertions.assertEquals("Оновлена назва", retrievedHotel.getName());
        Assertions.assertEquals("Оновлена адреса", retrievedHotel.getAddress());
    }

    @Test
    public void HotelRepository_FindAllHotels_ReturnAllHotels() {
        Hotel hotel1 = new Hotel();
        hotel1.setName("Тестова назва 1");
        hotel1.setAddress("Тестова адреса 1");

        Hotel hotel2 = new Hotel();
        hotel2.setName("Тестова назва 2");
        hotel2.setAddress("Тестова адреса 2");

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);

        List<Hotel> hotels = hotelRepository.findAll();
        Assertions.assertEquals(5, hotels.size());
        Assertions.assertTrue(hotels.stream().anyMatch(hotel -> hotel.getName().equals("Тестова назва 1")));
        Assertions.assertTrue(hotels.stream().anyMatch(hotel -> hotel.getName().equals("Тестова назва 2")));
    }
}
