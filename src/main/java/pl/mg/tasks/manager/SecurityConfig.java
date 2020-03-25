package pl.mg.tasks.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("user1").authorities("ROLE_USER")
                .and().withUser("user2").password("user2").authorities("ROLE_USER")
                .and().withUser("admin").password("admin").authorities("ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/tasks", "/tasks/*").permitAll()
                .antMatchers(HttpMethod.POST, "/tasks", "/tasks/*").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/tasks", "/tasks/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/tasks", "/tasks/*").hasRole("ADMIN")
                .and().httpBasic();
    }
}
