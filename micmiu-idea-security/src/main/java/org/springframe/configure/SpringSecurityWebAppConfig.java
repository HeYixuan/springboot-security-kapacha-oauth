package org.springframe.configure;

import org.springframe.security.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Created by HeYixuan on 2017/4/17.
 */
@EnableWebSecurity
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSecurityService springSecurityService;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .addFilterBefore(captchaAuthenticationFilter(), CaptchaAuthenticationFilter.class)
                .headers().frameOptions().sameOrigin()
            .and()
                /*.requestMatchers().antMatchers("/api/**","/oauth/**")   //配置使HttpSecurity接收以"/api/","/oauth/"开头请求。
            .and()*/
                .authorizeRequests()
                .antMatchers("/assets/**", "/kaptcha" , "/swagger-ui.html", "/webjars/**", "/oauth/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
//                .antMatchers("/dba/**").hasAnyRole("ADMIN", "DBA")
                .anyRequest().authenticated()
            .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/") //登录成功默认url
                .failureUrl("/login?error")
                .permitAll()
            .and()
                .sessionManagement()
                .maximumSessions(1)     // 一个账号最多允许一处登录
                .expiredUrl("/login?expired")
            .and()
                .and()
                .rememberMe() //记住用户
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(60 * 60 * 24 * 7) //设置cookie有效期
                // Example portMapper() configuration
                //下面的配置将确保在spring security中的http请求端口9090跳转到https端口9443 并且将http端口80跳转到https443端口。
            .and()
                .portMapper()
                .http(8089).mapsTo(9443)
                .http(80).mapsTo(443)
            .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(false)
               // .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/403")
            .and()
                .csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(springSecurityService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean(name="myAuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        captchaAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        captchaAuthenticationFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        captchaAuthenticationFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
        return captchaAuthenticationFilter;
    }



    /*@Bean
    public PasswordEncoder passwordEncoder(){
        //密码加密
        return new BCryptPasswordEncoder();
    }*/
}
