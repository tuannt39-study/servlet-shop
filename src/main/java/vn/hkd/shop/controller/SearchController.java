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
 * Servlet implementation class SearchController
 */
@WebServlet("/search.html")
public class SearchController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    CategoryDAO categoryDAO;
    ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
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
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        if (keyword.equals("")) {
            response.sendRedirect("index.html");
        } else {
            request.setAttribute("categories", categoryDAO.findAll());
            request.setAttribute("products", productDAO.search(keyword));
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }
    }

}
