package org.springframe.listener;

import org.springframe.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    private static RedisTemplate<String, String> redisTemplate;
    private int attempts = 0;
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getName();
        attempts++;
        redisTemplate.opsForValue().set(username, String.valueOf(attempts), 10L, TimeUnit.MINUTES);
        int lockAttempts = Integer.parseInt(redisTemplate.opsForValue().get(username));
        if (lockAttempts>=3){
            System.err.println("用户"+ username + "锁定");
            systemUserService.updateAttempts(username);
        }
        System.err.println("用户"+ username + "登录失败次数+1");


    }
}
