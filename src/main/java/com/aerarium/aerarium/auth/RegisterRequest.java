package com.aerarium.aerarium.auth;


import com.aerarium.aerarium.user.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    Long document;
    String typeDocument;
    String name;
    String lastName;
    String phone;
    String userEmail;
    String password;
    Boolean state;
    Role role;
    LocalDateTime created;
}
