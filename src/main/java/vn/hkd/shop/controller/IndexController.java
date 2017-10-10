package vn.hkd.shop.controller;

import vn.hkd.shop.dao.CategoryDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.hkd.shop.dao.CategoryDAOImpl;
import vn.hkd.shop.dao.ProductDAO;
import vn.hkd.shop.dao.ProductDAOImpl;
import vn.hkd.shop.db.DBConnection;
import java.sql.Connection;

/**
 * Servlet implementation class IndexController
 */
@WebServlet({"", "/index.html"})
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    CategoryDAO categoryDAO;
    ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        Connection conn = DBConnection.getInstance().getConn();
        categoryDAO = new CategoryDAOImpl(conn);
        productDAO = new ProductDAOImpl(conn);
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
        request.setAttribute("categories", categoryDAO.findAll());
        request.setAttribute("products", productDAO.findAll(9));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
