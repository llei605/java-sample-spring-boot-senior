package com.sample.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 开启web security模式
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 定制请求的授权规则
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/aaa/**").hasRole("a1")
                .antMatchers("/bbb/**").hasRole("b1");
        // 开启自动配置的登陆功能，如果没有权限，就会进入登陆页面
        http.formLogin();
        // 1. /login 来到登陆页面
        // 2. 如果登陆失败，重定向到/login?error
    }

    // 设置用户信息如何验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication() // 用JDBC查用户
        auth.inMemoryAuthentication() //在内存中查用户
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("aaa").password(new BCryptPasswordEncoder().encode("111")).roles("a1")
                .and()
                .withUser("bbb").password(new BCryptPasswordEncoder().encode("222")).roles("b1")
                .and()
                .withUser("ccc").password(new BCryptPasswordEncoder().encode("333")).roles("a1", "b1")
                ;
    }
}
