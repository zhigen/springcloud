package com.zglu.api.other;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zglu
 */
@FeignClient(name = "chp", url = "https://chp.shadiao.app/api.php")
public interface ChpApi {

    /**
     * 获取彩虹屁
     *
     * @return 彩虹屁
     */
    @GetMapping
    String chp();

}