<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <!-- Include CSS/JS libraries -->
</head>
<body>
    <h1>Add Product</h1>
    <!-- Form for adding a new product -->
    <form action="${pageContext.request.contextPath}/products" method="post">
        <label>Name:</label>
        <input type="text" name="name" required>
        <label>Description:</label>
        <input type="text" name="description">
        <label>Price:</label>
        <input type="number" name="price" step="0.01" required>
        <button type="submit">Add Product</button>
    </form>
</body>
</html>