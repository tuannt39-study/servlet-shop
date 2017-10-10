package vn.hkd.shop.controller;

import vn.hkd.shop.dao.UserDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.hkd.shop.dao.UserDAOImpl;
import vn.hkd.shop.db.DBConnection;
import vn.hkd.shop.domain.User;
import java.sql.Connection;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.html")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserDAO userDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        Connection conn = DBConnection.getInstance().getConn();
        userDAO = new UserDAOImpl(conn);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.findOne(email, password);
        if (user == null) {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("success", "Login successfully");
            session.setAttribute("user", user);
            response.sendRedirect("index.html");
        }
    }

}
