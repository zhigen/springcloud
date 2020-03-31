package com.zglu.goods.controller;

import com.zglu.goods.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/goods/{id}")
    public String get(@PathVariable String id) {
        return goodsService.get(id);
    }
}
