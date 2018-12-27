# demo-01-security





## 如何构建Spring Security项目

### 1. 引入maven

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### 2. 构建配置类

#### 2-1. WebSecurityConfigurerAdapter

1. 创建MySecurityConfig，继承WebSecurityConfigurerAdapter；

```java
package com.sample.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 开启web security模式
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
}
```

2. 控制请求的访问权限

   * 在上面这个类中重写WebSecurityConfigurerAdapter的configure(HttpSecurity http)方法；

   * configure(HttpSecurity http)中的HttpSecurity可以用来指定安全规则；

   * http.authorizeRequest()...用来描述验证请求的方式

     ```java
     @EnableWebSecurity // 开启web security模式
     public class MySecurityConfig extends WebSecurityConfigurerAdapter {
         @Override
         protected void configure(HttpSecurity http) throws Exception {
     
             // 定制请求的授权规则
             http
                 .authorizeRequests()
                     .antMatchers("/").permitAll()
                     .antMatchers("/aaa/**").hasRole("a1")
                     .antMatchers("/bbb/**").hasRole("b2")
             ;
         }
     }
     ```

3. 开启Security自带的登陆功能

   ```
   // 开启自动配置的登陆功能，如果没有权限，就会进入登陆页面
   http.formLogin();
   // 1. /login 来到登陆页面
   // 2. 如果登陆失败，重定向到/login?error
   ```

4. 当开启用户登录功能后，用户信息如何验证呢？ 

   **答：重写gonfigureGlobal(AuthenticationManagerBuild auth)方法**

   ```java
   // 设置用户信息如何验证
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.jdbcAuthentication() // 用JDBC查用户
       auth.inMemoryAuthentication() //在内存中查用户
           .withUser("aaa").password("111111").roles("a1")
           .and()
           .withUser("aaa").password("111111").roles("b1")
           .and()
           .withUser("ccc").password("111111").roles("a1", "b1");
   }
   ```


