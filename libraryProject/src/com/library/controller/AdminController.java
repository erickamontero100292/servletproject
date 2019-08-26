package com.library.controller;

import com.library.constants.ConstantController;
import com.library.constants.ConstantView;
import com.library.constants.Messages;
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
        String msg = "";

        try {
            switch (action) {
                case "login":
                    loginCase(request, response, session, msg);
                    break;
                case "crear":
                    addCase(request, response, session);
                    break;
                case "eliminar":
                    deleteCase(request, response, session);
                    break;
                case "logout":
                    logoutCase(request, response, session);
                    break;
				default: break;
            }
        } catch (ServletException e) {
            //todo implement log4j
        } catch (IOException e) {
			//todo implement log4j();
        }

    }

    private void logoutCase(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + ConstantView.HOME_PAGE.getName());
    }

    private void deleteCase(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String msg;
        RequestDispatcher rd;
        if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
            msg = Messages.ACCESS_DENIED.getMessage();
            request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
            rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
            rd.forward(request, response);
        } else {
            this.deleteBook(request, response);
        }
    }

    private void addCase(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String msg;
        RequestDispatcher rd;
        if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
            msg = "Acceso Denegado.";
            request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
            rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
            rd.forward(request, response);
        } else {
            rd = request.getRequestDispatcher(ConstantView.FORM_BOOK.getName());
            rd.forward(request, response);
        }
    }

    private void loginCase(HttpServletRequest request, HttpServletResponse response, HttpSession session, String msg) throws ServletException, IOException {
        RequestDispatcher rd;
        if (session.getAttribute(ConstantController.USUARIO.getProperty()) == null) {
            request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
            rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
            rd.forward(request, response);
        } else {
            rd = request.getRequestDispatcher(ConstantView.ADMIN.getName());
            rd.forward(request, response);
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
            rd = request.getRequestDispatcher(ConstantView.ADMIN.getName());
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
			} catch (IOException e) {
			}

		} else {
            msg = Messages.USER_PASSWORD_ERROR.getMessage();
            request.setAttribute(ConstantController.MESSAGE.getProperty(), msg);
            rd = request.getRequestDispatcher(ConstantView.LOGIN.getName());
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idBook = Integer.parseInt(request.getParameter("idBook"));
        DbConnection conn = new DbConnection();
        BookDao bookDao = new BookDao(conn);
        int respuesta = bookDao.delete(idBook);
        String msg = "";
        if (respuesta == 1) {
            msg = Messages.BOOK_DELETE.getMessage();
        } else {
            msg = Messages.BOOK_WITH_REQUEST.getMessage();
        }
        conn.disconnect();
        request.setAttribute("message", msg);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher(ConstantView.MESSAGE_ADMIN.getName());
        rd.forward(request, response);
    }


}
