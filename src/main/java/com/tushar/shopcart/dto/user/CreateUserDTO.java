package com.tushar.shopcart.dto.user;

import com.tushar.shopcart.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CreateUserDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",
            message = "Username can only contain letters, numbers, and underscores")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain: 1 digit, 1 lowercase, 1 uppercase, 1 special character (@#$%^&+=), no spaces")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email cannot exceed {max} characters")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 10, message = "Phone number must be exactly {min} digits")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain only digits (0-9)")
    private String phoneNumber;


    private UserEntity.UserStatus status;
    private Set<UserEntity.UserRole> roles;

    // If you want to ensure default roles are set when none provided
    public Set<UserEntity.UserRole> getRoles() {
        return (roles == null || roles.isEmpty()) ?
                Set.of(UserEntity.UserRole.CUSTOMER) :
                roles;
    }

    public UserEntity.UserStatus getStatus() {
        return status == null ? UserEntity.UserStatus.ACTIVE : status;
    }
}