package com.zhqy.springcloud.eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3></h3>
 *
 * @author wangshuaijing
 * @version 1.0.0
 * @date 2021/4/15 10:52
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "hello word";
    }

}
