package com.zglu.second.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@RequestMapping("/second")
public class GoodsController {

    @GetMapping("/goods/{id}")
    public String get(@PathVariable String id) {
        return "goods：" + id;
    }
}
