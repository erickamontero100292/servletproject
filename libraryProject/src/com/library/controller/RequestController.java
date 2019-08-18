package com.library.controller;

import com.library.dao.BookDao;
import com.library.dao.DbConnection;
import com.library.dao.RequestDao;
import com.library.model.Book;
import com.library.model.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public RequestController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		switch (action) {
			case "solicitudes":
				if (session.getAttribute("usuario") == null) {
					rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
				} else {
					this.verSolicitudes(request, response);
				}
				break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int idBook = Integer.parseInt(request.getParameter("idBook"));

		Request requestModel = new Request(0);

		requestModel.setAddress(address);
		requestModel.setDateRequest(new Date());
		requestModel.setEmail(email);
		requestModel.setName(name);
		requestModel.setPhone(phone);
		DbConnection conn = new DbConnection();
		BookDao bookDao = new BookDao(conn);
		Book book = bookDao.getById(idBook);
		requestModel.setBook(book);
		RequestDao requestDao = new RequestDao(conn);
		requestDao.insert(requestModel);
		conn.disconnect();
		String msg="";
		msg = "<b>" + requestModel.getName() + "</b> hemos recibido tus datos."
				+ "<br> Revisaremos tu solicitud y nos pondremos en contacto contigo.<br><br>Gracias.";
		request.setAttribute("message", msg);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/mensaje_guest.jsp");
		rd.forward(request, response);
	}

	protected void verSolicitudes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;
		String msg = null;
		List<Request> requestList = null;
		DbConnection conn = new DbConnection();
		RequestDao requestDao = new RequestDao(conn);
		requestList = requestDao.getAll();
		conn.disconnect();

		request.setAttribute("message", msg);
		request.setAttribute("requests", requestList);
		rd = request.getRequestDispatcher("/requests.jsp");
		rd.forward(request, response);
	}

}
