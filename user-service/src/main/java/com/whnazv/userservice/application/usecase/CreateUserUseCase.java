package com.whnazv.userservice.application.usecase;

import com.whnazv.userservice.domain.model.User;

public interface CreateUserUseCase {
    User execute(User user);
}
