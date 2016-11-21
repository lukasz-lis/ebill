package pl.eightbit;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.eightbit.models.Member;
import pl.eightbit.services.internal.MemberDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private MemberDetailsService memberDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(memberDetailsService)
                .passwordEncoder(Member.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/built/**", "/node_modules/**", "/main.css", "/rejestracja").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/logowanie")
                .failureUrl("/logowanie-blad")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .csrf().disable()
                .logout()
                .logoutUrl("/wyloguj")
                .logoutSuccessUrl("/");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
