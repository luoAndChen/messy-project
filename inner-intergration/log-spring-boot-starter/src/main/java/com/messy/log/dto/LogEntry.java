package com.messy.log.dto;

import com.github.structlog4j.IToLog;
import com.messy.common.auth.details.LoginAppUser;
import com.messy.common.util.SysUserUtil;
import com.messy.common.util.TokenUtil;
import com.messy.log.util.TraceUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * 业务日志
 */
@Data
@Builder
public class LogEntry implements IToLog {
   
    private String transId;
    private String path ;
    private String clazz ;
    private String method ;
    private String token ;
    private String username ;
    private String msg ;
    private String error ;
  
    
    @Override
    public Object[] toLog() {
        return new Object[] {
                "transId",  Optional.ofNullable(TraceUtil.getTrace()).orElse(""),
                "path",Optional.ofNullable(path).orElse(""),
                "clazz",Optional.ofNullable(clazz).orElse(""),
                "method",Optional.ofNullable(method).orElse(""),
                "token" , Optional.ofNullable(TokenUtil.getToken()).orElse("") ,
                "username", Optional.ofNullable(SysUserUtil.getLoginAppUser()).orElse(new LoginAppUser()).getUsername(),
                "msg" , Optional.ofNullable(msg).orElse(""),
                "error",Optional.ofNullable(error).orElse("")
                
        };
    }
}