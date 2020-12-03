package site.liuye.studentinfomanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.liuye.studentinfomanager.handler.AccessHandler;
import site.liuye.studentinfomanager.handler.FailureHandler;
import site.liuye.studentinfomanager.handler.LogoutHandler;
import site.liuye.studentinfomanager.handler.SuccessHandler;
import site.liuye.studentinfomanager.service.UserService;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 11:06
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    //不对密码进行加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通过数据库来实现用户的认证与登录
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //如果用户拥有admin权限，那么它可以访问/admin/**、/user/**里所有的API
        //如果用户拥有user权限，则可以访问/admin/getAllClassInfo、/user/**
        //同时，设置了登录的API接口为login，并设置了对应的登录处理器、登录成功的处理器、登陆失败处理器和权限不够的处理器
        //关闭了csrf
        http.authorizeRequests()
                .antMatchers("/admin/getAllClassInfo").access("hasAnyRole('admin','user')")
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").access("hasAnyRole('admin','user')")
                .and()
                .formLogin().loginProcessingUrl("/login")
                .successHandler(new SuccessHandler()).failureHandler(new FailureHandler())
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutHandler()).deleteCookies("JSESSIONID")
                .and()
                .csrf().disable().exceptionHandling().accessDeniedHandler(new AccessHandler());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }
}