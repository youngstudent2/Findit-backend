package cn.edu.nju.FindIt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.edu.nju.FindIt.security.JwtAuthenticationTokenFilter;
import cn.edu.nju.FindIt.security.handler.CustomAccessDeniedHandler;
import cn.edu.nju.FindIt.security.handler.CustomAuthenticationEntryPoint;
import cn.edu.nju.FindIt.security.qq.QqAuthenticationFilter;
import cn.edu.nju.FindIt.security.qq.QqAuthenticationProvider;
import cn.edu.nju.FindIt.security.qq.QqAuthenticationSuccessHandler;
import cn.edu.nju.FindIt.security.wx.WxAuthenticationFilter;
import cn.edu.nju.FindIt.security.wx.WxAuthenticationProvider;
import cn.edu.nju.FindIt.security.wx.WxAuthenticationSuccessHandler;

/**
 * security配置类
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(wxAuthenticationProvider())
            .authenticationProvider(qqAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                // 不创建Session, 使用jwt来管理用户的登录状态
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // /error 异常端点不需要用户认证
                .antMatchers("/error/**").permitAll()
                // 开放swagger文档
                .antMatchers("/swagger-ui.html",
                                "/swagger-ui/*",
                                "/swagger-resources/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/webjars/**",
                                "/webjars/**").permitAll()
                .antMatchers("/api/data/**").permitAll()
                .antMatchers("/api/posts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/images").permitAll()
                // 其余的全部需要用户认证
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(wxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(qqAuthenticationFilter(), JwtAuthenticationTokenFilter.class);
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public WxAuthenticationFilter wxAuthenticationFilter(){
        WxAuthenticationFilter wxAuthenticationFilter = new WxAuthenticationFilter("/api/login/wx");
        wxAuthenticationFilter.setAuthenticationSuccessHandler(wxAuthenticationSuccessHandler());
        return wxAuthenticationFilter;
    }

    @Bean
    public QqAuthenticationFilter qqAuthenticationFilter(){
        QqAuthenticationFilter qqAuthenticationFilter = new QqAuthenticationFilter("/api/login/qq");
        qqAuthenticationFilter.setAuthenticationSuccessHandler(qqAuthenticationSuccessHandler());
        return qqAuthenticationFilter;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public WxAuthenticationProvider wxAuthenticationProvider(){
        return new WxAuthenticationProvider();
    }

    @Bean
    public QqAuthenticationProvider qqAuthenticationProvider(){
        return new QqAuthenticationProvider();
    }

    @Bean
    public QqAuthenticationSuccessHandler qqAuthenticationSuccessHandler(){
        return new QqAuthenticationSuccessHandler();
    }

    @Bean
    public WxAuthenticationSuccessHandler wxAuthenticationSuccessHandler(){
        return new WxAuthenticationSuccessHandler();
    }
}
