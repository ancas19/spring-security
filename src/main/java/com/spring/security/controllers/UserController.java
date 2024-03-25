package com.spring.security.controllers;

import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserPresenter>> findAll() throws NotFoundException, NotFoundException {
        return ResponseEntity.ok(userService.findAll());
    }
}
