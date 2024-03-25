package com.spring.security.controllers;

import com.nimbusds.jose.JOSEException;
import com.spring.security.exceptions.BadRequestException;
import com.spring.security.exceptions.ForbiddenException;
import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.TokenPresenter;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.models.requesters.LoginRequester;
import com.spring.security.models.requesters.UserRequester;
import com.spring.security.services.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenPresenter> login(@Valid @RequestBody LoginRequester loginRequester) throws ForbiddenException, NotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        return ResponseEntity.ok(authService.login(loginRequester));
    }

    @PostMapping("/register")
    public ResponseEntity<UserPresenter> register(@Valid @RequestBody UserRequester userRequester) throws BadRequestException {
        return new ResponseEntity(authService.register(userRequester), HttpStatus.CREATED);
    }
}
