package org.springframe.configure;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.constans.GlobalConstant;
import org.springframe.common.wxpay.exception.CaptchaException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final static Logger logger =  LoggerFactory.getLogger(CaptchaAuthenticationFilter.class);

//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String kaptcha = request.getParameter("kaptcha");
//        String captcha = (String) request.getSession().getAttribute(GlobalConstant.KEY_CAPTCHA);
//        if(StringUtils.isBlank(captcha) || (! captcha.equalsIgnoreCase(kaptcha))) {
//            //throw new AuthenticationServiceException("图片验证码错误！");
//            throw new CaptchaException("captcha Invalid");
//        }
//        return super.attemptAuthentication(request, response);
//    }


    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            String kaptcha = request.getParameter("kaptcha");
            String captcha = (String) request.getSession().getAttribute(GlobalConstant.KEY_CAPTCHA);
            if(StringUtils.isBlank(captcha) || (! captcha.equalsIgnoreCase(kaptcha))) {
                //throw new AuthenticationServiceException("图片验证码错误！");
                throw new CaptchaException("captcha Invalid");
            }
            return super.attemptAuthentication(request, response);
        } catch (CaptchaException ce){
            throw new CaptchaException("验证码错误");
        } catch (UsernameNotFoundException nfe){
            throw new UsernameNotFoundException("未知账户");
           // logger.error("对用户进行登录验证..验证未通过,未知账户");
        } catch (BadCredentialsException bce){
            throw new BadCredentialsException("账号或密码错误");
           // logger.error("对用户进行登录验证..验证未通过,错误的凭证");
        } catch (LockedException le){
            throw new LockedException("账户已锁定");
           // logger.error("对用户进行登录验证..验证未通过,账户已锁定");
        } catch (CredentialsExpiredException cee){
            throw new CredentialsExpiredException("凭证过期");
            //logger.error("对用户进行登录验证..验证未通过,帐号已过期");
        } catch (DisabledException de){
            throw new DisabledException("账户已禁用");
            //logger.error("对用户进行登录验证..验证未通过,帐号已禁用");
        }
    }

}
