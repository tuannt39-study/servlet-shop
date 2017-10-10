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
import vn.hkd.shop.domain.Category;
import java.sql.Connection;
import java.util.List;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/category.html")
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    CategoryDAO categoryDAO;
    ProductDAO productDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
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

            // Check category exists
            boolean flag = false;
            List<Category> categories = categoryDAO.findAll();
            Category current = null;
            for (Category category : categories) {
                if (category.getId() == id) {
                    current = category;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                request.setAttribute("categories", categories);
                request.setAttribute("category", current);
                request.setAttribute("products", productDAO.findByCategory(id));
                forward = "/category.jsp";
            } else {
                forward = "/404.jsp";
            }
        } catch (NumberFormatException e) {
            forward = "/404.jsp";
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

}
