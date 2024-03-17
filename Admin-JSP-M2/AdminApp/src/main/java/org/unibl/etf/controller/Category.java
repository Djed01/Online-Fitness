package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.model.bean.CategoryBean;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CategoryBean categoryBean = new CategoryBean();
    	HttpSession session = request.getSession();
    	session.setAttribute("categoryBean", categoryBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/categories.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if (action != null && action.equals("delete")) {
	        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	        CategoryBean categoryBean = (CategoryBean) request.getSession().getAttribute("categoryBean");
	        boolean success = categoryBean.deleteCategory(categoryId);
	        if (success) {
	        	System.out.println("\n\n\nDeleted!\n\n\n");
	            // TODO Success
	        } else {
	        	System.out.println("\n\n\nNot Deleted!\n\n\n");
	            // TODO Error
	        }
	    }

	    response.sendRedirect(request.getContextPath());
	}

}
