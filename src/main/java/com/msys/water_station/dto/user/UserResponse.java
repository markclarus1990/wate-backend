package com.msys.water_station.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @NotBlank
    private String fullname;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private boolean active;
    @NotBlank
    private String roleName;
}
