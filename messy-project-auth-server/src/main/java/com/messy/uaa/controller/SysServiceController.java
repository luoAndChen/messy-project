package com.messy.uaa.controller;

import com.messy.common.web.PageResult;
import com.messy.common.web.Result;
import com.messy.uaa.dto.SysClientDto;
import com.messy.uaa.model.SysService;
import com.messy.uaa.service.SysServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: [gitgeek]
 * @Date: [2018-08-23 16:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Slf4j
@RestController
@Api(tags = "SERVICE API")
@RequestMapping("/services")
@SuppressWarnings("all")
public class SysServiceController {

    @Autowired
    private SysServiceService sysServiceService;

    /**
     * 查询所有服务
     * @return
     */
    @ApiOperation(value = "查询所有服务")
    @GetMapping("/findAlls")
    @PreAuthorize("hasAuthority('service:get/service/findAlls')")
    public PageResult<SysService> findAlls() {
        List<SysService> list = sysServiceService.findAll();

        return PageResult.<SysService>builder().data(list).code(0).count((long)list.size()).build() ;
    }

    /**
     * 获取服务以及顶级服务
     * @return
     */
    @ApiOperation(value = "获取服务以及顶级服务")
    @GetMapping("/findOnes")
    @PreAuthorize("hasAuthority('service:get/service/findOnes')")
    public PageResult<SysService> findOnes(){
        List<SysService> list = sysServiceService.findOnes();
        return PageResult.<SysService>builder().data(list).code(0).count((long)list.size()).build() ;
    }

    /**
     * 删除服务
     * @param id
     * @return
     */
    
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除服务")
    @PreAuthorize("hasAuthority('service:delete/service/{id}')")
    public Result delete(@PathVariable Long id){
        try {
            sysServiceService.delete(id);

        }catch (Exception ex){
        	log.error("SysServiceController->delete:{}" ,ex.getMessage());
            return Result.failed("操作失败");
        }
        return Result.succeed("操作成功");
    }

    
    @ApiOperation(value = "新增服务")
    @PostMapping("/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('service:post/saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SysService service) {
        try{
            if (service.getId() != null){
                sysServiceService.update(service);
            }else {
                sysServiceService.save(service);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
        	log.error("SysServiceController->saveOrUpdate:{}" ,ex.getMessage());
            return Result.failed("操作失败");
        }
    }

    @ApiOperation(value = "根据clientId获取对应的服务")
    @GetMapping("/{clientId}/services")
    public List<Map<String, Object>> findServicesByclientId(@PathVariable Long clientId) {
        Set<Long> clientIds = new HashSet<Long>();
        
        //初始化应用
        clientIds.add(clientId);
        
        List<SysService> clientService = sysServiceService.findByClient(clientIds);
        List<SysService> allService = sysServiceService.findAll();
        List<Map<String, Object>> authTrees = new ArrayList<>();

        Map<Long,SysService> clientServiceMap = clientService.stream().collect(Collectors.toMap(SysService::getId,SysService->SysService));

        for (SysService sysService: allService) {
            Map<String, Object> authTree = new HashMap<>();
            authTree.put("id",sysService.getId());
            authTree.put("name",sysService.getName());
            authTree.put("pId",sysService.getParentId());
            authTree.put("open",true);
            authTree.put("checked", false);
            if (clientServiceMap.get(sysService.getId())!=null){
                authTree.put("checked", true);
            }
            authTrees.add(authTree);
        }

        return  authTrees;
    }

    @PostMapping("/granted")
    public Result setMenuToClient(@RequestBody SysClientDto clientDto) {
        sysServiceService.setMenuToClient(clientDto.getId(), clientDto.getServiceIds());

        return Result.succeed("操作成功");
    }















}
