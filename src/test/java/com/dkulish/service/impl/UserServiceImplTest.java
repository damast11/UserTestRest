package com.dkulish.service.impl;

import com.dkulish.data.User;
import com.dkulish.entity.UserDb;
import com.dkulish.mapper.UserMapper;
import com.dkulish.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String USER_NAME = "Vasya";
    public static final int USER_AGE = 5;
    public static final String USER_ID = "1";
    @Mock
    UserMapper userMapper;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl testInstance;

    @Test
    void shouldSaveUser() {
        var user = createUser();
        var userDb = createUserDb();
        given(userMapper.toEntity(user)).willReturn(userDb);

        testInstance.saveUser(user);

        verify(userRepository).save(userDb);
    }

    @Test
    void shouldFindUserById() {
        var userDb = createUserDb();
        var user = createUser();
        given(userRepository.findUserByUserId(USER_ID)).willReturn(Optional.ofNullable(userDb));
        given(userMapper.toDto(userDb)).willReturn(user);

        var actual = testInstance.findUserById(USER_ID);

        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldThrowException() {
        given(userRepository.findUserByUserId(USER_ID))
            .willThrow(new IllegalArgumentException("User not found with id : " + USER_ID));

        assertThatThrownBy(() -> testInstance.findUserById(USER_ID))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("User not found with id : " + USER_ID);
    }

    @Test
    void shouldDeleteUserById() {

        testInstance.deleteUserById(USER_ID);

        verify(userRepository).deleteUserByUserId(USER_ID);
    }

    @Test
    void shouldUpdateUser() {
        var user = createUser();
        var userDb = createUserDb();
        given(userRepository.findUserByUserId(USER_ID)).willReturn(Optional.ofNullable(userDb));

        testInstance.updateUser(user);

        verify(userRepository).save(Objects.requireNonNull(userDb));
    }

    private UserDb createUserDb() {
        return UserDb.builder()
            .userId(USER_ID)
            .userAge(USER_AGE)
            .userName(USER_NAME)
            .build();
    }

    private User createUser() {
        return User.builder()
            .userId(1)
            .userAge(USER_AGE)
            .userName(USER_NAME)
            .build();
    }
}
