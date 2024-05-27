package com.hotel.roomreserv.Controllers;

import com.hotel.roomreserv.Models.User;
import com.hotel.roomreserv.Services.Abstract.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController
{
    private IUserService service;

    @Operation(
            description = "Returning all users in dataBase",
            summary = "Get all users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/all-users")
    public List<User> GetAllUsers() {
        return service.GetAllUsers();
    }
    @Operation(
            description = "Creating a new user in dataBase",
            summary = "Create new user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/add-user")
    public void AddUser(@RequestBody User user) {
        service.AddUser(user);
    }
    @Operation(
            description = "Updating information about user in dataBase",
            summary = "Update information about user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PutMapping("/update-user")
    public void UpdateUser(@RequestBody User user) {
        service.UpdateUser(user);
    }
    @Operation(
            description = "Deleting a user from dataBase",
            summary = "Delete user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/remove-user")
    public void RemoveUser(@RequestBody User user) {
        service.RemoveUser(user);
    }
}
