package com.zhqy.springcloud.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <h3>调用客户端a的服务</h3>
 *
 * @author wangshuaijing
 * @version 1.0.0
 * @date 2021/4/15 14:49
 */
@RestController
@RequestMapping("/remote")
public class RemoteController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello() {
        ServiceInstance choose = loadBalancerClient.choose("eureka-client-a");
        if (null == choose) {
            return "error";
        }

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(choose.getUri() + "/hello", String.class);
        return String.format("%s - %s", choose.getUri().toString(), response);
    }
}
