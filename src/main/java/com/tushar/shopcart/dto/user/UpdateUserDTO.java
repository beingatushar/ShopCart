package com.tushar.shopcart.dto.user;

import com.tushar.shopcart.enums.user.UserRole;
import com.tushar.shopcart.enums.user.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",
            message = "Username can only contain letters, numbers, and underscores")
    private String username;

    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain: 1 digit, 1 lowercase, 1 uppercase, 1 special character, no spaces")
    private String password;

    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain only digits (0-9)")
    private String phoneNumber;

    private UserStatus status;

    private Set<UserRole> roles;
}