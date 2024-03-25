package com.spring.security.utils;

import com.spring.security.entities.UserEntity;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.models.requesters.UserRequester;

public class Mapper {

    public static UserEntity toUserEntity(UserRequester userRequester){
        return UserEntity.builder()
                .firstName(userRequester.getFirstName())
                .lastName(userRequester.getLastName())
                .email(userRequester.getEmail())
                .password(userRequester.getPassword())
                .build();
    }

    public static UserPresenter toUserPresenter(UserEntity userEntity){
        return UserPresenter.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }
}
