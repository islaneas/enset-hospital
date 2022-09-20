package ma.enset.ensethospital.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          PasswordEncoder passwordEncoder=passwordEncoder();
       /* String Encodedpwd =passwordEncoder.encode("1234");
            System.out.println(Encodedpwd);


        auth.inMemoryAuthentication()
                .withUser("user1").password(Encodedpwd).roles("USER")
                .and()
                .withUser("user2").password(Encodedpwd).roles("USER")
                .and()
                .withUser("admin").password(Encodedpwd).roles("USER","ADMIN");
*/
       auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,password as credential ,active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("Role_")
                .passwordEncoder(passwordEncoder);


        //auth.userDetailsService(userDetailsService );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
      http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        //http.authorizeRequests().antMatchers("/index/**").hasAuthority("USER");
        //http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
