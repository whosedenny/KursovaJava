package com.hotel.roomreserv.Services.Abstract;

import com.hotel.roomreserv.Models.User;
import java.util.List;

public interface IUserService {
    public List<User> GetAllUsers();

    public  void  AddUser(User user);

    public void UpdateUser(User user);

    public  void RemoveUser(User user);
    public  User GetUserById(String id);
}
