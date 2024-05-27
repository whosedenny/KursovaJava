package com.hotel.roomreserv.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hotel.roomreserv.Models.User;
import com.hotel.roomreserv.Repository.Abstract.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private IUserRepository repos;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId("1");
        user.setName("Василь Василь");
        user.setEmail("vasul@gmail.com");
        user.setPassword("password");
        user.setIsAdmin(false);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(repos.findAll()).thenReturn(userList);

        List<User> result = service.GetAllUsers();

        assertEquals(userList.size(), result.size());
        assertEquals(userList, result);
        verify(repos, times(1)).findAll();
    }

    @Test
    public void testAddUser() {
        service.AddUser(user);

        verify(repos, times(1)).insert(user);
    }

    @Test
    public void testUpdateUser() {
        service.UpdateUser(user);

        verify(repos, times(1)).save(user);
    }

    @Test
    public void testRemoveUser() {
        service.RemoveUser(user);

        verify(repos, times(1)).delete(user);
    }

    @Test
    public void testGetUserById() {
        when(repos.findById("1")).thenReturn(Optional.of(user));
        when(repos.findById("2")).thenReturn(Optional.empty());

        User result1 = service.GetUserById("1");
        User result2 = service.GetUserById("2");

        assertEquals(user, result1);
        assertNull(result2);
        verify(repos, times(1)).findById("1");
        verify(repos, times(1)).findById("2");
    }

    @Test
    public void testLoadUserByUsername() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(repos.findAll()).thenReturn(userList);

        UserDetails result = service.loadUserByUsername("vasul@gmail.com");

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertTrue(result.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        List<User> userList = new ArrayList<>();

        when(repos.findAll()).thenReturn(userList);

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("vasul12312@gmail.com");
        });

        assertEquals("User not found with email: vasul12312@gmail.com", exception.getMessage());
    }
}
