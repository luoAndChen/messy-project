package com.messy.uaa.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.messy.common.constant.UaaConstant;
import com.messy.uaa.client.dao.SysClientDao;
import com.messy.uaa.client.service.SysClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 查询应用绑定的资源权限
 */
@Slf4j
@SuppressWarnings("all")
@Service("sysClientService")
public class SysClientServiceImpl implements SysClientService {

 
    @Autowired
    private RedisTemplate<String,Object> redisTemplate ;
    @Autowired
    private SysClientDao sysClientDao ;
    
	public Map getClient(String clientId){
		// 先从redis获取
		Map client = null  ;
        String value = (String) redisTemplate.boundHashOps(UaaConstant.CACHE_CLIENT_KEY).get(clientId);
        // 没有从数据库获取
        if (StringUtils.isBlank(value)) {
        	client = sysClientDao.getClient(clientId) ;
        } else {
        	client = JSONObject.parseObject(value, Map.class);
        }
        return client ;
	}
	
	 
}
