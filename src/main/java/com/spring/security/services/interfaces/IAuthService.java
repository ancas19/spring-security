package com.spring.security.services.interfaces;

import com.nimbusds.jose.JOSEException;
import com.spring.security.exceptions.BadRequestException;
import com.spring.security.exceptions.ForbiddenException;
import com.spring.security.exceptions.NotFoundException;
import com.spring.security.models.presenters.TokenPresenter;
import com.spring.security.models.presenters.UserPresenter;
import com.spring.security.models.requesters.LoginRequester;
import com.spring.security.models.requesters.UserRequester;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IAuthService {

    TokenPresenter login(LoginRequester loginRequester) throws NotFoundException, ForbiddenException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException;
    UserPresenter register(UserRequester userRequester) throws BadRequestException;
}
