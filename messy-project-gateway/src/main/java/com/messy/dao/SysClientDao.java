package com.messy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author 作者 owen 
 * @version 创建时间：2018年4月5日 下午19:52:21 类说明
 * 查询应用绑定的资源权限
 */
@Mapper
@SuppressWarnings("all")
public interface SysClientDao {

	 
	@Select("select * from oauth_client_details t where t.client_id = #{clientId}")
	Map getClient(String clientId);
 
}
