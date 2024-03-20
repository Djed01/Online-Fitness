<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="userBean" type="org.unibl.etf.model.bean.UserBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link href="/YourApp/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
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
        <h1>User Management</h1>
        <div style="display: flex; flex-direction: row;">
            <h2>Existing Users</h2>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">City</th>
                    <th scope="col">Email</th>
                    <th scope="col">Activation</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<UserDTO> users = userBean.getAllUsers();
                %>
                <%
                if(users != null) {
                %>
                    <%
                    for(UserDTO user : users) {
                    %>
                        <tr>
                            <td><%= user.getId() %></td>
                            <td><%= user.getUsername() %></td>
                            <td><%= user.getName() %></td>
                            <td><%= user.getSurname() %></td>
                            <td><%= user.getCity() %></td>
                            <td><%= user.getEmail() %></td>
                            <td><%= user.getActivated() %></td>
                            <td class="btn-group">
                                <a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editUserModal<%= user.getId() %>">Edit</a>
                                <form id="deleteUserForm<%= user.getId() %>" action="User" method="post">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="userId" value="<%= user.getId() %>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this user?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>
    </div>
    
    <!-- ================== MODALS ================== -->
    <% 
    for (UserDTO user : users) {
    %>
        <!-- Edit User Modal -->
        <div class="modal fade" id="editUserModal<%= user.getId() %>" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editUserModalLabel">Edit User</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editUserForm<%= user.getId() %>" action="User" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="userId" value="<%= user.getId() %>">
                            <div class="mb-3">
                                <label for="editUsername" class="form-label">Username</label>
                                <input type="text" class="form-control" id="editUsername" name="editUsername" value="<%= user.getUsername() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editName" class="form-label">Name</label>
                                <input type="text" class="form-control" id="editName" name="editName" value="<%= user.getName() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editSurname" class="form-label">Surname</label>
                                <input type="text" class="form-control" id="editSurname" name="editSurname" value="<%= user.getSurname() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editCity" class="form-label">City</label>
                                <input type="text" class="form-control" id="editCity" name="editCity" value="<%= user.getCity() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="editEmail" name="editEmail" value="<%= user.getEmail() %>">
                            </div>
                            <div class="mb-3">
							    <label for="editActivation" class="form-label">Activation</label>
							    <div class="form-check">
							        <input class="form-check-input" type="checkbox" id="editActivation" name="editActivation" <% if (user.getActivated()) { %>checked<% } %>>
							        <label class="form-check-label" for="editActivation">
							            Activated
							        </label>
							    </div>
							</div>
                            
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <% } %>


</body>
</html>
