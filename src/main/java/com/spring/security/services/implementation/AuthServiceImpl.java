package com.spring.security.services.implementation;

import com.nimbusds.jose.JOSEException;
import com.spring.security.entities.UserEntity;
import com.spring.security.exceptions.BadRequestException;
import com.spring.security.exceptions.ForbiddenException;
import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.TokenPresenter;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.models.requesters.LoginRequester;
import com.spring.security.models.requesters.UserRequester;
import com.spring.security.repositories.UserRepository;
import com.spring.security.services.interfaces.IAuthService;
import com.spring.security.services.interfaces.IJWTUtility;
import com.spring.security.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final IJWTUtility jwtUtility;
    private final PasswordEncoder encoder;

    @Override
    public TokenPresenter login(LoginRequester loginRequester) throws NotFoundException, ForbiddenException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        Optional<UserEntity> usuarioEncontrado = userRepository.findByEmail(loginRequester.getEmail());
        if(usuarioEncontrado.isEmpty()){
            throw new NotFoundException("User not registered!");
        }
        if(!verifyPassword(loginRequester.getPassword(),usuarioEncontrado.get().getPassword())){
            throw  new ForbiddenException("Invalid credentials!");
        }
        return TokenPresenter.builder()
                .token(jwtUtility.generateToken(usuarioEncontrado.get().getId()))
                .mensaje("Login successful!")
                .build();
    }

    @Override
    public UserPresenter register(UserRequester userRequester) throws BadRequestException {
        if(this.userRepository.existsByEmail(userRequester.getEmail())){
           throw new BadRequestException("User already registered!");
        }
        userRequester.setPassword(encoder.encode(userRequester.getPassword()));
        return Mapper.toUserPresenter(this.userRepository.save(Mapper.toUserEntity(userRequester)));
    }


    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        return encoder.matches(enteredPassword, storedPassword);
    }
}
