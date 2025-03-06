<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.teachmeskills.market.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of all users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .table-container {
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .table {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Users List</h2>

    <div class="button-container">
        <a href="/register/users/create" class="btn btn-primary mx-2">Create</a>
        <a href="/register/users/update" class="btn btn-warning mx-2">Update</a>
        <a href="/register/users/delete" class="btn btn-danger mx-2">Delete</a>
        <a href="/auth/logout" class="btn btn-secondary mx-2">Logout</a>
    </div>

    <div class="table-container">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Sex</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Created Date</th>
                    <th>Deleted</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null && !users.isEmpty()) {
                        for (User  user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getFirstname() %></td>
                    <td><%= user.getSecondName() %></td>
                    <td><%= user.getSex() != null ? user.getSex() : "N/A" %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getTelephoneNumber() %></td>
                    <td><%= user.getCreated() != null ? user.getCreated().toString() : "N/A" %></td>
                    <td><%= user.getIsDeleted() ? "Yes" : "No" %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-center">No users to display</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>