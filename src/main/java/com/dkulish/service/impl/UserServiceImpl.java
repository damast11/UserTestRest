package com.dkulish.service.impl;

import com.dkulish.data.User;
import com.dkulish.mapper.UserMapper;
import com.dkulish.repository.UserRepository;
import com.dkulish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        log.info("Start mapping process...");
        var userDb = userMapper.toEntity(user);
        log.info("Mapped user : {}", userDb);
        var savedUser = userRepository.save(userDb);
        log.info("Saved user : {}", savedUser);
    }

    @Override
    public User findUserById(String id) {
        var userFromDb = userRepository.findUserByUserId(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id : " + id));
        log.info("User from DB : {}", userFromDb);

        return userMapper.toDto(userFromDb);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        userRepository.deleteUserByUserId(id);
        log.info("User with id {} , deleted", id);
    }

    @Override
    public void updateUser(User user) {
        var userByUserId = userRepository.findUserByUserId(String.valueOf(user.getUserId()))
            .orElseThrow(() -> new IllegalArgumentException("User not found with id : " + user.getUserId()));

        userByUserId.setUserName(user.getUserName());
        userByUserId.setUserAge(user.getUserAge());
        userRepository.save(userByUserId);
    }

}
