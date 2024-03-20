package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.model.bean.LoginBean;
import org.unibl.etf.model.dto.AdminDTO;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginBean loginBean = new LoginBean();
    	HttpSession session = request.getSession();
    	session.setAttribute("loginBean", loginBean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action != null && action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
	    	
			LoginBean loginBean = (LoginBean)request.getSession().getAttribute("loginBean");
			AdminDTO admin = loginBean.login(username, password);
			if(admin.isLoggedIn()) {
				HttpSession session = request.getSession();
		    	session.setAttribute("admin", admin);
		    	response.sendRedirect("Category");
			}else {
				response.sendRedirect("Login");
			}
		}else if (action != null && action.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("Login");
			return;
		}
		else {
			//response.sendRedirect("Login");
		}
	}
}


