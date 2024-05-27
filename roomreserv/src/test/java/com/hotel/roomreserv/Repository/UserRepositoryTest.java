package com.hotel.roomreserv.Repository;

import com.hotel.roomreserv.Models.User;
import com.hotel.roomreserv.Repository.Abstract.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void UserRepository_AddUser_ReturnAddedUser() {
        User user = new User();
        user.setName("Тестове ім'я");
        user.setPassword("тест");
        user.setEmail("тест@тест.com");

        User savedUser = userRepository.save(user);

        Optional<User> retrievedUserOptional = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(retrievedUserOptional.isPresent());
        User retrievedUser = retrievedUserOptional.get();
        Assertions.assertEquals("Тестове ім'я", retrievedUser.getName());
        Assertions.assertEquals("тест", retrievedUser.getPassword());
        Assertions.assertEquals("тест@тест.com", retrievedUser.getEmail());
    }

    @Test
    public void UserRepository_DeleteUserById_UserNotExists() {
        // Создаем объект пользователя
        User user = new User();
        user.setName("Тестове ім'я");
        user.setPassword("тест");
        user.setEmail("тест@тест.com");

        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        Optional<User> deletedUserOptional = userRepository.findById(savedUser.getId());
        Assertions.assertTrue(deletedUserOptional.isEmpty());
    }

    @Test
    public void UserRepository_UpdateUser_ReturnUpdatedUser() {
        User user = new User();
        user.setName("Тестове ім'я");
        user.setPassword("тест");
        user.setEmail("тест@тест.com");

        User savedUser = userRepository.save(user);

        savedUser.setName("Оновлене ім'я");
        User updatedUser = userRepository.save(savedUser);

        Optional<User> retrievedUserOptional = userRepository.findById(updatedUser.getId());

        Assertions.assertTrue(retrievedUserOptional.isPresent());
        User retrievedUser = retrievedUserOptional.get();
        Assertions.assertEquals("Оновлене ім'я", retrievedUser.getName());
    }

    @Test
    public void UserRepository_FindUserByEmail_ReturnUser() {
        User user = new User();
        user.setName("Тестове ім'я");
        user.setPassword("тест");
        user.setEmail("тест@тест.com");

        userRepository.save(user);

        User retrievedUser = userRepository.findByEmail("тест@тест.com");

        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals("Тестове ім'я", retrievedUser.getName());
        Assertions.assertEquals("тест", retrievedUser.getPassword());
    }
}
