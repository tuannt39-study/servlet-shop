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
import vn.hkd.shop.domain.Product;
import java.sql.Connection;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product.html")
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    CategoryDAO categoryDAO;
    ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
        String forward = "/index.jsp";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.findOne(id);
            if (product == null) {
                forward = "/404.jsp";
            } else {
                request.setAttribute("categories", categoryDAO.findAll());
                request.setAttribute("product", product);
                forward = "product.jsp";
            }
        } catch (NumberFormatException e) {
            forward = "/404.jsp";
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

}
