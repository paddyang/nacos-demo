package com.paddyang.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

@Component
public class DynamicRouteServiceImplByNacos {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    @Autowired
    private Environment env;

    public DynamicRouteServiceImplByNacos() {
        try {
            Properties  properties = PropertiesLoaderUtils.loadAllProperties("conf.properties");
            String serverAddr = properties.getProperty("nacos.config.server-addr");
            String dataId = properties.getProperty("nacos.config.dataId");
            String group = properties.getProperty("nacos.config.group");
            long timeoutMs = Long.parseLong(properties.getProperty("nacos.config.timeoutMs"));
            dynamicRouteByNacosListener(dataId,group,serverAddr,timeoutMs);
        } catch (IOException e) {
            throw new RuntimeException("获取配置文件---conf.properties---失败");
        }

    }
    /**
     * 监听Nacos Server下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group,String serverAddr,long timeoutMs){
        try {
            ConfigService configService= NacosFactory.createConfigService(serverAddr);
            String content = configService.getConfig(dataId, group, timeoutMs);
            System.out.println(content);
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> definitionList= JSON.parseArray(configInfo, RouteDefinition.class);
                    for (RouteDefinition definition:definitionList){
                        String update = dynamicRouteService.update(definition);
                        System.out.println(update);
                    }
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            //todo 提醒:异常自行处理此处省略
            e.printStackTrace();
        }
    }
}
