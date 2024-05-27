package com.hotel.roomreserv.Services;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Repository.Abstract.IRoomRepository;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import com.hotel.roomreserv.Services.Abstract.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RoomService implements IRoomService {
    private IRoomRepository reposRoom;
    private IHotelService serviceHotel;

    @Override
    public List<Room> GetAllRooms() {
        return reposRoom.findAll().stream()
                .peek(room -> {
                    Hotel hotel = serviceHotel.GetHotelById(room.getHotelId());
                    room.setHotel(hotel);
                })
                .collect(Collectors.toList());
    }
    @Override
    public void AddRoom(Room room) {
        reposRoom.insert(room);
    }
    @Override
    public void UpdateRoom(Room room) {
        reposRoom.save(room);
    }
    @Override
    public void RemoveRoom(Room room) {
        reposRoom.delete(room);
    }
    @Override
    public Room GetRoomById(String id) {
        return reposRoom.findById(id).orElse(null);
    }
    @Override
    public List<Room> GetRoomsByHotel(Hotel hotel) {
        return GetAllRooms().stream()
                .filter(room -> room.getHotelId().equals(hotel.getId()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Room> GetFreeRooms() {
        return GetAllRooms().stream()
                .filter(Room::isIsFree)
                .collect(Collectors.toList());
    }
}
