<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #e9f5fb;
        }
        .registration-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        label {
            font-style: italic;
            color: #333;
        }
    </style>
    <script>
        function validateAge() {
            const ageInput = document.getElementById('age');
            const age = parseInt(ageInput.value); //метод не забыть.
            if (age < 12) {
                alert("Registration is not allowed for users under 12 years old.");
                return false;
            }
            return true;
        }

        function validatePassword() {
            const passwordInput = document.getElementById('password');
            const password = passwordInput.value; // метод не забыть.
            if (password.length < 8) {
                alert("Password must be at least 8 characters long.");
                return false;
            }
            return true;
        }

        function validateForm() {
            return validateAge() && validatePassword();
        }
    </script>
</head>
<body>
<div class="registration-container mt-5">
    <h2 class="text-center">Registration</h2>
    <form action="registration" method="POST" class="mt-4" onsubmit="return validateForm();">
        <div class="form-group">
            <label for="firstname">First name:</label>
            <input type="text" id="firstname" name="firstname" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="secondName">Second name:</label>
            <input type="text" id="secondName" name="secondName" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Sex:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="male" name="sex" value="male" required>
                <label class="form-check-label" for="male">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="female" name="sex" value="female" required>
                <label class="form-check-label" for="female">Female</label>
            </div>
        </div>

        <div class="form-group">
            <label for="telephoneNumber">Telephone number:</label>
            <div class="input-group">
                <span class="input-group-text">+ 375</span>
                <select class="form-select" id="operator" name="operator" required>
                    <option value="29">29</option>
                    <option value="44">44</option>
                    <option value="25">25</option>
                    <option value="33">33</option>
                </select>
                <input type="text" id="telephoneNumber" name="telephoneNumber" class="form-control" placeholder="XXX-XX-XX" required>
            </div>
        </div>

        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" id="login" name="login" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Register</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>