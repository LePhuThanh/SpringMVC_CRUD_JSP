<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product list</title>
        <style>
            table,
            th,
            td {
                border: 1px solid black;
            }
        </style>
    </head>

    <body>
        <h1>Product</h1>
        <!-- display products -->
        <table>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.getProductID()}</td>
                    <td>${product.getProductName()}</td>
                    <td>$${product.getPrice()}</td>
                    <td>${product.getDescription()}</td>
                    <td>
                        <a href="../../products/changeCategory/${product.getProductID()}">
                            Update this Product
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="../../categories">
            Back
        </a>
    </body>

    </html>