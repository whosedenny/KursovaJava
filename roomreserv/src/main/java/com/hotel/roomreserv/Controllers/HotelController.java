package com.hotel.roomreserv.Controllers;

import com.hotel.roomreserv.Models.Hotel;
import com.hotel.roomreserv.Services.Abstract.IHotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.Descriptor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@Tag(name = "Hotels")
@AllArgsConstructor
public class HotelController {

    private IHotelService service;

    @Operation(
            description = "Returning all hotels in dataBase",
            summary = "Get all hotels",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/all-hotels")
    public List<Hotel> GetAllHotels(){
        return service.GetAllHotels();
    }
    @Operation(
            description = "Creating a new hotel in dataBase",
            summary = "Create new hotel",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/add-hotels")
    public  void  AddHotel(@RequestBody Hotel hotel){
        service.AddHotel(hotel);
    }
    @Operation(
            description = "Updating information about hotel in dataBase",
            summary = "Update information in hotel",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/update-hotel")
    public void UpdateHotel(@RequestBody Hotel hotel){
        service.UpdateHotel(hotel);
    }
    @Operation(
            description = "Deleting a hotel in dataBase",
            summary = "Delete hotel",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/remove-hotel")
    public  void RemoveHotel(@RequestBody Hotel hotel){
        service.RemoveHotel(hotel);
    }
    @Operation(
            description = "Searching hotel in dataBase by hotels name",
            summary = "Search hotel by name",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/hotel-by-name/{Name}")
    public  Hotel GetHotelByName(@PathVariable String Name){
        return service.GetHotelByName(Name);
    }
    @Operation(
            description = "Searching hotel in dataBase by hotels city",
            summary = "Search hotel by city",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/hotel-by-city/{city}")
    public List<Hotel> GetHotelsByCity(@PathVariable String city){
        return null;
    }
    @Operation(
            description = "Searching hotel in dataBase by hotels count stars",
            summary = "Search hotel by stars",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/hotels-by-star/{star}")
    public List<Hotel> GetHotelsOrderByStar(@PathVariable int star){
        return service.GetHotelsByStar(star);
    }
}
