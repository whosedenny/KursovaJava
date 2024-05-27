package com.hotel.roomreserv.Services;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Repository.Abstract.IHotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.*;

public class HotelServiceTest {

    @Mock
    private IHotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private Hotel hotel1;
    private Hotel hotel2;
    private List<Hotel> hotelList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hotel1 = new Hotel();
        hotel1.setId("1");
        hotel1.setName("Готель 1");
        hotel1.setCity("Місто 1");
        hotel1.setStars(4);

        hotel2 = new Hotel();
        hotel2.setId("2");
        hotel2.setName("Готель 2");
        hotel2.setCity("Місто 2");
        hotel2.setStars(5);

        hotelList = Arrays.asList(hotel1, hotel2);
    }
    @Test
    public void testGetAllHotels(){
        when(hotelRepository.findAll()).thenReturn(hotelList);

        List<Hotel> actualHotels = hotelService.GetAllHotels();
        assertEquals(2, actualHotels.size());
        verify(hotelRepository, times(1)).findAll();
    }
    @Test
    public void testAddHotel(){
        hotelService.AddHotel(hotel1);
        verify(hotelRepository, times(1)).insert(hotel1);
    }
    @Test
    public void testUpdateHotel(){
        hotelService.UpdateHotel(hotel1);
        verify(hotelRepository, times(1)).save(hotel1);
    }
    @Test
    public void testRemoveHotel(){
        hotelService.RemoveHotel(hotel1);
        verify(hotelRepository, times(1)).delete(hotel1);
    }
    @Test
    public void testGetHotelById(){
        when(hotelRepository.findById("1")).thenReturn(Optional.of(hotel1));

        Hotel actualHotel = hotelService.GetHotelById("1");
        assertEquals(hotel1, actualHotel);
        verify(hotelRepository, times(1)).findById("1");
    }
    @Test
    public void testGetHotelById_NotFound(){
        when(hotelRepository.findById("3")).thenReturn(Optional.empty());

        Hotel actualHotel = hotelService.GetHotelById("3");
        assertNull(actualHotel);
        verify(hotelRepository, times(1)).findById("3");
    }
    @Test
    public void testGetHotelsByCity(){
        when(hotelRepository.findAll()).thenReturn(hotelList);

        List<Hotel> actualHotels = hotelService.GetHotelsByCity("Місто 1");
        assertEquals(1, actualHotels.size());
        assertEquals(hotel1, actualHotels.get(0));
    }
    @Test
    public void testGetHotelsByStar(){
        when(hotelRepository.findAll()).thenReturn(hotelList);

        List<Hotel> actualHotels = hotelService.GetHotelsByStar(5);
        assertEquals(1, actualHotels.size());
        assertEquals(hotel2, actualHotels.get(0));
    }
    @Test
    public void testGetHotelByName(){
        when(hotelRepository.findAll()).thenReturn(hotelList);

        Hotel actualHotel = hotelService.GetHotelByName("Готель 1");
        assertEquals(hotel1, actualHotel);
    }
    @Test
    public void testGetHotelByName_NotFound(){
        when(hotelRepository.findAll()).thenReturn(hotelList);

        Hotel actualHotel = hotelService.GetHotelByName("Готель 213213");
        assertNull(actualHotel);
    }
}
