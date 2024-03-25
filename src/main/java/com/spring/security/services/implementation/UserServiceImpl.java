package com.spring.security.services.implementation;

import com.spring.security.entities.UserEntity;
import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.repositories.UserRepository;
import com.spring.security.services.interfaces.IUserService;
import com.spring.security.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    @Override
    public List<UserPresenter> findAll() throws NotFoundException {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("Users not found!");
        }
        return users.stream().map(user-> Mapper.toUserPresenter(user)).toList();
    }
}
