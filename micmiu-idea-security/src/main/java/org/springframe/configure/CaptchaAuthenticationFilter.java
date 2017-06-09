package org.springframe.configure;

import org.apache.commons.lang3.StringUtils;
import org.springframe.common.constans.GlobalConstant;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤器
 * @author: HeYixuan
 * @create: 2017-06-05 17:59
 */
@Component
public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String kaptcha = request.getParameter("kaptcha");
        String captcha = (String) request.getSession().getAttribute(GlobalConstant.KEY_CAPTCHA);
        if(StringUtils.isBlank(captcha) || (! captcha.equalsIgnoreCase(kaptcha))) {
            throw new AuthenticationServiceException("图片验证码错误！");
            //throw new CaptchaException("captcha Invalid");
        }
        return super.attemptAuthentication(request, response);
    }
}
