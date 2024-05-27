package com.hotel.roomreserv.Controllers;


import com.hotel.roomreserv.Models.Reservation;
import com.hotel.roomreserv.Models.Room;
import com.hotel.roomreserv.Services.Abstract.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
@Tag(name = "Reservation")
public class ReservationController {
    private IReservationService service;

    @Operation(
            description = "Returning all reservation in dataBase",
            summary = "Get all reservation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/all-reservations")
    public List<Reservation> GetAllReservations(){
        return  service.GetAllReservations();
    }
    @Operation(
            description = "Creating a new reservation in dataBase",
            summary = "Create reservation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/add-reservation")
    public  void  AddReservation(@RequestBody Reservation reservation){
        service.AddReservation(reservation);
    }
    @Operation(
            description = "Updating information about reservation in dataBase",
            summary = "Update reservation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/update-reservation")
    public void UpdateReservation(@RequestBody Reservation reservation){
        service.UpdateReservation(reservation);
    }
    @Operation(
            description = "Deleting a reservation from dataBase",
            summary = "Delete reservation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/remove-reservation")
    public  void RemoveReservation(@RequestBody Reservation reservation){
        service.RemoveReservation(reservation);
    }

    @Operation(
            description = "Searching reservation in dataBase by room",
            summary = "Get reservation by room",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )

    @PostMapping("/reservation-by-room")
    public ResponseEntity<Reservation> getReservationByRoom(@RequestBody Room room) {
        Reservation reservation = service.GetReservationByRoom(room);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
