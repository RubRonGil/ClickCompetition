package es.iesrafaelalberti.daw.dwes.clickcompetitionbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class ClickCompetitionBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickCompetitionBaseApplication.class, args);
    }
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers( "/demo2.html").hasRole("player")
                    .antMatchers("/addclicks/*").hasRole("player")
                    .antMatchers("/team/image/*").hasRole("coach")
                    .antMatchers("/").permitAll()
                    .and().formLogin().defaultSuccessUrl("/index.html");
        }

        @Override
        protected void configure (AuthenticationManagerBuilder auth) throws  Exception{
            auth.inMemoryAuthentication()
                    .withUser("Rubo").password("Rubo").roles("player")
                    .and()
                    .withUser("Diego").password("Diego").roles("player")
                    .and()
                    .withUser("Javi").password("Javi").roles("coach");
        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {

            return NoOpPasswordEncoder.getInstance();
        }

    }
}
