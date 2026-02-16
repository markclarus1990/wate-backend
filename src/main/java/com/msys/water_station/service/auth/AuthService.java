package com.msys.water_station.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.msys.water_station.Model.Role;
import com.msys.water_station.Model.User;
import com.msys.water_station.dto.user.Register;
import com.msys.water_station.dto.user.UserResponse;
import com.msys.water_station.repo.RoleRepo;
import com.msys.water_station.repo.UserRepo;
import com.msys.water_station.service.jwt.JwtService;
import com.msys.water_station.util.UserMapper;

@Service
public class AuthService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public String verify(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));

            return jwtService.generateToken(authentication.getName());

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    // Create User
    // @Transactional
    public UserResponse Register(Register register) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Role role = roleRepo.findById(register.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userMapper.toEntity(register);
        System.out.println("Mapped User Entity: " + user);
        user.setRole(role);
        user.setPassword(encoder.encode(register.getPassword()));

        User savedUser = userRepo.save(user);
        return userMapper.toDTO(savedUser);
    }

}
