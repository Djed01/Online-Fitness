package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.model.bean.AttributeBean;
import org.unibl.etf.model.bean.CategoryBean;
import org.unibl.etf.model.dto.AdminDTO;
import org.unibl.etf.model.dto.AttributeDTO;
import org.unibl.etf.model.dto.CategoryDTO;

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
        HttpSession session = request.getSession();
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null || !admin.isLoggedIn()) {
            response.sendRedirect("Login");
            return;
        }
        
        System.out.println("\n\n\n\n"+admin.getUsername()+"\n\n\n");

        CategoryBean categoryBean = new CategoryBean();
        AttributeBean attributeBean = new AttributeBean();
        
        session.setAttribute("categoryBean", categoryBean);
        session.setAttribute("attributeBean", attributeBean);
        
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
	    }else if (action != null && action.equals("update")) {
	        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	        String categoryName = request.getParameter("editCategoryName");

	        // Construct a Category object with updated values
	        CategoryDTO updatedCategory = new CategoryDTO();
	        updatedCategory.setId(categoryId);
	        updatedCategory.setName(categoryName);

	        // Update the category
	        CategoryBean categoryBean = (CategoryBean) request.getSession().getAttribute("categoryBean");
	        boolean success = categoryBean.updateCategory(updatedCategory);

	        if (success) {
	            // Category updated successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category update failed
	            // Redirect or set an error message as needed
	        }
	    }else if(action != null && action.equals("add")){
	    	String categoryName = request.getParameter("newCategoryName");
	    	
	    	CategoryDTO newCategory = new CategoryDTO();
	    	newCategory.setName(categoryName);
	    	// Add the category
	        CategoryBean categoryBean = (CategoryBean) request.getSession().getAttribute("categoryBean");
	        boolean success = categoryBean.insertCategory(newCategory);
	        if (success) {
	            // Category added successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category add failed
	            // Redirect or set an error message as needed
	        }
	    	
	    }else if (action != null && action.equals("delete_attribute")) {
	    	int attributeId = Integer.parseInt(request.getParameter("attributeId"));
	    	AttributeBean attributeBean = (AttributeBean) request.getSession().getAttribute("attributeBean");
	    	boolean success = attributeBean.deleteAttribute(attributeId);
	        if (success) {
	        	System.out.println("\n\n\nDeleted!\n\n\n");
	            // TODO Success
	        } else {
	        	System.out.println("\n\n\nNot Deleted!\n\n\n");
	            // TODO Error
	        }
	    }else if (action != null && action.equals("update_attribute")) {
	    	int attributeId = Integer.parseInt(request.getParameter("attributeId"));
	    	String attributeName = request.getParameter("editAttributeName");
	    	
	    	AttributeDTO updatedAttribute = new AttributeDTO();
	    	updatedAttribute.setId(attributeId);
	    	updatedAttribute.setName(attributeName);
	    	
	    	AttributeBean attributeBean = (AttributeBean) request.getSession().getAttribute("attributeBean");
	        boolean success = attributeBean.updateAttribute(updatedAttribute);
	        
	        if (success) {
	            // Category updated successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category update failed
	            // Redirect or set an error message as needed
	        }
	    }else if(action != null && action.equals("add_attribute")){
	    	String attributeName = request.getParameter("newAttributeName");
	    	System.out.println("\n\n\n\n"+request.getParameter("categoryId")+"\n\n\n\n\n");
	    	Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
	    	
	    	AttributeDTO newAttribute = new AttributeDTO();
	    	newAttribute.setName(attributeName);
	    	newAttribute.setCategoryId(categoryId);
	    	// Add the category
	        AttributeBean attributeBean = (AttributeBean) request.getSession().getAttribute("attributeBean");
	        boolean success = attributeBean.insertAttribute(newAttribute);
	        if (success) {
	            // Category added successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category add failed
	            // Redirect or set an error message as needed
	        }
	    	
	    }


	    response.sendRedirect(request.getContextPath());
	}

}
