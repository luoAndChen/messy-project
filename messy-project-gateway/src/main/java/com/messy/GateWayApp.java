package com.messy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjr
 * @date 2020/5/20 17:04
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class,args);
    }
}
