package com.messy.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * 开启session共享
 * blog: https://blog.51cto.com/13005375 
 * code: https://gitee.com/owenwangwen/open-capacity-platform
*/
@EnableRedisHttpSession
public class SessionConfig {

}
