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

<!-- Originaler Counter Teil
<head>
    <meta charset="UTF-8">
    <title>Counter</title>
</head>
<body>
<h1>Counter</h1>
<form action="counter.do" method="post">
    Counter: ${counter}
    <br/><br/>
    <input type="submit" value="Increment"/>
</form>
</body>
</html>
-->

<head>
    <meta charset = "UTF-8">
    <title>Deine ToDo's</title>

    <style>
        html, body {background: pink; margin: 0; padding: 0; height: 100%;}
        #container {background: white; position: relative; min-height: 100%;}
        #header {background: gray; position: fixed; top: 0; width: 100%; height: 50px; overflow: hidden;}
        #body {background: white; padding-top: 50px;}
        #footer {background: gray; position: absolute; bottom: 0; width: 100%;}

        .inhalt {width: 90%; margin-top:0; margin-left:5%; background: green;}

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
        .test {content:"";clear:both;display:table;}

        .todo {background: yellow; height: 100px; width: 31%; float: left; padding: 10px; 	border-width: 10px;
            border-style: solid;
            border-color: black;

            font-family: arial;}

        .todo_overdue {background: red; height: 100px; width: 31%; float: left; padding: 10px; 	border-width: 10px;
            border-style: solid;

            border-color: black;
            font-family: arial;}

        .todo_important {background: yellow; height: 100px; width: 31%; float: left; padding: 10px; 	border-width: 10px;
            border-style: solid;

            border-color: gold;
            font-family: arial;}

        .todo_important_and_overdue {background: red; height: 100px; width: 31%; float: left; padding: 10px; 	border-width: 10px;
            border-style: solid;
            ont-weight: bold;
            border-color: gold;
            font-family: arial;}

        /*DropdownMenueCSS*/
        .dropbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #ddd;}

        .dropdown:hover .dropdown-content {display: block;}

        .dropdown:hover .dropbtn {background-color: #3e8e41;}

        .normal {
            color: black;
        }

        /*
        .login_registration {background: white; margin: auto; text-align: center;}

        @media only screen and (min-width: 700px) {
            .login_registration {width: 700px;}
        }

        @media only screen and (max-width: 699px) {
            .login_registration {width: 100%;}
        }

        .login_registration_label {background: orange; border-radius: 10px;}
        .login_registration_form {background: white; border-style: solid; border-width: 3px; border-color: gray; padding: 10px; border-radius: 10px;}
        */
    </style>
</head>

<body>
<div id="container">
    <div id="header">
        <div class = "inhalt inhalt_header" action="todoListNew.do" method="get">
            <h1 class = "inhalt_header_font">Todos of ${aktuellerUser}</h1>
        </div>
        <br>
    </div>
    <div id="body">

        <div class = "inhalt">
            <a href="CreateTodo.html">
                <section>
                    <h1>Create New Todo</h1>
                </section>
            </a>
            <section>
                <h2>Category-Chooser</h2>
                <p>Display only Todos of a certain category</p>

                <div class="dropdown" action="todoListNew" method="get" >
                    <form action = "todoListNew.do" method="post">
                        <select name="category">
                            <option value="all}">--All--</option>
                            <c:forEach items="${categoryList}" var="category" varStatus="loop"  >
                                <option value="${category}"> --${category}--</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Select category">
                    </form>
                </div>
            </section>

            <section>
                <h1>Your Todos</h1>

                <div class = "test" action="todoListNew.do" method="get">

                   <%--Todo: nur die gewählte Kategorie anzeigen, Standard: Alles--%>

                    <c:forEach items="${todoList}" var="element">

                        <%--Wahlweise verschiedene Elementklassen, je nach Important und Overdue ausprägung.--%>
                        <div class = "
                          <c:choose>
                            <c:when test="${element.isImportantAndOverdue()}">
                                todo_important_and_overdue
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${element.isOverdue()}">
                                        todo_overdue
                                    </c:when>
                                    <c:otherwise>
                                       <c:choose>
                                            <c:when test="${element.isImportant()}">
                                                todo_important
                                            </c:when>
                                            <c:otherwise>
                                            todo
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                         ">
                                <%--Texte in einer Tabelle--%>
                            <style type="text/css">
                                .tg  {border-collapse:collapse;border-spacing:0;}
                                .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:hidden;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
                                .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:hidden;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
                                .tg .tg-0lax{text-align:left;vertical-align:top;}
                                .tg .tg-fymr{font-weight:bold;border-color:inherit;text-align:left;vertical-align:top}
                                .tg .tg-re1e{bborder-color:inherit;text-align:left;vertical-align:top;writing-mode: tb-rl;}
                                @media screen and (max-width: 767px) {.tg {width: auto !important;}.tg col {width: auto !important;}.tg-wrap {overflow-x: auto;-webkit-overflow-scrolling: touch;}}</style>
                            <div class="tg-wrap"><table class="tg" style="undefined;table-layout: fixed; width: 239px">
                                <colgroup>
                                    <col style="width: 71.2px">
                                    <col style="width: 71.2px">
                                    <col style="width: 73.2px">
                                    <col style="width: 23px">
                                </colgroup>
                                <tr>
                                    <%--Elemente jeweils durchgetrichen falls Completed.--%>
                                    <c:choose>
                                        <c:when test="${element.isCompleted()}">
                                            <th class="tg-fymr" colspan="3"><s>${element.getTitle()}</s></th>
                                        </c:when>
                                        <c:otherwise>
                                            <th class="tg-fymr" colspan="3">${element.getTitle()}</th>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${element.isCompleted()}">
                                            <th class="tg-re1e" rowspan="3"><s>${element.getCategory()}</s></th>
                                        </c:when>
                                        <c:otherwise>
                                            <th class="tg-re1e" rowspan="3">${element.getCategory()}</th>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>

                                    <c:choose>
                                        <c:when test="${element.isCompleted()}">
                                            <td class="tg-0lax" colspan="3"><s>Fällig am ${element.getFormattedDate()}</s> </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="tg-0lax" colspan="3">Fällig am ${element.getFormattedDate()} </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>
                                    <c:choose>
                                        <c:when test="${element.isCompleted()}">
                                            <td class="tg-0lax"><form action="MarkUncompletedNew.do" method="post"><button type="submit" name="complete" value = ${element.getId()}>Uncomplete</button> </form></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="tg-0lax"><form action="MarkCompletedNew.do" method="post"><button type="submit" name="complete" value = ${element.getId()}>Complete</button> </form></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="tg-0lax"><form action="update.do" method="post"><button type="submit" name="update" value = ${element.getId()}>Update</button> </form> </td>
                                    <td class="tg-0lax"><form action="DeleteNew.do" method="post"><button type="submit" name="delete" value = ${element.getId()}>Delete</button> </form></td>
                                </tr>
                            </table></div>
                        </div>




                    </c:forEach>

                </div>
            </section>
            <p>TETSTTTT</p>
        </div>
    </div>
    <div id="footer">
        <div class = "inhalt inhalt_footer">
            <p class = "inhalt_footer_font">&copy Copyright 2018. All rights reserves. Powered by the BFH - CAS Software Development.</p>
        </div>
    </div>
</div>
</body>
</html>

