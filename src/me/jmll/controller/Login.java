package me.jmll.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import me.jmll.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Login.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    public void init(){
        Map<String, User> DB = new HashMap<String, User>();
        DB.put("r2d2", new User("r2d2", "c3po", "Kenny Baker"));
        DB.put("c3po", new User("c3po", "r2d2", "Anthony Daniels"));
        DB.put("luke", new User("luke", "r2d2", "Mark Hamill"));
        this.getServletContext().setAttribute("DB", DB);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtiene parámetros de la solicitud
		String inputUsername = request.getParameter("inputUsername");
		String inputPassword = request.getParameter("inputPassword");
		// Crea elementos de error o warnings
		List<String> errors = new ArrayList<String>();
		List<String> warnings = new ArrayList<String>();
		// Inicia la validación
		if (inputPassword != null && inputUsername != null){
			Map<String, User> DB = getDBfromContext();
			User user = DB.get(inputUsername);
			if (user != null){
				if (user.getPassword().equals(inputPassword)){
					// Usuario y passwords válido
					HttpSession session = request.getSession();
					logger.info("Autenticando a {}", user.toString());
					// Creando atributos de la sesión
					session.setAttribute("validUuser", user);
					
					// Agrega mensaje de bienvenida
					warnings.add(String.format("Bienvenido %s! ", user.getFullName()));
					request.setAttribute("warnings", warnings);
					
					// Redirecconando
					request.getRequestDispatcher("/welcome.jsp").forward(request, response);
					return;
				} else {
					errors.add("Contraseña inválida");
					request.setAttribute("errors", errors);
				}
			} else {
				errors.add("Usuario inválido");
				request.setAttribute("errors", errors);
			}
			
		} else{
			errors.add("Usuario y password no deben estar vacíos.");
			request.setAttribute("errors", errors);
		}
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, User> getDBfromContext() {
		return (HashMap<String, User>) this.getServletContext().getAttribute("DB");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
