package com.zglu.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zglu
 */
@FeignClient("gateway")
@RequestMapping("/user")
public interface UserFeign {

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/user/{id}?token=1")
    String get(@PathVariable("id") String id);

}