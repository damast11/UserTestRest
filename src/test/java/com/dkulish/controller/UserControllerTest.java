package com.dkulish.controller;

import com.dkulish.data.User;
import com.dkulish.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

    @Test
    void shouldDeleteUser() {
        var id = "1";

        testInstance.deleteUser(id);

        verify(userService).deleteUserById(id);
    }

    @Test
    void shouldGetUser() {
        var id = "1";
        var user = createUser();
        given(userService.findUserById(id)).willReturn(user);

        var actual = testInstance.getUserById(id);

        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldUpdateUser() {
        var user = createUser();

        testInstance.updateUser(user);

        verify(userService).updateUser(user);
    }

    private User createUser() {
        return User.builder()
            .userId(1)
            .userAge(5)
            .userName("Vasya")
            .build();
    }
}
