package com.library.controller;

import com.library.dao.BookDao;
import com.library.dao.DbConnection;
import com.library.dao.RequestDao;
import com.library.model.Book;
import com.library.model.Request;
import com.library.util.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class RequestController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

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

        String msg = "";
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        Part archivo = request.getPart("archivo");
        String archivoParam = archivo.getSubmittedFileName();
        RequestDispatcher rd;
        File fileUploadDirectory = new File(uploadFilePath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        if (archivoParam.endsWith("pdf")) {

            String archivoFisico = Utility.randomAlphaNumeric(10) + archivoParam.replace(" ", "_");
            requestModel.setFile(archivoFisico);
            RequestDao requestDao = new RequestDao(conn);
            requestDao.insert(requestModel);
            conn.disconnect();
            msg = "<b>" + requestModel.getName() + "</b> hemos recibido tus datos."
                    + "<br> Revisaremos tu solicitud y nos pondremos en contacto contigo.<br><br>Gracias.";
            request.setAttribute("message", msg);

            archivo.write(uploadFilePath + File.separator + archivoFisico);
            rd = request.getRequestDispatcher("/mensaje_guest.jsp");

            rd.forward(request, response);
        } else {

            msg = "Solo se permiten archivos de tipo PDF";
            request.setAttribute("message", msg);
            request.setAttribute("requestModel", requestModel);
            request.setAttribute("book", requestModel.getBook());
            rd = request.getRequestDispatcher("/requestForm.jsp");
            rd.forward(request, response);
        }
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
