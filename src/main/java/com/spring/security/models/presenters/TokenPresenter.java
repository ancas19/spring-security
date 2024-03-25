package com.spring.security.models.presenters;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TokenPresenter {
    private String token;
    private String mensaje;
}
