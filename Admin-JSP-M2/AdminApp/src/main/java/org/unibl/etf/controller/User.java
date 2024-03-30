package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.model.bean.UserBean;
import org.unibl.etf.model.dto.AdminDTO;
import org.unibl.etf.model.dto.UserDTO;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
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
		
		UserBean userBean = new UserBean();
    	session.setAttribute("userBean", userBean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/users.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if (action != null && action.equals("delete")) {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
	        boolean success = userBean.deleteUser(userId);
	        if (success) {
	        	System.out.println("\n\n\nDeleted!\n\n\n");
	            // TODO Success
	        } else {
	        	System.out.println("\n\n\nNot Deleted!\n\n\n");
	            // TODO Error
	        }
	    }else if (action != null && action.equals("update")) {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        String username = request.getParameter("editUsername");
	        String name = request.getParameter("editName");
	        String surname = request.getParameter("editSurname");
	        String city = request.getParameter("editCity");
	        String email = request.getParameter("editEmail");
	        
	        // Parse activation status
	        boolean activated = "on".equals(request.getParameter("editActivation"));
	        

	        UserDTO userDTO = new UserDTO();
	        userDTO.setId(userId);
	        userDTO.setUsername(username);
	        userDTO.setName(name);
	        userDTO.setSurname(surname);
	        userDTO.setCity(city);
	        userDTO.setEmail(email);
	        userDTO.setActivated(activated);

	        // Update the category
	        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
	        userBean.updateUser(userDTO);


	    }
	    
	    response.sendRedirect(request.getContextPath() + "/User");

	}

}
