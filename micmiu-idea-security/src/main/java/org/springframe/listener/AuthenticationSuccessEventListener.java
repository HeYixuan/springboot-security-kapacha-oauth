package org.springframe.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆成功监听
 *
 * @author HeYixuan
 * @create 2017-04-27 10:15
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                event.getAuthentication().getDetails();
        /*String username = event.getAuthentication().getName();
        System.err.println("用户"+ username + "登录成功重置登录次数");*/

        System.err.println("IP地址: " + auth.getRemoteAddress());
        System.err.println("SESSION: " + auth.getSessionId());
        System.err.println("用户: " + event.getAuthentication().getName());
    }

    public static final String getIpAddr(final HttpServletRequest request) throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if ( StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if ( StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if ( StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString) ) {
            ipString = request.getHeader("HTTP_CLIENT_IP");
        }
        if ( StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString) ) {
            ipString = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }
        return ipString;
    }
}
