package com.hotel.roomreserv.Services;

import com.hotel.roomreserv.Models.Reservation;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Repository.Abstract.IReservationRepository;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import com.hotel.roomreserv.Services.Abstract.IReservationService;
import com.hotel.roomreserv.Services.Abstract.IRoomService;
import com.hotel.roomreserv.Services.Abstract.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private IReservationRepository repos;
    private IUserService userService;
    private IRoomService roomService;
    private IHotelService hotelService;

    @Override
    public List<Reservation> GetAllReservations() {
        var res = repos.findAll();
        for(Reservation reservation :res){
            var room = roomService.GetRoomById(reservation.getRoomId());
            if (reservation.getDateEnd().isBefore(LocalDate.now())){
                room.setIsFree(true);
                roomService.UpdateRoom(room);
            }

            reservation.setRoom(room);
            reservation.getRoom().setHotel(hotelService.GetHotelById(reservation.getRoom().getHotelId()));
            reservation.setUser(userService.GetUserById(reservation.getUserId()));
        }
        return res;
    }
    @Override
    public void AddReservation(Reservation reservation) {
        reservation.setDateStart(LocalDate.now());
        var room = roomService.GetRoomById(reservation.getRoomId());
        room.setIsFree(false);
        roomService.UpdateRoom(room);
        repos.insert(reservation);
    }
    @Override
    public void UpdateReservation(Reservation reservation) {
        repos.save(reservation);
    }
    @Override
    public void RemoveReservation(Reservation reservation) {
        repos.delete(reservation);
    }
    @Override
    public Reservation GetReservationByRoom(Room room) {
        return GetAllReservations().stream()
                .filter(reservation -> reservation.getRoom().getId().equals(room.getId()))
                .findFirst()
                .orElse(null);
    }
}
