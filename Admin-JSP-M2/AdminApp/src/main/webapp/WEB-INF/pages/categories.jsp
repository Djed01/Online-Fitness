<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.dto.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="categoryBean" type="org.unibl.etf.model.bean.CategoryBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categories</title>
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
</head>

<script>
    document.querySelectorAll('.btn-danger').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            if (confirm('Are you sure you want to delete this category?')) {
                this.closest('form').submit();
            }
        });
    });
</script>


<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
        <h1>Categories Management</h1>
        <h2>Existing Categories</h2>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Category Name</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% List<Category> categories = categoryBean.getAllCategories(); %>
                <% if(categories != null) { %>
                    <% for(int i = 0; i < categories.size(); i++) { %>
                        <% Category category = categories.get(i); %>
                        <tr>
                            <td><%= category.getId() %></td>
                            <td><%= category.getName() %></td>
                             <td class="btn-group">
                               <a href="EditCategoryServlet?id=<%= category.getId() %>" class="btn btn-warning">Edit</a>
                                <form id="deleteForm" action="Category" method="post">
								    <input type="hidden" name="action" value="delete">
								    <input type="hidden" name="categoryId" value="<%= category.getId() %>">
								    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this category?')">Delete</button>
								</form>

                                <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#categoryAttributesModal<%= category.getId() %>">
                                    Attributes
                                </button>
                            </td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
