<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
    <!-- Include any necessary CSS/JS libraries -->
</head>
<body>
    <h1>Products</h1>
    <c:choose>
        <c:when test="${not empty products}">
            <ul>
                <c:forEach items="${products}" var="product">
                    <li>${product.name}</li>
                    <!-- Add more product details as needed -->
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No products found.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
