<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.FleetX.model.CartModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - FleetX</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #2d2b2b;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }
        h1, h2 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .order-table table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .order-table th, .order-table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        .order-table th {
            background-color: #f4f4f4;
            color: #333;
        }
        .order-table img {
            width: 90px;
            height: auto;
            border-radius: 4px;
        }
        .cart-total {
            display: flex;
            justify-content: space-between;
            font-size: 20px;
            font-weight: bold;
            padding: 15px 0;
            border-top: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
            margin-bottom: 20px;
        }
        .submit-btn {
            display: block;
            width: 100%;
            padding: 15px;
            background: #018f48;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background: #488561;
        }
        .back-btn {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #2d2d2b;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Checkout</h1>

    <div class="checkout-summary">
        <h2>Order Summary</h2>
        <c:if test="${not empty cartItems}">
            <div class="order-table">
                <table>
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Vehicle Name</th>
                            <th>Rental Day</th>
                            <th>Per Day Rate (Rs.)</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td><img src="${contextPath}/assets/vehicle/${item.imageUrl}" alt="${item.model}" /></td>
                                <td>${item.model}</td>
                                <td>${item.rentalDays}</td>
                                <td>${item.dailyRate}</td>
                                <td>${item.dailyRate * item.rentalDays}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="4">Total</th>
                            <td>Rs. <%= request.getAttribute("totalValue") %></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </c:if>

        <div class="cart-total">
            <div>
                <span>Total Amount:</span>
                <span>Rs. <%= request.getAttribute("totalValue") %></span>
            </div>
        </div>
    </div>

    <form action="ProcessOrder" method="POST">
        <button type="submit" class="submit-btn">Place Order</button>
    </form>

    <a href="cart" class="back-btn">Back to Cart</a>
</div>
</body>
</html>
