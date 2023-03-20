package com.dkulish.controller;

import com.dkulish.data.User;
import com.dkulish.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController testInstance;

    @Test
    void shouldSaveUser() {
        var user = createUser();

        testInstance.saveUser(user);

        verify(userService).saveUser(user);
    }

    private User createUser() {
        return User.builder()
            .userId(1)
            .userAge(5)
            .userName("Vasya")
            .build();
    }
}
