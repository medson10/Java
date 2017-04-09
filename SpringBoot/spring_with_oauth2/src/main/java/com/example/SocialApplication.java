package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SocialApplication extends WebSecurityConfigurerAdapter{

	@RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}

	@Autowired
  OAuth2ClientContext oauth2ClientContext;

	@Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .antMatcher("/**")
      .authorizeRequests()
        .antMatchers("/", "/login**", "/webjars/**")
        .permitAll()
      .anyRequest()
        .authenticated();
			.and().logout().logoutSuccessUrl("/").permitAll();
  }

}
