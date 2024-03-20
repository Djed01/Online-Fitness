<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.dto.LogDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="logBean" type="org.unibl.etf.model.bean.LogBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link href="/AdminApp/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <style>
        .btn-group {
            display: inline-block;
        }
        .btn-group .btn {
            margin-right: 5px;
        }
    </style>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
        <h1>Application Logs</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Level</th>
                    <th scope="col">Time</th>
                    <th scope="col">Description</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<LogDTO> logs = logBean.getAllLogs(); // Retrieve logs from backend
                int pageSize = 10; // Number of logs per page
                int totalPages = (logs.size() + pageSize - 1) / pageSize;
                int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
                int start = (currentPage - 1) * pageSize;
                int end = Math.min(start + pageSize, logs.size());
                
                for (int i = start; i < end; i++) {
                    LogDTO log = logs.get(i);
                %>
                    <tr>
                        <td><%= log.getLevel() %></td>
                        <td><%= log.getTime() %></td>
                        <td><%= log.getDescription() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <% for (int i = 1; i <= totalPages; i++) { %>
                    <li class="page-item <%= currentPage == i ? "active" : "" %>">
                        <a class="page-link" href="?page=<%= i %>"><%= i %></a>
                    </li>
                <% } %>
            </ul>
        </nav>
    </div>
</body>
</html>
