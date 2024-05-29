package com.hotel.roomreserv.Controllers;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Services.Abstract.IRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@AllArgsConstructor
@Tag(name = "Rooms")
public class RoomController {
    private IRoomService service;

    @Operation(
            description = "Returning all rooms in dataBase",
            summary = "Get all rooms",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/all-rooms")
    public List<Room> GetAllRooms() {
        return service.GetAllRooms();
    }
    @Operation(
            description = "Creating a new room in dataBase",
            summary = "Create new room",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/add-room")
    public void AddRoom(@RequestBody Room room) {
        service.AddRoom(room);
    }
    @Operation(
            description = "Updating information about room in dataBase",
            summary = "Update information in room",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/update-room")
    public void UpdateRoom(@RequestBody Room room) {
        service.UpdateRoom(room);
    }
    @Operation(
            description = "Deleting a room from dataBase",
            summary = "Delete room",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/remove-room")
    public void RemoveRoom(@RequestBody Room room) {
        service.RemoveRoom(room);
    }
    @Operation(
            description = "Searching rooms in dataBase by hotel",
            summary = "Search rooms by hotel",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/rooms-by-hotel")
    public List<Room> GetRoomsByHotel(@RequestBody Hotel hotel){
        return service.GetRoomsByHotel(hotel);
    }
    @Operation(
            description = "Searching free rooms in dataBase",
            summary = "Search free rooms",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/free-rooms")
    public List<Room> GetFreeRooms(){
        return service.GetFreeRooms();
    }
}
