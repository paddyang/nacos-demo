package com.paddyang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.WebFilter;

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

  /*  @Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!CorsUtils.isCorsRequest(request)) {
                return chain.filter(exchange);
            }
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET,OPTIONS,DELETE,PUT");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "content-type");
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3628800");
            return chain.filter(exchange);
        };
    }*/
}
