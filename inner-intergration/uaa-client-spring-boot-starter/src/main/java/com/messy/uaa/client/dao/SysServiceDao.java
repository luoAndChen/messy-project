package com.messy.uaa.client.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询应用绑定的资源权限
 */
@Mapper
@SuppressWarnings("all")
public interface SysServiceDao {

 

	@Select("select p.* from sys_service p inner join sys_client_service rp on p.id = rp.serviceId where rp.clientId = #{clientId} order by p.sort")
	List<Map> listByClientId(Long clientId);
 
 
}
