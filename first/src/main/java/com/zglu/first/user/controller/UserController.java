package com.zglu.first.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@RequestMapping("/first")
public class UserController {

    @GetMapping("/user/{id}")
    public String get(@PathVariable String id) {
        return "user：" + id;
    }
}
