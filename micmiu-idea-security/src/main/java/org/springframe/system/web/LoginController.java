package org.springframe.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframe.common.constans.GlobalConstant;
import org.springframe.common.util.ImageUtils;
import org.springframe.configure.CaptchaAuthenticationFilter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by HeYixuan on 2017/4/14.
 */
@Controller
public class LoginController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @RequestMapping({"/","/index"})
    public String index() {
        // 加入一个属性，用来在模板中读取
        return "index";
    }

    //@PostMapping("/login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect){
        try {
            CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
            captchaAuthenticationFilter.attemptAuthentication(request,response);
        } catch (UsernameNotFoundException nfe){
            logger.error("对用户进行登录验证..验证未通过,未知账户");
            redirect.addFlashAttribute("message", "未知账户");
            return "redirect:/login";
        } catch (AuthenticationServiceException ase){
            logger.error("对用户进行登录验证..验证未通过,图片验证码错误");
            redirect.addFlashAttribute("message", "验证码错误");
            return "redirect:/login";
        } catch (BadCredentialsException bce){
            logger.error("对用户进行登录验证..验证未通过,错误的凭证");
            redirect.addFlashAttribute("message", "密码错误");
            return "redirect:/login";
        } catch (LockedException le){
            logger.error("对用户进行登录验证..验证未通过,账户已锁定");
            redirect.addFlashAttribute("message", "账户已锁定");
            return "redirect:/login";
        } catch (CredentialsExpiredException cee){
            logger.error("对用户进行登录验证..验证未通过,帐号已过期");
            redirect.addFlashAttribute("message", "帐号已过期");
            return "redirect:/login";
        } catch (DisabledException de){
            logger.error("对用户进行登录验证..验证未通过,帐号已禁用");
            redirect.addFlashAttribute("message", "帐号已禁用");
            return "redirect:/login";
        }
        return "/";
    }

    /**
     * 生成验证码方法
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 禁止图像缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        // 利用图片工具生成图片
        // 第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtils.createImage();
        // 先移除session 再将验证码存入Session
        session.removeAttribute(GlobalConstant.KEY_CAPTCHA);
        session.setAttribute(GlobalConstant.KEY_CAPTCHA, objs[0]);
        System.err.println("验证码为:"+objs[0]);
        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(){
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String Not_Found(){
        return "404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String SERVER_ERROR(){
        return "500";
    }

    @RequestMapping(value = "/extra_lock1", method = RequestMethod.GET)
    public String extra_lock1(){
        return "extra_lock1";
    }

    @RequestMapping(value = "/extra_lock2", method = RequestMethod.GET)
    public String extra_lock2(){
        return "extra_lock2";
    }
}
