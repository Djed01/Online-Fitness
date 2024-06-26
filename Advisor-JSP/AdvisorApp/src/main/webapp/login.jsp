<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="org.unibl.etf.model.bean.AdvisorBean"%>
<%@ page import="org.unibl.etf.model.dto.AdvisorDTO"%>


<%
	    // Initialize variables
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String errorMessage = "";
	

	    if (request.getMethod().equals("POST")) {

	        AdvisorBean advisorBean = new AdvisorBean();
	        AdvisorDTO advisorDTO = advisorBean.login(username, password);
	

	        if (advisorDTO != null && advisorDTO.isLoggedIn()) {
	        	System.out.println("IMA");
	        	session.setAttribute("advisor", advisorDTO);
	            // Redirect to inbox page
	            response.sendRedirect("inbox.jsp");
	        } else {
	        	System.out.println("NEMA");
	            errorMessage = "Invalid username or password";
	        }
	    }
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Advisor App</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; 
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            max-width: 400px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff; 
        }

        .form-group {
            position: relative;
            margin-bottom: 20px;
        }

        .form-group .icon {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: 15px;
        }

        .form-control {
            padding-left: 40px; 
            border-radius: 20px;
            padding: 15px;
            padding-left: 40px;
        }

        .btn-primary {
        	color: white;
            border-radius: 20px;
            padding: 15px;
            width: 100%;
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3; 
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2><i class="fas fa-user icon"></i> Advisor Login</h2>
        <% if (!errorMessage.isEmpty()) { %>
            <div class="alert alert-danger"><%= errorMessage %></div>
        <% } %>
        <form action="?action=login" method="post">
            <div class="form-group">
                <i class="fas fa-user icon"></i>
                <input type="text" class="form-control" name="username" placeholder="Username">
            </div>
            <div class="form-group">
                <i class="fas fa-lock icon"></i>
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary"><i class="fas fa-sign-in-alt"></i> Login</button>
        </form>
    </div>
</body>
</html>