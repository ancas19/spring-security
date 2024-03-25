package com.spring.security.models.requesters;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequester {
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\da-zA-Z]).{8,16}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number.")
    private String password;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
}
