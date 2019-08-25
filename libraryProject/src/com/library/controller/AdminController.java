package com.library.controller;

import com.library.constants.ConstantController;
import com.library.constants.ConstantView;
import com.library.dao.BookDao;
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
				if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
					request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
					rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
					rd.forward(request, response);
				} else {
					rd = request.getRequestDispatcher(ConstantView.ADMIN.getName());
					rd.forward(request, response);
				}
				break;
			case "crear":
				if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
					msg = "Acceso Denegado.";
					request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
					rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
					rd.forward(request, response);
				} else {
					rd = request.getRequestDispatcher(ConstantView.FORM_BOOK.getName());
					rd.forward(request, response);
				}
				break;
			case "eliminar":
				if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
					msg = "Acceso Denegado.";
					request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
					rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
					rd.forward(request, response);
				} else {
				this.deleteBook(request,response);
				}
				break;
			case "logout":
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/homepage");
				break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userParam = request.getParameter("user");
		String passParam = request.getParameter("pass");
		String msg = "";
		HttpSession session = request.getSession();

		DbConnection conn = new DbConnection();
		UserDao userDao = new UserDao(conn);
		User usuario = userDao.login(userParam, passParam);
		conn.disconnect();

		RequestDispatcher rd;
		if (usuario.getId() > 0) {
			session.setAttribute(ConstantController.USUARIO.getProperty(), usuario);
			rd = request.getRequestDispatcher("/admin.jsp");
			rd.forward(request, response);

		} else {
			msg = "Usuario y/o password incorrectos";
			request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
			rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
			rd.forward(request, response);
		}
	}
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idBook = Integer.parseInt(request.getParameter("idBook"));
		DbConnection conn = new DbConnection();
		BookDao bookDao = new BookDao(conn);
		int respuesta = bookDao.delete(idBook);
		String msg = "";
		if (respuesta == 1) {
			msg = "El libro fue eliminado correctamente.";
		} else {
			msg = "El libro tiene solicitudes asociadas";
		}
		conn.disconnect();
		request.setAttribute("message", msg);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/message_admin.jsp");
		rd.forward(request, response);
	}


}
