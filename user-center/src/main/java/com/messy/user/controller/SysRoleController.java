package com.messy.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messy.common.exception.controller.ControllerException;
import com.messy.common.exception.service.ServiceException;
import com.messy.common.model.SysRole;
import com.messy.common.web.PageResult;
import com.messy.common.web.Result;
import com.messy.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
* 角色管理
 */

@RestController
@Api(tags = "ROLE API")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	private ObjectMapper objectMapper = new ObjectMapper();


//	<!-- -->
	/**
	 * 后台管理查询角色
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@PreAuthorize("hasAuthority('role:get/roles')")
	@ApiOperation(value = "后台管理查询角色")
	@GetMapping("/roles")
	public PageResult<SysRole> findRoles(@RequestParam Map<String, Object> params) throws ControllerException {
		try {
//			BizLog.info("角色列表", LogEntry.builder().clazz(this.getClass().getName()).method("findRoles").msg("hello").path("/roles").build());
			return sysRoleService.findRoles(params);
		} catch (ServiceException e) {
			 throw new ControllerException(e);
		}
	}

	/**
	 * 角色新增或者更新
	 * @param sysRole
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAnyAuthority('role:post/roles','role:put/roles')")
	@PostMapping("/roles/saveOrUpdate")
	public Result saveOrUpdate(@RequestBody SysRole sysRole) throws ControllerException {
		try {
			return sysRoleService.saveOrUpdate(sysRole);
		} catch (ServiceException e) {
			 throw new ControllerException(e);
		}
	}

	/**
	 * 后台管理删除角色
	 * delete /role/1
	 * @param id
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('role:delete/roles/{id}')")
	@ApiOperation(value = "后台管理删除角色")
	@DeleteMapping("/roles/{id}")
	public Result deleteRole(@PathVariable Long id) throws ControllerException {
		try {
			if (id == 1L){
				return Result.failed("管理员不可以删除");
			}
			sysRoleService.deleteRole(id);
			return Result.succeed("操作成功");
		}catch (Exception e){
			 throw new ControllerException(e);
		}
	}

}
