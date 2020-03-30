package com.zglu.user.controller;

import com.zglu.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    public String get(@PathVariable String id) {
        return userService.get(id);
    }
}
