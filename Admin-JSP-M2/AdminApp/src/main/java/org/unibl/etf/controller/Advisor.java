package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.model.bean.AdvisorBean;
import org.unibl.etf.model.dto.AdminDTO;
import org.unibl.etf.model.dto.AdvisorDTO;


/**
 * Servlet implementation class Advisor
 */
@WebServlet("/Advisor")
public class Advisor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advisor() {
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
		
		AdvisorBean advisorBean = new AdvisorBean();
    	session.setAttribute("advisorBean", advisorBean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advisors.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if (action != null && action.equals("delete")) {
	        int advisorId = Integer.parseInt(request.getParameter("advisorId"));
	        AdvisorBean advisorBean = (AdvisorBean) request.getSession().getAttribute("advisorBean");
	        boolean success = advisorBean.deleteAdvisor(advisorId);
	        if (success) {
	        	System.out.println("\n\n\nDeleted!\n\n\n");
	            // TODO Success
	        } else {
	        	System.out.println("\n\n\nNot Deleted!\n\n\n");
	            // TODO Error
	        }
	    }else if (action != null && action.equals("update")) {
	        int advisorId = Integer.parseInt(request.getParameter("advisorId"));
	        String username = request.getParameter("editAdvisorUsername");
	        String name = request.getParameter("editAdvisorName");
	        String surname = request.getParameter("editAdvisorSurname");
	        String email = request.getParameter("editAdvisorEmail");
	        

	        AdvisorDTO advisorDTO = new AdvisorDTO();
	        advisorDTO.setId(advisorId);
	        advisorDTO.setUsername(username);
	        advisorDTO.setName(name);
	        advisorDTO.setSurname(surname);
	        advisorDTO.setEmail(email);

	        // Update the category
	        AdvisorBean advisorBean = (AdvisorBean) request.getSession().getAttribute("advisorBean");
	        boolean success = advisorBean.updateAdvisor(advisorDTO);

	        if (success) {
	            // Category updated successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category update failed
	            // Redirect or set an error message as needed
	        }
	    }else if(action != null && action.equals("add")){
	        String username = request.getParameter("newAdvisorUsername");
	        String name = request.getParameter("newAdvisorName");
	        String surname = request.getParameter("newAdvisorSurname");
	        String email = request.getParameter("newAdvisorEmail");
	        String password = request.getParameter("newAdvisorPassword");
	        

	        AdvisorDTO advisorDTO = new AdvisorDTO();
	        advisorDTO.setUsername(username);
	        advisorDTO.setName(name);
	        advisorDTO.setSurname(surname);
	        advisorDTO.setEmail(email);
	        advisorDTO.setPassword(password);
	    	// Add the category
	        AdvisorBean advisorBean = (AdvisorBean) request.getSession().getAttribute("advisorBean");
	        boolean success = advisorBean.addNewAdvisor(advisorDTO);
	        if (success) {
	            // Category added successfully
	            // Redirect or set a success message as needed
	        } else {
	            // Category add failed
	            // Redirect or set an error message as needed
	        }
	    	
	    }
		
		response.sendRedirect(request.getContextPath() + "/Advisor");
	}

}
