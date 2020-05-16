package com.paddyang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paddy
 */
@SpringBootApplication
@EnableDiscoveryClient
//@NacosConfigurationProperties(dataId = "gateway",autoRefreshed = true)
@RestController
public class GatewayServerApplication {

//    @NacosValue(value = "${my.conf:}", autoRefreshed = true)
//    private String conf;

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

//    @RequestMapping("/hi")
//    public String hi(){
//        return conf;
//    }
}
