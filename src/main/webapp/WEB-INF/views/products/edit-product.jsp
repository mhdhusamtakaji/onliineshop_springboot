<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <!-- Include CSS/JS libraries -->
</head>
<body>
    <h1>Edit Product</h1>
    <!-- Form for editing an existing product -->
    <form action="${pageContext.request.contextPath}/products/${product.id}" method="post">
        <label>Name:</label>
        <input type="text" name="name" value="${product.name}" required>
        <label>Description:</label>
        <input type="text" name="description" value="${product.description}">
        <label>Price:</label>
        <input type="number" name="price" step="0.01" value="${product.price}" required>
        <button type="submit">Save Changes</button>
    </form>
</body>
</html>