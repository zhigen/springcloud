package com.zglu.app.goods.service;

import com.zglu.app.goods.feign.GoodsFeign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zglu
 */
@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsFeign goodsFeign;

    public String get(String token, String id) {
        return goodsFeign.get(token, id);
    }
}
