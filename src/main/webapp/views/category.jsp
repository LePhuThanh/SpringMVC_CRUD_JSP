<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Category list</title>
        <style>
            table,
            th,
            td {
                border: 1px solid black;
            }
        </style>
    </head>

    <body>
        <h1>Category</h1>
        <!-- <h3>name = ${name}, age = ${age}</h3> -->
        <!-- display categories -->
        <table>
            <tr>
                <th>Category ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.getCategoryID()}</td>
                    <td>${category.getCategoryName()}</td>
                    <td>${category.getDescription()}</td>
                    <td>
                        <a href="products/getProductByCategoryID/${category.getCategoryID()}">
                            Show products
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href ="products/insertProduct" >Insert new Product</a>
    </body>

    </html>