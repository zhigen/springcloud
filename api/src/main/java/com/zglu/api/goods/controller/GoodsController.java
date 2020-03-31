package com.zglu.api.goods.controller;

import com.zglu.api.goods.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/goods/{id}")
    public String get(@RequestHeader String token, @PathVariable String id) {
        return goodsService.get(token, id);
    }
}
