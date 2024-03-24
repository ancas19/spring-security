package com.spring.security.models.dtos;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResponseDTO implements Serializable {
    private int numOfErrors;
    private String message;
}
