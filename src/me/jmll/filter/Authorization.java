package me.jmll.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.jmll.model.User;

/**
 * Servlet Filter implementation class Login
 */
public class Authorization implements Filter {
	private static final Logger logger = LogManager.getLogger(Authorization.class);
    /**
     * Default constructor. 
     */
    public Authorization() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Casting de request y response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		List<String> errors = new ArrayList<String>();
		// Valida que exista el usuario
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("validUuser");
		if (user != null) {
			logger.info("Usuario {} ha sido autenticado", user);
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			errors.add("Por favor, inicie sesión para tener acceso.");
			request.setAttribute("errors", errors);
			logger.info("Usuario no ha sido autenticado {}", req.getRemoteAddr());
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
