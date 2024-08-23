package com.aerarium.aerarium.services;

import com.aerarium.aerarium.auth.AuthResponse;
import com.aerarium.aerarium.auth.LoginRequest;
import com.aerarium.aerarium.auth.RegisterRequest;
import com.aerarium.aerarium.jwt.JwtService;
import com.aerarium.aerarium.user.IUserRepository;
import com.aerarium.aerarium.user.Role;
import com.aerarium.aerarium.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getPassword()));
       UserDetails user=userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();
       String token=jwtService.getToken(user);
       return AuthResponse.builder()
               .token(token)
               .build();
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .document(request.getDocument())
                .typeDocument(request.getTypeDocument())
                .name(request.getName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .userEmail(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .state(true)
                .role(Role.USER)
                .created(LocalDateTime.now())
                .build();

        try{
            userRepository.save(user);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }


        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
