package com.hotel.roomreserv.Services.Abstract;

import com.hotel.roomreserv.Models.Reservation;
import com.hotel.roomreserv.Models.Room;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IReservationService {
    public List<Reservation> GetAllReservations();

    public  void  AddReservation(Reservation reservation);


    public void UpdateReservation(Reservation hotel);

    public  void RemoveReservation(@RequestBody Reservation reservation);

    public  Reservation GetReservationByRoom(Room room);
}
