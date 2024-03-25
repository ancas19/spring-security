package com.spring.security.services.interfaces;

import com.spring.security.entities.UserEntity;
import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.UserPresenter;

import java.util.List;

public interface IUserService {
    List<UserPresenter> findAll() throws NotFoundException;
}
