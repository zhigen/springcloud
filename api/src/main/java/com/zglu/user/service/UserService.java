package com.zglu.user.service;

import com.zglu.user.feign.UserFeign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zglu
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserFeign userFeign;

    public String get(String id) {
        return userFeign.get(id);
    }
}
