package ua.springweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity//активовуємо спрінг секюріті
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	/*auth.
	inMemoryAuthentication().withUser("admin@gmail.com").password("123")
	.roles("ADMIN").and().withUser("user@gmail.com").password("123").roles("USER");
	
	}

*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.formLogin().usernameParameter("email")
	.passwordParameter("password")
	.loginPage("/login").successHandler(new CustomAuthenticatinSuccesshandler()) //<--2 // для того щоб посилання відкривалося
	.failureUrl("/login?fail=true")//якщо авторизація пройшла неуспішно (і поверне на якусь вказану  сторінку)
	.and()
	.logout()
	.logoutUrl("/logout")                                  //якщо logout успішний то редіректить на
	.logoutSuccessUrl("/")//редіректить сюда home(вказуємо тут куда редіректити)
	.deleteCookies("JSESSIONID", "RememberMeCookie")//видаляємо кукі бо спрінг по дефолту зберігає юзера в JSESSIONID
	.invalidateHttpSession(true)
	
	.and()
	.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
	.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
	.anyRequest().permitAll()
	.and()
	.exceptionHandling().accessDeniedPage("/")
	.and()
	.sessionManagement().maximumSessions(1)
	.and().and()  // для створення кукі для RememberMe
	.rememberMe()
	.rememberMeParameter("rememberme").key("secret key")
	.rememberMeCookieName("RememberMeCookie")//RememberMeCookie - назва новоствореного кукі
	.tokenValiditySeconds(365*24*60*60);// 1 year / default 2 weeks
	

	}


	@Override
	public void configure(WebSecurity web) throws Exception {	//для того щоб не ігнорив css і тд 
	web.ignoring().antMatchers("/resources/**");
	
	}
}
