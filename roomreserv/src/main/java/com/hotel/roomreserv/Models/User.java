package com.hotel.roomreserv.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@Document(collection = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;
    private String passportNumber;
    private LocalDate dateBirthday;

    public List<String> getRoles() {
        return isAdmin ? Collections.singletonList("ROLE_ADMIN") : Collections.singletonList("ROLE_USER");
    }
}
