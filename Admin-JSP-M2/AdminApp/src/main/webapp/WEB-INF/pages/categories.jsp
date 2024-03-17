<%@ page import="java.util.List" %>
<%@ page import="org.unibl.etf.model.dto.CategoryDTO" %>
<%@ page import="org.unibl.etf.model.dto.AttributeDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="categoryBean" type="org.unibl.etf.model.bean.CategoryBean" scope="session"></jsp:useBean>
<jsp:useBean id="attributeBean" type="org.unibl.etf.model.bean.AttributeBean" scope="session"></jsp:useBean>

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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        // Function to close attribute list modal when attempting to open edit attribute modal
        document.querySelectorAll('.edit-attribute-modal').forEach(modal => {
            modal.addEventListener('show.bs.modal', function () {
                // Close attribute list modal associated with the category
                var categoryId = this.getAttribute('data-category-id');
                var attributeListModal = document.getElementById('categoryAttributesModal' + categoryId);
                if (attributeListModal) {
                    var modalInstance = bootstrap.Modal.getInstance(attributeListModal);
                    modalInstance.hide();
                }
            });
        });

        // Function to close attribute list modal when edit attribute modal is closed
        document.querySelectorAll('.edit-attribute-modal').forEach(modal => {
            modal.addEventListener('hidden.bs.modal', function () {
                // No need to implement anything here since the modal is already closed before opening another modal
            });
        });
    });

    function submitEditCategoryForm(categoryId) {
        var formId = "editCategoryForm" + categoryId;
        document.getElementById(formId).submit();
    }
</script>
    
</head>

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
                <%
                List<CategoryDTO> categories = categoryBean.getAllCategories();
                %>
                <%
                if(categories != null) {
                %>
                    <%
                    for(int i = 0; i < categories.size(); i++) {
                        CategoryDTO category = categories.get(i);
                    %>
                        <tr>
                            <td><%= category.getId() %></td>
                            <td><%= category.getName() %></td>
                            <td class="btn-group">
                                <a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editCategoryModal<%= category.getId() %>">Edit</a>
                                <form id="deleteForm<%= category.getId() %>" action="Category" method="post">
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
    
    <!-- MODALS -->
    <% 
    for (CategoryDTO category : categories) {
        List<AttributeDTO> attributes = attributeBean.getAllAttributesByCategoryId(category.getId());
    %>
        <!-- Edit Category Modal -->
        <div class="modal fade" id="editCategoryModal<%= category.getId() %>" tabindex="-1" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editCategoryModalLabel">Edit Category</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editCategoryForm<%= category.getId() %>" onsubmit="submitEditCategoryForm(<%= category.getId() %>)" action="Category" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="categoryId" value="<%= category.getId() %>">
                            <div class="mb-3">
                                <label for="editCategoryName" class="form-label">Category Name</label>
                                <input type="text" class="form-control" id="editCategoryName" name="editCategoryName" value="<%= category.getName() %>">
                            </div>
                            <!-- Add other fields for updating category attributes if needed -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Attributes Table -->
        <div class="modal fade" id="categoryAttributesModal<%= category.getId() %>" tabindex="-1" aria-labelledby="categoryAttributesModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="categoryAttributesModalLabel">Attributes for <%= category.getName() %></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Attribute Name</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                if(attributes != null) {
                                    for(AttributeDTO attribute : attributes) { 
                                %>
                                        <tr>
                                            <td><%= attribute.getId() %></td>
                                            <td><%= attribute.getName() %></td>
                                            <td class="btn-group">
                                                <!-- Edit button for attribute -->
                                                <a href="#" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editAttributeModal<%= attribute.getId() %>">Edit</a>
                                                
                                                <!-- Delete button for attribute -->
                                                <form id="deleteAttributeForm<%= attribute.getId() %>" action="Attribute" method="post">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="attributeId" value="<%= attribute.getId() %>">
                                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this attribute?')">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                <% 
                                    }
                                } else { 
                                %>
                                    <tr>
                                        <td colspan="3">No attributes found.</td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Edit Attributes Modal -->
        <% 
        for (AttributeDTO attribute : attributes) {
        %>
              <div class="modal fade edit-attribute-modal" id="editAttributeModal<%= attribute.getId() %>" tabindex="-1" aria-labelledby="editAttributeModalLabel" aria-hidden="true" data-category-id="<%= category.getId() %>">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editCategoryModalLabel">Edit Attribute</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="editCategoryForm<%= attribute.getId() %>" onsubmit="submitEditCategoryForm(<%= attribute.getId() %>)" action="Attribute" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="categoryId" value="<%= attribute.getId() %>">
                                <div class="mb-3">
                                    <label for="editCategoryName" class="form-label">Attribute Name</label>
                                    <input type="text" class="form-control" id="editAttributeName" name="editAttributeName" value="<%= attribute.getName() %>">
                                </div>
                                <!-- Add other fields for updating category attributes if needed -->
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
    <% } %>
</body>
</html>
