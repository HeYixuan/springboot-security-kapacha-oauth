package org.springframe.listener;

import org.springframe.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * 登陆失败监听
 *
 * @author HeYixuan
 * @create 2017-04-27 10:09
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>  {

    @Autowired
    private SystemUserService systemUserService;
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getName();
        System.err.println("用户"+ username + "登录失败次数+1");
        systemUserService.updateAttempts(username);

    }
}
