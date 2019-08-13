package com.library.controller;

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
            this.verDetalle(request, response);
        } else if ("lista".equals(action)) {
            this.verTodas(request, response);
        } else if ("enviar".equals(action)) {
            this.mostrarFormulario(request, response);
        }

    }

    protected void verDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idBook = Integer.valueOf(request.getParameter("id"));
        DbConnection dbConnection = new DbConnection();
        BookDao bookDao = new BookDao(dbConnection);
        Book book = bookDao.getById(idBook);
        // Compartimos la variable srv para acceder desde la vista con Expression Language
        request.setAttribute("book", book);
        RequestDispatcher rd;
        // Enviarmos respuesta a la vista detalle.jsp
        rd = request.getRequestDispatcher("/detalle.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    protected void verTodas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        List<Book> lista = bookDao.getAll();
        conn.disconnect();
        // Compartimos la variable lista, para poder accederla desde la vista
        request.setAttribute("books", lista);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/books.jsp");
        rd.forward(request, response);
    }

    protected void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBook = Integer.parseInt(request.getParameter("id"));
        Book book = null;
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        book = bookDao.getById(idBook);
        conn.disconnect();
        request.setAttribute("book", book);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/requestForm.jsp");
        rd.forward(request, response);
    }

}
