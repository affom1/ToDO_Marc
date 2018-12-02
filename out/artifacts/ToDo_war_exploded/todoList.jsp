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
            <h1 class = "inhalt_header_font">Todos von ${aktuellerUser}</h1>
        </div>
        <br>
    </div>
    <div id="body">

        <div class = "inhalt">
            <a href="CreateTodo.html">
                <section>
                    <h1>Neue ToDo erfassen</h1>
                </section>
            </a>
            <!--
            <a href=\"TodoListFiltern.do\">
                <section>
                 <h1>ToDo's filtern</h1>
                </section>
            </a>
            -->
            <section>
                <h1>Deine Todos</h1>

                <div class = "test" action="todoListNew.do" method="get">
                    <c:forEach items="${todoList}" var="element">
                        <div class = "${element.isOverdue() ? 'todo' : 'todo_overdue'}">


                                <style type="text/css">
                                    .tg  {border-collapse:collapse;border-spacing:0;}
                                    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:hidden;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
                                    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:hidden;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
                                    .tg .tg-qtf5{border-color:#000000;text-align:left}
                                    .tg .tg-v47y{font-weight:bold;border-color:#000000;text-align:left}
                                    .tg .tg-pj8m{writing-mode: tb-rl;}
                                    @media screen and (max-width: 767px) {.tg {width: auto !important;}.tg col {width: auto !important;}.tg-wrap {overflow-x: auto;-webkit-overflow-scrolling: touch;}}</style>
                                <div class="tg-wrap"><table class="tg" style="undefined;table-layout: fixed; width: 317px">
                                    <colgroup>
                                        <col style="width: 136.2px">
                                        <col style="width: 136.2px">
                                        <col style="width: 45px">
                                    </colgroup>
                                    <tr>
                                        <th class="tg-v47y" colspan="2">${element.getTitle()}</th>
                                        <th class="tg-pj8m" rowspan="3">${element.getCategory()}<br></th>
                                    </tr>
                                    <tr>
                                        <td class="tg-qtf5" colspan="2">Fällig am ${element.getFormattedDate()}</td>
                                    </tr>
                                    <tr>
                                        <td class="tg-qtf5">Button 1</td>
                                        <td class="tg-qtf5">Button2</td>
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

