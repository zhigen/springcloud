package com.zglu.goods.service;

import com.zglu.goods.feign.GoodsFeign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zglu
 */
@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsFeign goodsFeign;

    public String get(String id) {
        return goodsFeign.get(id);
    }
}
