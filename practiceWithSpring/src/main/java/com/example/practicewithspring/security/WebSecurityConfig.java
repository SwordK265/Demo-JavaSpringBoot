package com.example.practicewithspring.security;

import com.example.practicewithspring.security.jwt.JwtAuthenticationFilter;
import com.example.practicewithspring.eroles.ERole;
import com.example.practicewithspring.service.UserService;
import com.example.practicewithspring.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//kích hoạt tính năng Spring Security trên ứng dụng Web của mình
/* WebSecurityConfigurerAdapter là một interface tiện ích của
Spring Security giúp chúng ta cài đặt các thông tin dễ dàng hơn */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()// Ngăn chặn request từ 1 domain khác
                .authorizeRequests()
                .antMatchers("/","/api/v1/signup","/api/v1/login").permitAll()
                //api của tours
                .antMatchers(HttpMethod.GET,"/api/v1/tours","/api/v1/tours/{tourId}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/tours").hasAnyAuthority(String.valueOf(ERole.ADMIN),String.valueOf(ERole.GUIDE))
                .antMatchers(HttpMethod.PUT,"/api/v1/tours/{tourId}").hasAnyAuthority(String.valueOf(ERole.ADMIN),String.valueOf(ERole.GUIDE))
                .antMatchers(HttpMethod.DELETE,"/api/v1/tours/{tourId}").hasAnyAuthority(String.valueOf(ERole.ADMIN),String.valueOf(ERole.GUIDE))
                //api của users
                .antMatchers("/api/v1/users","/api/v1/users/{id}").hasAuthority(String.valueOf(ERole.ADMIN))
                //api của reviews
                .antMatchers(HttpMethod.GET,"/api/v1/reviews","api/v1/reviews/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/reviews").hasAuthority(String.valueOf(ERole.USER))
                .antMatchers(HttpMethod.PUT,"api/v1/reviews/{id}").hasAnyAuthority(String.valueOf(ERole.ADMIN),String.valueOf(ERole.USER))
                .antMatchers(HttpMethod.DELETE,"api/v1/reviews/{id}").hasAnyAuthority(String.valueOf(ERole.ADMIN),String.valueOf(ERole.USER))
                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                .and()
                // Thêm một lớp Filter kiểm tra jwt
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


}
