<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	if ("logout".equals(request.getParameter("action"))) {
		 session.invalidate();
		 response.sendRedirect("login.jsp");
	}
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin App</title>
    <!-- Add Font Awesome CDN -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <!-- Add Bootstrap CSS CDN -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Style the navbar background and links */
        .navbar {
            background-color: #2c3e50; /* Change to your preferred background color */
        }

        .navbar-brand,
        .navbar-nav .nav-link {
            color: #ffffff; /* Change to your preferred text color */
        }

        .navbar-brand:hover,
        .navbar-nav .nav-link:hover {
            color: #f8b400; /* Change to your preferred hover color */
        }

        /* Style the navbar toggler icon */
        .navbar-toggler-icon {
            background-color: #ffffff; /* Change to your preferred toggler icon color */
        }

        /* Style the navbar toggler button */
        .navbar-toggler {
            border-color: #ffffff; /* Change to your preferred toggler button color */
        }

        /* Optional: Add padding to the navbar items */
        .navbar-nav .nav-link {
            padding: 0.5rem 1rem;
        }
    </style>
</head>
<body>

<header class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid justify-content-center">
        <a class="navbar-brand" href="inbox.jsp" style="color: #ffffff;"><i class="fas fa-dumbbell"></i> Admin App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            
        </div>
        <div class="navbar-nav">
        
        	<form id="logoutForm" action="navbar.jsp" method="post">
            	<input type="hidden" name="action" value="logout">      
                <button type="submit" class="btn btn-link nav-link text-white">
			        <i class="fas fa-sign-out-alt"></i> Logout
			    </button>
            </form>
          
        </div>
    </div>
</header>

<!-- Add Bootstrap JS CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
