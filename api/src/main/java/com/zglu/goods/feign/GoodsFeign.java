package com.zglu.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zglu
 */
@FeignClient("gateway")
@RequestMapping("/goods")
public interface GoodsFeign {

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/goods/{id}?token=1")
    String get(@PathVariable("id") String id);

}