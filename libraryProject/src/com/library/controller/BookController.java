package com.library.controller;

import com.library.constants.ConstantView;
import com.library.constants.Messages;
import com.library.dao.BookDao;
import com.library.dao.DbConnection;
import com.library.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ver".equals(action)) {
            this.details(request, response);
        } else if ("lista".equals(action)) {
            this.allLoad(request, response);
        } else if ("enviar".equals(action)) {
            this.showForms(request, response);
        }

    }

    protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idBook = Integer.valueOf(request.getParameter("id"));
        DbConnection dbConnection = new DbConnection();
        BookDao bookDao = new BookDao(dbConnection);
        Book book = bookDao.getById(idBook);
        request.setAttribute("book", book);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher(ConstantView.DETAIL.getName());
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Book book = new Book(0);
        String nameBook = request.getParameter("nombre");
        String nameAutor = request.getParameter("author");
        String description = request.getParameter("descripcion");
        String detail = request.getParameter("detalle");
        String datePublished = request.getParameter("datePublished");
        book.setName(nameBook);
        book.setAuthor(nameAutor);
        book.setDescription(description);
        book.setDetail(detail);
        book.setDatePublished(Integer.parseInt(datePublished.substring(0,4)));
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        boolean status = bookDao.insert(book);
        String msg = "";
        if (status) {
            msg = Messages.BOOK_SAVE.getMessage();
        } else {
            msg = Messages.BOOK_SAVE_ERROR.getMessage();
        }
        conn.disconnect();
        RequestDispatcher rd;
        request.setAttribute("message", msg);
        rd = request.getRequestDispatcher(ConstantView.MESSAGE_ADMIN.getName());
        rd.forward(request, response);
    }

    protected void allLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        List<Book> lista = bookDao.getAll();
        conn.disconnect();
        request.setAttribute("books", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher(ConstantView.BOOKS.getName());
        rd.forward(request, response);
    }

    protected void showForms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBook = Integer.parseInt(request.getParameter("id"));
        Book book = null;
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        book = bookDao.getById(idBook);
        conn.disconnect();
        request.setAttribute("book", book);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher(ConstantView.REQUEST_FORM.getName());
        rd.forward(request, response);
    }

}
