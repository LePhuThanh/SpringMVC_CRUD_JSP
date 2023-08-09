<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
            <h1>Insert new product<h1/>
                <form:form method="POST"
                    action="/api/v1/products/insertProduct"
                    modelAttribute="product">

                    <label for="Name">Name:</label><br>
                    <form:input type = "text"
                        id = "Name"
                        placeholder = "Enter product's name"
                        path = "productName"
                    /><br/>
                    <form:errors path = "productName" cssClass= "error"/><br/>

                    <label for="price">Price:</label><br>
                    <form:input type = "number"
                        id = "price"
                        placeholder = "Enter product's price"
                        path = "price"
                    /><br/>
                    <form:errors path = "price" cssClass= "error"/><br/>

                    <label for="description">Description:</label><br>
                    <form:input type = "text"
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
                    <p class = "error">${error}</p>
                    <input type="submit" value="Insert" />
                </form:form>
        </body>

</html>