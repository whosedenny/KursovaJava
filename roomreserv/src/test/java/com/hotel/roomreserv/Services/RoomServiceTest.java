package com.hotel.roomreserv.Services;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Repository.Abstract.IRoomRepository;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RoomServiceTest {

    @Mock
    private IRoomRepository roomRepository;

    @Mock
    private IHotelService hotelService;

    @InjectMocks
    private RoomService roomService;

    private Room room1;
    private Room room2;
    private List<Room> roomList;
    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        room1 = new Room("1", 101, 2, 150.0, true, "посилання1", "посиланняНаВсе1", "1", null);
        room2 = new Room("2", 102, 2, 250.0, false, "посилання2", "посиланняНаВсе2", "2", null);

        roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);

        hotel = new Hotel();
        hotel.setId("1");
    }

    @Test
    public void testGetAllRooms() {
        when(roomRepository.findAll()).thenReturn(roomList);
        when(hotelService.GetHotelById(anyString())).thenReturn(hotel);

        List<Room> result = roomService.GetAllRooms();

        assertEquals(roomList.size(), result.size());
        assertEquals(roomList, result);
        verify(roomRepository, times(1)).findAll();
        verify(hotelService, times(2)).GetHotelById(anyString());
    }

    @Test
    public void testAddRoom() {
        roomService.AddRoom(room1);

        verify(roomRepository, times(1)).insert(room1);
    }

    @Test
    public void testUpdateRoom() {
        roomService.UpdateRoom(room1);

        verify(roomRepository, times(1)).save(room1);
    }

    @Test
    public void testRemoveRoom() {
        roomService.RemoveRoom(room1);

        verify(roomRepository, times(1)).delete(room1);
    }

    @Test
    public void testGetRoomById() {
        when(roomRepository.findById("1")).thenReturn(Optional.of(room1));
        when(roomRepository.findById("3")).thenReturn(Optional.empty());

        Room result1 = roomService.GetRoomById("1");
        Room result2 = roomService.GetRoomById("3");

        assertEquals(room1, result1);
        assertNull(result2);
        verify(roomRepository, times(1)).findById("1");
        verify(roomRepository, times(1)).findById("3");
    }

    @Test
    public void testGetRoomsByHotel() {
        when(roomRepository.findAll()).thenReturn(roomList);
        when(hotelService.GetHotelById("1")).thenReturn(hotel);

        List<Room> result = roomService.GetRoomsByHotel(hotel);

        assertEquals(1, result.size());
        assertEquals(room1, result.get(0));
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void testGetFreeRooms() {
        when(roomRepository.findAll()).thenReturn(roomList);

        List<Room> result = roomService.GetFreeRooms();

        assertEquals(1, result.size());
        assertEquals(room1, result.get(0));
        verify(roomRepository, times(1)).findAll();
    }
}
