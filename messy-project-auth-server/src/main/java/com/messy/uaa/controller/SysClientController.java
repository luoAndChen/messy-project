package com.messy.uaa.controller;

import com.google.common.collect.Maps;
import com.messy.common.web.PageResult;
import com.messy.common.web.Result;
import com.messy.uaa.dto.SysClientDto;
import com.messy.uaa.model.SysClient;
import com.messy.uaa.service.SysClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色相关接口
 *
 * @author owen 624191343@qq.com
 */
@RestController
@Api(tags = "CLIENT API")
@RequestMapping("/clients")
@SuppressWarnings("all")
public class SysClientController {

    @Autowired
    private SysClientService sysClientService;


    @GetMapping
    @ApiOperation(value = "应用列表")
    @PreAuthorize("hasAuthority('client:get/clients')")
    public PageResult<SysClient> listRoles(@RequestParam Map<String, Object> params) {
        return sysClientService.listRoles(params) ;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取应用")
    @PreAuthorize("hasAuthority('client:get/clients/{id}')")
    public SysClient get(@PathVariable Long id) {
        return sysClientService.getById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有应用")
    @PreAuthorize("hasAnyAuthority('client:get/clients')")
    public List<SysClient> roles() {
        return sysClientService.findList(Maps.newHashMap());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除应用")
    @PreAuthorize("hasAuthority('client:delete/clients')")
    public void delete(@PathVariable Long id) {
    	sysClientService.deleteClient(id);
    }

	@PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者修改应用")
    @PreAuthorize("hasAuthority('client:post/clients')")
    public Result saveOrUpdate(@RequestBody SysClientDto clientDto){
        return  sysClientService.saveOrUpdate(clientDto);
    }
    @PutMapping("/updateEnabled")
    @ApiOperation(value = "修改状态")
    @PreAuthorize("hasAuthority('client:post/clients')")
    public Result updateEnabled(@RequestBody Map<String, Object> params){
        return  sysClientService.updateEnabled(params);
    }
    
}
