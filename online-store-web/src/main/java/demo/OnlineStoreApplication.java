package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
public class OnlineStoreApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/login", "/", "/api/catalog/**",
                        "/user", "/assets/**", "/css/**", "/dist/**", "/js/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }

}
