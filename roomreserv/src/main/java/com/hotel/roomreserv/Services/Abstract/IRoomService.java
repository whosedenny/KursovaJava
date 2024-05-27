package com.hotel.roomreserv.Services.Abstract;
import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Models.Room;
import java.util.List;

public interface IRoomService {

    public List<Room> GetAllRooms();

    public  void  AddRoom(Room room);

    public void UpdateRoom(Room room);

    public  void RemoveRoom(Room room);
    public  Room GetRoomById(String id);
    public  List<Room> GetRoomsByHotel(Hotel hotel);
    public  List<Room> GetFreeRooms();
}
