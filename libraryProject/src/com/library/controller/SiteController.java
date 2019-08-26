package com.library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.constants.ConstantView;
import com.library.dao.BookDao;
import com.library.dao.DbConnection;
import com.library.model.Book;


public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		 DbConnection conn = new DbConnection();
		 BookDao bookDao = new BookDao(conn);
		List<Book> bookList = bookDao.getUltimos();
		conn.disconnect();
		request.setAttribute("bookList", bookList);
		rd = request.getRequestDispatcher(ConstantView.INDEX.getName());
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
