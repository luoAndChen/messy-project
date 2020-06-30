package com.messy.uaa.client.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 查询应用绑定的资源权限
 */
@Mapper
@SuppressWarnings("all")
public interface SysClientDao {

	 
	
	@Select("select * from oauth_client_details t where t.client_id = #{clientId}")
	Map getClient(String clientId);
	
 
}
