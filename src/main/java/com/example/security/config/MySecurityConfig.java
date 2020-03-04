package com.example.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author huang.beijin
 * @date 2020/3/3 21:57
 * @description
 */

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    //定义授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能
//        http.formLogin();
        //1.Login请求来到登录页

        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin"); //定义自己登录页

        //一旦定制 loginPage 那么 loginPage 的 post 请求就是登录
        //2./login?error 登录失败，重定向到这里
        //3.更多详细规则

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页

        //开启记住我功能
        http.rememberMe();
        //登录成功之后，将cookie发送给浏览器，以后访问页面会带上cookie,只要通过检查就能免登录
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP3");
    }


}
