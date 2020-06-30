package com.messy;

import com.messy.common.feign.GlobalFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @author chenjr
 * @date 2020/6/17 16:21
 */
@EnableDiscoveryClient
@SpringBootApplication
@Import(UAAServerConfig.class)
@EnableFeignClients(defaultConfiguration= GlobalFeignConfig.class)
public class AuthServerApp {
    public static void main(String[] args) {
//		固定端口启动
 		SpringApplication.run(AuthServerApp.class, args);

        //随机端口启动
        /*SpringApplication app = new SpringApplication(AuthServerApp.class);
        app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
        app.run(args);*/

    }
}
