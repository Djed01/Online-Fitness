<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.dto.AdvisorDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="advisorBean" type="org.unibl.etf.model.bean.AdvisorBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Advisors</title>
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
        <h1>Advisors Management</h1>
        <div style="display: flex; flex-direction: row;">
            <h2>Existing Advisors</h2>
            <button style="margin-left:20px" type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#addAdvisorModal">
                Add New Advisor
            </button>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Email</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<AdvisorDTO> advisors = advisorBean.getAllAdvisors();
                %>
                <%
                if(advisors != null) {
                %>
                    <%
                    for(int i = 0; i < advisors.size(); i++) {
                        AdvisorDTO advisor = advisors.get(i);
                    %>
                        <tr>
                            <td><%= advisor.getId() %></td>
                            <td><%= advisor.getUsername() %></td>
                            <td><%= advisor.getName() %></td>
                            <td><%= advisor.getSurname() %></td>
                            <td><%= advisor.getEmail() %></td>
                            <td class="btn-group">
                                <a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editAdvisorModal<%= advisor.getId() %>">Edit</a>
                                <form id="deleteForm<%= advisor.getId() %>" action="Advisor" method="post">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="advisorId" value="<%= advisor.getId() %>">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this category?')">Delete</button>
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
    for (AdvisorDTO advisor : advisors) {
    %>
        <!-- Edit Advisor Modal -->
        <div class="modal fade" id="editAdvisorModal<%= advisor.getId() %>" tabindex="-1" aria-labelledby="editAdvisorModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editAdvisorModalLabel">Edit Advisor</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editAdvisorForm<%= advisor.getId() %>" action="Advisor" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="advisorId" value="<%= advisor.getId() %>">
                            <div class="mb-3">
                                <label for="editAdvisorUsername" class="form-label">Username</label>
                                <input type="text" class="form-control" id="editAdvisorUsername" name="editAdvisorUsername" value="<%= advisor.getUsername() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editAdvisorName" class="form-label">Name</label>
                                <input type="text" class="form-control" id="editAdvisorName" name="editAdvisorName" value="<%= advisor.getName() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editAdvisorSurname" class="form-label">Surname</label>
                                <input type="text" class="form-control" id="editAdvisorSurname" name="editAdvisorSurname" value="<%= advisor.getSurname() %>">
                            </div>
                            <div class="mb-3">
                                <label for="editAdvisorEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="editAdvisorEmail" name="editAdvisorEmail" value="<%= advisor.getEmail() %>">
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
    
    <!-- Add Advisor Modal -->
    <div class="modal fade" id="addAdvisorModal" tabindex="-1" aria-labelledby="addAdvisorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addAdvisorModalLabel">Add Advisor</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="addAdvisorForm" action="Advisor" method="post">
                        <input type="hidden" name="action" value="add">
                        <div class="mb-3">
                            <label for="newAdvisorUsername" class="form-label">Username</label>
                            <input type="text" class="form-control" id="newAdvisorUsername" name="newAdvisorUsername" required>
                        </div>
                        <div class="mb-3">
                            <label for="newAdvisorName" class="form-label">Name</label>
                            <input type="text" class="form-control" id="newAdvisorName" name="newAdvisorName" required>
                        </div>
                        <div class="mb-3">
                            <label for="newAdvisorSurname" class="form-label">Surname</label>
                            <input type="text" class="form-control" id="newAdvisorSurname" name="newAdvisorSurname" required>
                        </div>
                        <div class="mb-3">
                            <label for="newAdvisorEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="newAdvisorEmail" name="newAdvisorEmail" required>
                        </div>
                        <div class="mb-3">
                            <label for="newAdvisorPassword" class="form-label">Password</label>
                            <input type="password" class="form-control" id="newAdvisorPassword" name="newAdvisorPassword" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Add Advisor</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>
