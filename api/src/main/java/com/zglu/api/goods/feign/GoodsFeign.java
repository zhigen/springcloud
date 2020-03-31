package com.zglu.api.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zglu
 */
@FeignClient("gateway")
@RequestMapping("/second")
@ApiIgnore
public interface GoodsFeign {

    /**
     * 获取用户信息
     *
     * @param token token
     * @param id    用户id
     * @return 用户信息
     */
    @GetMapping("/goods/{id}")
    String get(@RequestHeader("token") String token, @PathVariable("id") String id);

}