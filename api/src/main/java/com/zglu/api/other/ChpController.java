package com.zglu.api.other;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class ChpController {
    private final ChpApi chpApi;

    @GetMapping("/chp")
    public String map() {
        return chpApi.chp();
    }
}