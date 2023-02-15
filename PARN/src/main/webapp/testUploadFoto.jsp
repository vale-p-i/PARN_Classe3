<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
Choose a profile picture:
<form action="UploadImage" method="post" enctype="multipart/form-data">
    <input type="file" id="image" name="image" accept="image/png, image/jpeg">
    <input type="text" id="email" name="email">
    <input type="submit">
</form>
</body>
</html>
