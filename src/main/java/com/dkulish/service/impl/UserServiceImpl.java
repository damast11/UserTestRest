package com.dkulish.service.impl;

import com.dkulish.data.User;
import com.dkulish.mapper.UserMapper;
import com.dkulish.repository.UserRepository;
import com.dkulish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
