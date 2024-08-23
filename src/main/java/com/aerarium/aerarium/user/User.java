package com.aerarium.aerarium.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "user")
public class User implements UserDetails {

    @Id
    @Column(name = "usDocument")
    Long document;
    @Column(name = "usTypeDocument")
    String typeDocument;
    @Column(name = "usName")
    String name;
    @Column(name = "usLastName")
    String lastName;
    @Column(name = "usPhone")
    String phone;
    @Column(name = "usEmail")
    String userEmail;
    @Column(name = "usPassword")
    String password;
    @Column(name = "usState")
    Boolean state;
    @Column(name = "usRole")
    Role role;
    @Column(name="createdAt")
    LocalDateTime  created;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return "Este es el user name";
    }


    @Override
    public boolean isAccountNonExpired() {
        //Colocando true en el retorno lo que hace es que retorne todo el token sin retricciones
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
