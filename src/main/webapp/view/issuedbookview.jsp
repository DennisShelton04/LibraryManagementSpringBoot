<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@page import="java.util.SortedSet" %>
		<%@page import="java.util.List" %>
			<%@page import="org.json.simple.*, org.json.simple.parser.*" %>
				<%@page import="java.io.FileReader" %>
					<%@page import="java.io.FileNotFoundException, java.io.IOException" %>
						<!DOCTYPE html>
						<html>

						<head>
							<meta charset="ISO-8859-1">
							<title>Issued Book</title>
							<link rel="stylesheet" type="text/css" href="Styles/view.css">
							<style>
								@charset "ISO-8859-1";

								/* Apply default styles to all elements */
								* {
									box-sizing: border-box;
									margin: 0;
									padding: 0;
								}

								/* Apply styles to the body */
								body {
									font-family: Arial, sans-serif;
									font-size: 16px;
									line-height: 1.5;
									background-color: #aef2f1;
								}

								/* Apply styles to the form */
								form {
									margin-bottom: 20px;
								}

								input[type="submit"] {
									padding: 10px;
									background-color: #333;
									color: #fff;
									border: none;
									border-radius: 5px;
									cursor: pointer;
								}

								/* Apply styles to the table */
								table {
									width: 100%;
									border-collapse: collapse;
									margin-bottom: 20px;
								}

								/* Apply styles to the table head */
								thead {
									background-color: #333;
									color: #fff;
								}

								/* Apply styles to the table head cells */
								th {
									padding: 10px;
								}

								/* Apply styles to the table body cells */
								td {
									padding: 10px;
									border: 1px solid #ccc;
								}

								/* Apply styles to the button */
								button {
									padding: 10px;
									background-color: #333;
									color: #fff;
									border: none;
									border-radius: 5px;
									cursor: pointer;
								}

								/* Apply styles to the button on hover */
								button:hover {
									background-color: #555;
								}
							</style>
						</head>

						<body>


							<div>
								<% String BookPath=(String)request.getAttribute("bookpath"); List<String> key
									=(List)request.getAttribute("Keys");

									JSONObject jobj=null;
									if(key !=null){

									JSONParser parse=new JSONParser();
									try(FileReader read=new FileReader(BookPath)){
									Object obj=parse.parse(read);
									JSONObject jsonobj=(JSONObject) obj;
									// System.out.println(array);
									jobj=jsonobj;
									} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();

									}
									}
									%>
									<%if(key !=null){%>
										<table>
											<thead>
												<tr>
													<th>Issued Book Name</th>
													<th>Borrower Name</th>
													<th>ID</th>
													<th>Date Of Issued</th>
												</tr>
											</thead>
											<tbody>
												<% for(String k:key){ JSONObject view=(JSONObject) jobj.get(k); String
													BookName=(String)view.get("IssuedBookName"); String
													BookAuthor=(String)view.get("BarrowerName"); String
													BookPublish=(String)view.get("LibId"); String
													BookField=(String)view.get("IssuedDate"); %>
													<tr>
														<td>
															<%= BookName %>
														</td>
														<td>
															<%= BookAuthor %>
														</td>
														<td>
															<%= BookPublish %>
														</td>
														<td>
															<%= BookField %>
														</td>

													</tr>
													<% }%>
											</tbody>
										</table>

										<% }%>
							</div>
							<br>
							<button type="button" onclick="location.href='librarianpage'">Return</button>

						</body>

						</html>