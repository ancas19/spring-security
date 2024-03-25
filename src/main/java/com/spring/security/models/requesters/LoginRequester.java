package com.spring.security.models.requesters;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginDTORequester implements Serializable {
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
