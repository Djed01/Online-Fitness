<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" type="org.unibl.etf.model.bean.LoginBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin App</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
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
            color: #007bff; /* Primary color */
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
            padding-left: 40px; /* Adjust padding to accommodate icon */
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
            background-color: #0056b3; /* Darker hover color */
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2><i class="fas fa-user icon"></i> Admin Login</h2>
        <form action="Login" method="post">
        	<input type="hidden" name="action" value="login">
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
