package com.spring.security.models.presenters;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserPresenter {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
