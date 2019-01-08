<%--
  Created by IntelliJ IDEA.
  User: Marc Affolter
  Date: 01.12.2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Todo App</title>
		
		<style>
			html, body {font-family: calibri; margin: 0; padding: 0; height: 100%;}
			#container {background: white; position: relative; min-height: 100%;}
			#header {background: orange; position: fixed; top: 0; width: 100%; height: 100px; overflow: hidden;}
			#body {background: white; padding-top: 100px; padding-bottom: 20px}
			#footer {clear: both; background: orange; position: absolute; height: 20px; bottom: 0; width: 100%;}

			.inhalt {width: 90%; margin-top:0; margin-left:5%;}
			.inhalt_header {vertical-align: middle; line-height: 50px;}
			.inhalt_header_font {margin: 0; padding: 0;}
			.inhalt_footer {text-align: center; vertical-align: middle;}	
			.inhalt_footer_font {margin: 0; padding: 0; }

			@media only screen and (min-width: 751px) {
				#body {padding-bottom: 30px;}
				#footer {height: 30px;}
				.inhalt_footer {height:20px; margin-top:5px;}
			}
			
			@media only screen and (min-width: 401px) and (max-width: 750px) {
				#body {padding-bottom: 50px;}
				#footer {height: 50px;}
				.inhalt_footer {height:40px; margin-top:5px;}
			}
			
			@media only screen and (max-width: 400px) {
				#body {padding-bottom: 70px;}
				#footer {height: 70px;}
				.inhalt_footer {height:60px; margin-top:5px;}
			}
			
			@media only screen and (max-width: 799px) {
				.todo {width: 100%;}
			}

			@media only screen and (min-width: 800px) and (max-width: 1199px) {
				.todo {width: 50%;}
			}

			@media only screen and (min-width: 1200px){
				.todo {width: 33.33%;}
			}

			.neu_filter {margin: auto; text-align: center;}
			
			@media only screen and (min-width: 700px) {
				.neu_filter {width: 700px;}
			}
			
			@media only screen and (max-width: 699px) {
				.neu_filter {width: 100%;}
			}
			ul.nav {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				background-color: #333;
			}
			li.nav {
				float: left;
			}
			li a.nav{
				display: block;
				color: white;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}

			li a:hover.nav{
				background-color: #111;
			}

			.neu_filter_label {background: orange; border-radius: 10px;}
			.neu_filter_form {border-style: solid; border-width: 3px; border-color: orange; padding: 10px; border-radius: 10px;}

			.todo_rahmen {content: ""; clear: both; display: table; width: 100%;}
			.todo {height: 100%; float: left;}

			.todo_inhalt {width: 80%; height: 80%; margin-left: 10%; margin-top: 10px;}
			.todo_inhalt_text {border-style: solid; border-width: 3px; border-color: orange; padding: 5px; border-radius: 10px;}

			.buttonToDo {background-color: Transparent; background-repeat:no-repeat; border: none; cursor:pointer; overflow: hidden; outline:none;}

		</style>


		<script type="text/javascript">
			function myFunction(input) {
				if (input == 1) {
					var x = document.getElementById("neues_todo");
				}
				if (input == 2) {
					var x = document.getElementById("filtern_todo");
				}

				if (x.style.display === "none") {
					x.style.display = "block";
				} else {
					x.style.display = "none";
				}
			}
		</script>
		
	</head>
	
	<body>
	<%--Beginn Header--%>
	<div id="container">
		<div id="header">


			<div class = "inhalt inhalt_header" action="todoListNew.do" method="get">
				<h1 class = "inhalt_header_font" style = "float:left;" >Todos of ${currentUser.getName()}</h1>
				<div class = "inhalt_header_font" style = "float:right;">

				</div>
			</div>
			<br>
			<br>
			<br>

			<ul class="nav">
				<li class="nav"><a href="todoListNew.do" class="nav">View Todo's</a></li>
				<li class="nav"><a href="newTodo.do" class="nav">Create New Todo</a></li>
				<li style="float:right" class="nav">
					<form action="registrieren.jsp" method="post">
						<input type = "image" name = "submit" src = "pictures/logoff_white.png" border = "0" alt = "Submit" style = "height: 35px;" />
					</form>
				</li>
			</ul>
		</div>
	<%--End Header / Beginn Body --%>

		<div id="body">
		
			<div class = "inhalt">

				<section>
					<div class = "neu_filter">
						<div class = "neu_filter_label" onclick="myFunction(2)" style="cursor:pointer;">
							<h1>Filter per category</h1>
						</div>
						<div class = "neu_filter_form" id = "filtern_todo" style="display: none;">
							<form action="neuesToDo.jsp" method="post">
								<input type="submit" value="ToDo's filtern"/>
							</form>
						</div>
					</div>
				</section>
				
				<section>
					<div class = "todo_rahmen">
							<c:forEach items="${todoList}" var="element">
							<div class = "todo">
								<div class = "todo_inhalt">
									<div class = "todo_inhalt_text">
										<p>${element.getTitle()}</p>
										<hr>
										<ul style = "columns: 2;">
											<li>category: </li>
											<li>important?: </li>
											<li>overdue?: </li>
											<li>due date: </li>
											<li>${element.getCategory()}</li>
											<li>${element.isImportant()}</li>
											<li>${element.isCompleted()}</li>
											<li>${element.getFormattedDate()}</li>
										</ul>
										<hr>
										<div style = "width: 100%; text-align: center;">
											<div style = "display: inline-block; width: 30%;">
												<c:choose>
													<c:when test="${element.isCompleted()}">
														<form action="MarkUncompletedNew.do" method="post">
															<button class = "buttonToDo" type="submit" name="complete" value="${element.getId()}"><img src="pictures/check_2_uncomplete.png" alt="Submit" height="50px"></button>
														</form>
													</c:when>
													<c:otherwise>
														<form action="MarkCompletedNew.do" method="post">
															<button class = "buttonToDo" type="submit" name="complete" value="${element.getId()}"><img src="pictures/check_2_complete.png" alt="Submit" height="50px"></button>
														</form>
													</c:otherwise>
												</c:choose>
											</div>
											<div style = "display: inline-block; width: 30%">
												<form action="registrieren.jsp" method="post">
													<input type = "image" name = "submit" src = "pictures/check_2_edit.png" border = "0" alt = "Submit" style = "height: 50px;" />
												</form>
											</div>
											<div style = "display: inline-block; width: 30%">
												<form action="DeleteNew.do" method="post">
													<button class = "buttonToDo" type="submit" name="delete" value="${element.getId()}"><img src="pictures/delete_2_delete.png" alt="Submit" height="50px"></button>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>

					</div>
				</section>
				<br/>
			</div>			
		</div>
	<%--End Body / Beginn Footer --%>
		<div id="footer">
			<div class = "inhalt inhalt_footer">
				<p class = "inhalt_footer_font">&copy Copyright 2018. All rights reserves. Powered by the BFH - CAS Software Development.</p>
			</div>
		</div>
	</div>
	<%--End Footer --%>

	</body>
</html>