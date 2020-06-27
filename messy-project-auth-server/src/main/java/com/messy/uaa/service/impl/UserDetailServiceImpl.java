package com.messy.uaa.service.impl;

import com.messy.common.auth.details.LoginAppUser;
import com.messy.common.util.StringUtil;
import com.messy.uaa.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings("all")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient  userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAppUser loginAppUser = null;

      
        if (StringUtil.isPhone(username)){
        	  //手机
            loginAppUser = userFeignClient.findByMobile(username);
        }else {
            // 用户名
            loginAppUser = userFeignClient.findByUsername(username);   			  
        }

        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }

        return loginAppUser;
    }


     
}
