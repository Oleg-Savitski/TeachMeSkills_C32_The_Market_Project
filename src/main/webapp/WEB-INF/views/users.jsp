<%@ page import="com.teachmeskills.market.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .table {
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">User  List</h2>

    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
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
                <td><%= user.getEmail() %></td>
                <td><%= user.getTelephoneNumber() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" class="text-center">No users to display</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>