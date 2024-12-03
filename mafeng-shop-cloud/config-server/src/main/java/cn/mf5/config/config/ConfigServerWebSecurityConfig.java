package cn.mf5.config.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ConfigServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 都是无状态请求，不需要session，节省资源
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        //关闭csrf跨站请求防御
        http.csrf().disable();
        // 所有的请求必须登录认证后才能访问
        http.authorizeRequests().antMatchers("/bus-refresh","/actuator/bus-refresh").permitAll().anyRequest().authenticated().and().httpBasic();
    }
}