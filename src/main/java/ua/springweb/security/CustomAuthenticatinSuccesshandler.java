package ua.springweb.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticatinSuccesshandler implements AuthenticationSuccessHandler{// щоб перекидало на сторінки у відповідності до вашої залогіненої або створеної ролі
//після написання викликаємо його в класі SpringSecurityConfig 2-->
	private final String ADMIN_PAGE = "/admin";
	private final String USER_PAGE = "/user";
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}
	
	
	private void handle(HttpServletRequest request , HttpServletResponse response, Authentication authentication) throws IOException {
		
		String targetURL = determinateTargetUrl(authentication);
		
		if(response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetURL);
	}
	
	
	private String determinateTargetUrl(Authentication authentication) {// Authentication містить дані про користувача(name, password, role) ми будемо получати роль для цьго використаємо Collecton 1-->
		boolean isAdmin = false;
		
		boolean isUser = false;
		
		
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities(); //<--1   (getAuthorities містить всі дані)
		
		for(GrantedAuthority  grantedAuthority : authorities) {
			
			if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
		break;
			}else if(grantedAuthority.getAuthority().equals("ROLE_USER")) {
				isUser = true;
				break;
			}
		}
		if(isAdmin) {
			return ADMIN_PAGE;
		}else if(isUser) {
			return USER_PAGE;
		}
		return null;
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {//опрацювання помилки
	HttpSession session = request.getSession(false);
	if(session == null) {
		return;
	}
	session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);//будуть видалятися старі атрибути і створюватися (переприсвоюватися) нові (не забуваємо викликати метод)
	}
	

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	
}
