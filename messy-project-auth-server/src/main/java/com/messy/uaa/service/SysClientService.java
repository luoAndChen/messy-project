package com.messy.uaa.service;

import com.messy.common.web.PageResult;
import com.messy.common.web.Result;
import com.messy.uaa.dto.SysClientDto;
import com.messy.uaa.model.SysClient;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface SysClientService {

	
	SysClient getById(Long id) ;
	 

    Result saveOrUpdate(SysClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<SysClient> listRoles(Map<String, Object> params);
    
    List<SysClient> findList(Map<String, Object> params) ;
    

	Result updateEnabled(Map<String, Object> params);
    
}
