<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libraian Login</title>
<link rel="stylesheet" type="text/css" href="Styles/libraianlogin.css">
</head>
<body>
	<h1>Libraian Login</h1>
    <form method="POST" action="libraianlogin">
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required="required"><br><br>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required="required"><br><br>
      <button type="submit" name="submit">Submit</button>
      <button type="button" onclick="location.href='adminlogin'">Admin</button>
      <br><br>
     
    </form>
    ${message}
    
</body>
</html>