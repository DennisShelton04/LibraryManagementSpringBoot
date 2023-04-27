<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="ISO-8859-1">
    <title>Admin Page</title>
    <link rel="stylesheet" type="text/css" href="Styles/adminpage.css">
    <style>
      @charset "ISO-8859-1";

      /* CSS Reset */
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      /* Global Styles */
      body {
        font-family: Arial, sans-serif;
        font-size: 16px;
        line-height: 1.5;
        color: #333;
        background-color: #aef2f1;
        padding: 20px;
      }

      h3 {
        font-size: 24px;
        margin-bottom: 20px;
      }

      label {
        display: block;
        margin-bottom: 10px;
        font-size: 18px;
      }

      input[type="text"],
      input[type="password"],
      input[type="email"] {
        width: 50%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-bottom: 20px;
      }

      button[type="submit"],
      button[type="button"] {
        display: inline-block;
        background-color: #333;
        color: #fff;
        border: none;
        padding: 10px 20px;
        font-size: 18px;
        border-radius: 5px;
        cursor: pointer;
        margin-right: 10px;
      }

      button[type="submit"]:hover,
      button[type="button"]:hover {
        background-color: #555;
      }

      .error {
        color: red;
        margin-top: 10px;
      }

      .message-box {
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 10px;
        margin: 10px auto;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        width: 25%;
      }
    </style>


  </head>

  <body>





    <h3>Add User</h3>
    <form method="post" action="useradd">
      <label for="username">UserName:</label>
      <input type="text" id="username" name="username" required="required">
      <label for="emain">Email:</label>
      <input type="email" id="useremail" name="useremail" required="required">
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required="required">
      <button type="submit">Add User</button>

    </form>

    <br>
    <form action="userview">
      <button type="submit"> View </button>
    </form>
    <br>
    <br>
    <hr style=" height:2px;border-width:0;color:gray;background-color:gray">
    <br>
    <form action="deleteuser" method="post">
      <h3>Delete User</h3>
      <label for="deleteusername">UserName</label>
      <input type="text" name="deleteUser" required="required">
      <button type="submit">Delete User</button>
    </form>
    <div class="message-box">
      Message Box:
      <p>
        <% String message=(String)request.getAttribute("message"); %>
          <% if(message !=null) { %>
            <div class="error">
              <%= message %>
            </div>
            <% } %>
      </p>
    </div>
    <form action="/logout" method="post">
      <button type="submit">Logout</button>
    </form>

  </body>

  </html>