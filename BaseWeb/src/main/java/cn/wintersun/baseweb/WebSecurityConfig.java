package cn.wintersun.baseweb;

import cn.wintersun.baseweb.component.CustomAccessDeniedHandler;
import cn.wintersun.baseweb.component.JwtAuthenticationEntryPoint;
import cn.wintersun.baseweb.filter.JwtAuthenticationTokenFilter;
import cn.wintersun.baseweb.service.intrer.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.SecurityConfig
 * @create 2021-10-13-10:05
 * @Description TODO
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;


    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;


    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                             JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // ??????UserDetailsService
                .userDetailsService(this.customUserDetailsService)
                // ??????BCrypt???????????????hash
                .passwordEncoder(passwordEncoder());
    }

    // ??????BCrypt???????????????
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();

        httpSecurity
                // ??????????????????JWT????????????????????????csrf
                .cors()
                .and()
                .csrf().disable()
                // ??????token??????????????????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // ????????????token???rest api?????????????????????
                .antMatchers("/login", "/admin").permitAll()
                // ???????????????????????????????????????????????????
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).accessDeniedHandler(customAccessDeniedHandler);

        // ????????????
        //httpSecurity.headers().cacheControl();

        // ??????JWT filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

  /*  @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html"
        );
    }*/

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
