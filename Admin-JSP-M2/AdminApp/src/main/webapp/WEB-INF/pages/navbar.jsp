<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin App</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
   
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        
        .navbar {
            background-color: #2c3e50; 
        }

        .navbar-brand,
        .navbar-nav .nav-link {
            color: #ffffff; 
        }

        .navbar-brand:hover,
        .navbar-nav .nav-link:hover {
            color: #f8b400;
        }

       
        .navbar-toggler-icon {
            background-color: #ffffff; 
        }

       
        .navbar-toggler {
            border-color: #ffffff;
        }

       
        .navbar-nav .nav-link {
            padding: 0.5rem 1rem;
        }
    </style>
</head>
<body>

<header class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid justify-content-center">
        <a class="navbar-brand" href="#"><i class="fas fa-dumbbell"></i> Admin App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="Category"><i class="fas fa-list"></i> Categories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="User"><i class="fas fa-users"></i> Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Advisor"><i class="fas fa-user-tie"></i> Advisors</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Log"><i class="fas fa-chart-bar"></i> Statistics</a>
                </li>
            </ul>
        </div>
        <div class="navbar-nav">
        
        	<form id="logoutForm" action="Login" method="post">
            	<input type="hidden" name="action" value="logout">      
                <button type="submit" class="btn btn-link nav-link text-white">
			        <i class="fas fa-sign-out-alt"></i> Logout
			    </button>
            </form>
          
        </div>
    </div>
</header>


<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
