<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Libraian Page</title>
		<link rel="stylesheet" type="text/css" href="Styles/libraianpage.css">
		<style>
			@charset "ISO-8859-1";

			body {
				font-family: Arial, sans-serif;
				background-color: #aef2f1;
			}

			h3 {
				color: #333;
				font-size: 24px;
				margin-top: 30px;
			}

			label {
				display: block;
				margin-top: 10px;
			}

			input[type="text"],
			input[type="date"] {
				display: block;
				width: 50%;
				padding: 10px;
				border: 1px solid #ccc;
				border-radius: 5px;
				margin-top: 5px;
				font-size: 16px;
				box-sizing: border-box;
			}

			input[type="submit"] {
				padding: 10px;
				background-color: #333;
				color: #fff;
				border: none;
				border-radius: 5px;
				cursor: pointer;
			}

			button[type="submit"] {
				padding: 10px;
				background-color: #333;
				color: #fff;
				border: none;
				border-radius: 5px;
				cursor: pointer;
			}

			button[type="button"] {
				padding: 10px;
				background-color: #333;
				color: #fff;
				border: none;
				border-radius: 5px;
				cursor: pointer;
			}

			.message-box {
				position: fixed;
				top: 50%;
				right: 0;
				transform: translateY(-50%);
				background-color: #f2f2f2;
				padding: 10px;
				border: 1px solid #ccc;
				width: 200px;
				text-align: center;
			}

			.login-button {
				position: absolute;
				top: 10px;
				right: 10px;
			}


			.message {
				color: green;
				font-weight: bold;
			}
		</style>
	</head>

	<body>



		<h3>Add Books</h3>
		<form method="POST" action="addingbook">
			<label for="book-name">Book Name:</label>
			<input type="text" id="book-name" name="bookName" required="required">

			<label for="book-author">Book Author:</label>
			<input type="text" id="book-author" name="bookAuthor" required="required">

			<label for="book-version">Published on:</label>
			<input type="date" id="book-publish" name="bookPublish" required="required">
			<label for="book-field">Field:</label>
			<input type="tel" id="book-field" name="bookField" required="required">
			<br>
			<br>
			<input type="submit" value="Add Book">

		</form>
		<br>
		<h3>View Books</h3>
		<form action="viewbook">
			<input type="submit" value="ViewBook">
		</form>
		<br>
		<br>
		<h3>Issue Books</h3>
		<form action="issuebook" method="post">
			<label for="issue-bookname">Book Name:</label>
			<input type="text" name="issueBookname" required="required">
			<label for="borrower-name">Borrower Name:</label>
			<input type="text" name="barrowerName" required="required">
			<label for="lib-id">User Id</label>
			<input type="text" name="libId" required="required">
			<label for="date-issue">Date Of Issue</label>
			<input type="date" name="dateOfissue" required="required">
			<button type="submit">Add</button>
		</form>
		<br>
		<h3>Issued Books View</h3>
		<form action="issuebookview">
			<input type="submit" value="Viewissuedbook">
		</form>
		<br>
		<h3>Return Books</h3>
		<form action="returnbook" method="post">
			<label for="book-name">Book Name</label>
			<input type="text" name="returnBookName" required="required">
			<label for="return-date">Date</label>
			<input type="date" name="returnDate" required="required">
			<button type="submit">Return</button>
		</form>
		<br>
		<form action="/logout" method="post">
			<button type="submit">Logout</button>
		</form>


		<div class="message-box"> Message Box:
			<p>${message}</p>
		</div>





	</body>

	</html>