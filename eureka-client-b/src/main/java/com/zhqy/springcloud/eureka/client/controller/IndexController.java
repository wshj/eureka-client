package com.zhqy.springcloud.eureka.client.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h3></h3>
 *
 * @author wangshuaijing
 * @version 1.0.0
 * @date 2021/4/15 10:56
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/eureka")
    public String index2eureka() {
        // 以循环的方式获取指定服务的实例，只获取状态是 InstanceInfo.InstanceStatus.UP 的实例
        // 参数virtualHostname是 spring.application.name 的值
        // 这种写法，eureka会随机从服务中取一个服务
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("eureka-client-a", false);
        return instance.getHomePageUrl();
    }

    @GetMapping("/springcloud")
    public String index2springcloud() {
        // discoveryClient.getInstances 的方式，可以从指定服务获取实例
        // 参数就是配置的 spring.application.name 的值
        List<ServiceInstance> list = discoveryClient.getInstances("eureka-client-a");
        if (list != null && list.size() > 0) {
            return list.get(0).getUri().toString();
        }
        return "no service";
    }

    @GetMapping("/eurekaVip")
    public String index2eurekaVip() {
        // 指定服务获取实例列表，不区分大小写
        // 参数就是配置的 spring.application.name 的值
        List<InstanceInfo> instanceList = eurekaClient.getInstancesByVipAddress("EUREKA-CLIENT-A", false);
        return "共有 client-a 服务：" + instanceList.size() + " 个";
    }

    @GetMapping("/loadBalance")
    public String index2loadBalance() {
        // 负责均衡的方式获取服务，只获取状态是 InstanceInfo.InstanceStatus.UP 的实例
        // 参数就是配置的 spring.application.name 的值
        return loadBalancerClient.choose("eureka-client-a").getHost();
    }
}
