package com.hotel.roomreserv.Services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hotel.roomreserv.Models.Reservation;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Models.User;
import com.hotel.roomreserv.Repository.Abstract.IReservationRepository;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import com.hotel.roomreserv.Services.Abstract.IRoomService;
import com.hotel.roomreserv.Services.Abstract.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceTest {

    @Mock
    private IReservationRepository repos;

    @Mock
    private IUserService userService;

    @Mock
    private IRoomService roomService;

    @Mock
    private IHotelService hotelService;

    @InjectMocks
    private ReservationService service;

    private Reservation reservation1;
    private Reservation reservation2;
    private List<Reservation> reservationList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation1 = new Reservation();
        reservation1.setId("1");
        reservation1.setUserId("1");
        reservation1.setRoomId("1");
        reservation1.setDateStart(LocalDate.now());
        reservation1.setDateEnd(LocalDate.now().plusDays(1));

        reservation2 = new Reservation();
        reservation2.setId("2");
        reservation2.setUserId("2");
        reservation2.setRoomId("2");
        reservation2.setDateStart(LocalDate.now());
        reservation2.setDateEnd(LocalDate.now().plusDays(2));

        reservationList = new ArrayList<>();
        reservationList.add(reservation1);
        reservationList.add(reservation2);
    }

    @Test
    public void testGetAllReservations() {
        when(repos.findAll()).thenReturn(reservationList);
        when(roomService.GetRoomById(anyString())).thenReturn(new Room());
        when(userService.GetUserById(anyString())).thenReturn(new User());

        List<Reservation> result = service.GetAllReservations();

        assertEquals(2, result.size());
        verify(repos, times(1)).findAll();
        verify(roomService, times(2)).GetRoomById(anyString());
        verify(userService, times(2)).GetUserById(anyString());
    }

    @Test
    public void testAddReservation() {
        Room room = new Room();
        room.setId("1");
        room.setIsFree(true);

        when(roomService.GetRoomById(anyString())).thenReturn(room);

        service.AddReservation(reservation1);

        assertFalse(room.isIsFree());
        verify(roomService, times(1)).UpdateRoom(room);
        verify(repos, times(1)).insert(reservation1);
    }

    @Test
    public void testUpdateReservation() {
        service.UpdateReservation(reservation1);
        verify(repos, times(1)).save(reservation1);
    }

    @Test
    public void testRemoveReservation() {
        service.RemoveReservation(reservation1);
        verify(repos, times(1)).delete(reservation1);
    }

    @Test
    public void testGetReservationByRoom() {
        when(repos.findAll()).thenReturn(reservationList);

        Room room = new Room();
        room.setId("1");

        Reservation result = service.GetReservationByRoom(room);

        assertEquals(reservation1, result);
        verify(repos, times(1)).findAll();
    }
}
