package com.dkulish.controller;

import com.dkulish.data.User;
import com.dkulish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        log.info("Start saving user : {}", user);
        userService.saveUser(user);
    }
}
