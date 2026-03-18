package com.whnazv.userservice.application.usecase;

import com.whnazv.userservice.application.usecase.CreateUserUseCase;
import com.whnazv.userservice.domain.model.User;
import com.whnazv.userservice.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository repository;

    @Override
    public User execute(User user) {
        return repository.save(user);
    }
}
