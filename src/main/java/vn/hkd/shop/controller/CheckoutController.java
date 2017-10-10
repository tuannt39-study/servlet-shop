package vn.hkd.shop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.hkd.shop.dao.OrderDAO;
import vn.hkd.shop.dao.OrderDAOImpl;
import vn.hkd.shop.db.DBConnection;
import vn.hkd.shop.domain.Cart;
import vn.hkd.shop.domain.Item;
import vn.hkd.shop.domain.Order;
import vn.hkd.shop.domain.User;
import java.sql.Connection;
import java.util.List;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/checkout.html")
public class CheckoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    OrderDAO orderDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        Connection conn = DBConnection.getInstance().getConn();
        orderDAO = new OrderDAOImpl(conn);
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
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            session.setAttribute("error", "Please login to checkout");
            response.sendRedirect("index.html");
        } else if (cart == null || cart.getCount() == 0) {
            session.setAttribute("error", "Your cart is empty");
            response.sendRedirect("index.html");
        } else {
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }       
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
        request.setCharacterEncoding("UTF-8");
        
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        if (name.equals("") || phone.equals("") || address.equals("")) {
            request.setAttribute("error", "Please enter all required fields");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        } else {
            // Save order
            Date created = new Date();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Cart cart = (Cart) session.getAttribute("cart");
            List<Item> items = cart.getItems();
            Order order = new Order(name, phone, address, note, created, user, items);
            orderDAO.save(order);

            session.removeAttribute("cart");
            session.setAttribute("success", "You placed order successfully!");
            response.sendRedirect("index.html");
        }
    }

}
