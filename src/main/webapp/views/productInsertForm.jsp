<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Product</title>
</head>

<body>
    <h1>Insert Product</h1>
    <form action="insert" method="post">
        <label for="lname">Name:</label><br>
        <input type="text" id="lname" name="lname"><br>
        <label for="lprice">Price:</label><br>
        <input type="text" id="lprice" name="lprice"><br>
        <label for="ldescription">Description:</label><br>
        <input type="text" id="ldescription" name="ldescription"><br>
        <label for="lcategory">Category:</label><br>
        <input type="text" id="lcategory" name="lcategoryID"><br>
        <input type="submit" value="Submit">
    </form>
</body>

</html>