package content.com.ua.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/customer/login/loginPage").hasAnyRole("CUSTOMER","WRITER","ADMIN")
                .antMatchers("/api/customer/login/checkEmail").permitAll()
                .antMatchers("/api/customer/getPaperTypes").permitAll()//.hasRole("CUSTOMER")
                .antMatchers("/api/customer/getFormats").permitAll()//.hasRole("CUSTOMER")
                .antMatchers("/api/customer/getSubjects").permitAll()//.hasRole("CUSTOMER")
                .antMatchers("/api/customer/getWriterLevels").permitAll()//.hasRole("CUSTOMER")
                .antMatchers("/api/customer/getServiceTypes").permitAll()//.hasRole("CUSTOMER")
                .antMatchers("/api/customer/add_order").hasRole("CUSTOMER")
                .antMatchers("/api/customer/uploadFile").hasRole("CUSTOMER")
                .antMatchers("/api/customer/forgotPassword").permitAll()//.hasRole("CUSTOMER")

                .and()
                .logout()
                .permitAll()
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }
}
