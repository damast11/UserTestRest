package com.dkulish.controller;

import com.dkulish.data.User;
import com.dkulish.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable String id) {
        log.info("Start find user by id : {}", id);

        return userService.findUserById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam String id) {
        log.info("Start deleting user by id : {}", id);

        userService.deleteUserById(id);
    }

    @PatchMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        log.info("Start updating user by user id : {}", user.getUserId());

        userService.updateUser(user);
    }
}
