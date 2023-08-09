<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
            <p>Do you really want to assign product:
                <strong>${product.getProductName()}

                </strong> to other category ?
            </p>
            <form:form method="POST"
                action="/api/v1/products/updateProduct/${product.getProductID()}"
                modelAttribute="product">

                <label for="Name">Name:</label><br>
                <form:input type = "text"
                    value = "${product.getProductName()}"
                    id = "Name"
                    placeholder = "Enter product's name"
                    path = "productName"
                /><br/>
                <form:errors path = "productName" cssClass= "error"/><br/>

                <label for="price">Price:</label><br>
                <form:input type = "number"
                    value = "${product.getPrice()}"
                    id = "price"
                    placeholder = "Enter product's price"
                    path = "price"
                /><br/>
                <form:errors path = "price" cssClass= "error"/><br/>

                <label for="description">Description:</label><br>
                <form:input type = "text"
                    value = "${product.getDescription()}"
                    id = "description"
                    placeholder = "Enter description"
                    path = "description"
                /><br/>
                <form:errors path = "description" cssClass= "error"/><br/>

                <form:select path="categoryID">
                    <c:forEach var="category" items="${categories}">
                        <form:option value="${category.getCategoryID()}">
                            ${category.getCategoryName()}
                        </form:option>
                    </c:forEach>
                </form:select>
                <input type="submit" value="Update" />
            </form:form>


        </body>
</html>