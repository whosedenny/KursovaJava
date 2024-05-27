package com.hotel.roomreserv.Services;

import com.hotel.roomreserv.Models.User;
import com.hotel.roomreserv.Repository.Abstract.IUserRepository;
import com.hotel.roomreserv.Services.Abstract.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private IUserRepository repos;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> GetAllUsers() {
        return repos.findAll();
    }

    @Override
    public void AddUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repos.insert(user);
    }

    @Override
    public void UpdateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        repos.save(user);
    }

    @Override
    public void RemoveUser(User user) {
        repos.delete(user);
    }

    @Override
    public User GetUserById(String id) {
        return repos.findById(id).orElse(null);
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = GetAllUsers().stream().filter(u -> u.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}
