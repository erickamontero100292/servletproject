package com.library.controller;

import com.library.dao.DbConnection;
import com.library.dao.UserDao;
import com.library.model.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		String msg = "";

		switch (action) {
			case "login":
				// Aqui no existe todavia una sesion para el usuario, lo mandamos al form de login
				if (session.getAttribute("usuario") == null) {
					request.setAttribute("message", msg);
					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				} else { // Ya esta logueado, lo mandamos al index.jsp, pero de la administracion
					rd = request.getRequestDispatcher("/admin.jsp");
					rd.forward(request, response);
				}
				break;
			case "crear":
				if (session.getAttribute("usuario") == null) {
					msg = "Acceso Denegado.";
					request.setAttribute("message", msg);
					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				} else {
					rd = request.getRequestDispatcher("/frmbook.jsp");
					rd.forward(request, response);
				}
				break;
			case "eliminar":
				if (session.getAttribute("usuario") == null) {
					msg = "Acceso Denegado.";
					request.setAttribute("message", msg);
					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				} else {
					//this.eliminarServidor(request, response);
				}
				break;
            /*
            Cuando es logout (GET), cerramos la session. Aqui se puede comprobar en el administrador
            de aplicaciones de tomcat, como la session es destruida.
            */
			case "logout":
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/homepage");
				break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recibimos parametros del formulario de login
		String userParam = request.getParameter("user");
		String passParam = request.getParameter("pass");
		String msg = "";
		// Recuperamos una instancia del objeto HttpSession
		HttpSession session = request.getSession();

		DbConnection conn = new DbConnection();
		UserDao userDao = new UserDao(conn);
		// Verificamos en la BD, si es un usuario correcto.
		User usuario = userDao.login(userParam, passParam);
		conn.disconnect();

		RequestDispatcher rd;
		if (usuario.getId() > 0) {
			// Creamos una variable de session, con el registro de usuario (Bean)
			// Verificar en el administrador de aplicaciones de tomcat.
			session.setAttribute("usuario", usuario);
			rd = request.getRequestDispatcher("/admin.jsp");
			rd.forward(request, response);

		} else {
			msg = "Usuario y/o password incorrectos";
			request.setAttribute("message", msg);
			rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}


}
