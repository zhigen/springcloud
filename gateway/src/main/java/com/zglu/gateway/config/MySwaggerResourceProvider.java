package com.zglu.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zglu
 */
@Configuration
@AllArgsConstructor
public class MySwaggerResourceProvider implements SwaggerResourcesProvider {
    /**
     * swagger2默认的url后缀
     */
    public static final String SWAGGER2URL = "/v2/api-docs";
    /**
     * 网关路由
     */
    private final RouteLocator routeLocator;

    @Override
    public List<SwaggerResource> get() {
        // 获取所有可用的serviceId
        List<String> routeHosts = new ArrayList<>();
        routeLocator.getRoutes().filter(m -> m.getUri().getHost() != null)
                .subscribe(m -> routeHosts.add(m.getUri().getHost()));
        // 根据serviceId生成swaggerResource
        List<SwaggerResource> resources = new ArrayList<>();
        routeHosts.stream().distinct().forEach(m -> {
            // 拼接url，格式为/serviceId/v2/api-docs，当网关调用这个接口时，会自动通过serviceId转发到对应的主机
            String url = "/" + m + SWAGGER2URL;
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setLocation(url);
            swaggerResource.setName(m);
            resources.add(swaggerResource);
        });
        return resources;
    }
}
