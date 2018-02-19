package com.nowcoder.wenda.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }

}
