package org.todo.servlets;


import org.todo.business.Todo;
import org.todo.business.TodoUser;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet("/TodoList.do")
public class TodoListServlet extends HttpServlet {
    ArrayList<TodoUser> userList;
    TodoUser currentUser;

    public void init () {
        // ServerContext initialisieren
        ServletContext sc = this.getServletContext();
        // UserListe aus ServerContext ziehen.
        userList = ( ArrayList<TodoUser>) sc.getAttribute("users");

        // und wiederum speichern im ServletContext.
//        sc.setAttribute("users", userList);

        // Todo: Choose the correct user, for now, just take the first.
        currentUser = userList.get(0);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Todos sortieren: using Lambadas --> heavy shit
        Collections.sort(currentUser.getTodoList(), (a, b) -> a.getDueDate().compareTo(b.getDueDate()));


        //Todo Wahlweise nur eine bestimmte Kategorie anzeigen.


        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            out.println("<html><body><h1>Todo List</h1>");
            out.println("<ul>\n" +
                    "  <li><a href=\"CreateTodo.html\">Create new Todo</a></li>\n" +
                    "  <li><a href=\"Login.do\">LogOut</a></li>\n" +
                    "  <li><a href=\"TodoList.do\">Todo List</a></li>\n" +
                    "</ul>");
            out.println("<P ALIGN='center'> <TABLE BORDER=1>");

            // table header
            out.println("  <tr>\n" +
                    "       <th>Todo ID</th>\n" +
                    "       <th>Todo</th>\n" +
                    "       <th>Category</th>\n" +
                    "       <th>Due Date</th>\n" +
                    "       <th>Important</th>\n" +
                    "       <th>Completed</th>\n" +
                    "       <th>Un/Complete</th>\n" +
                    "       <th>Update</th>\n" +
                    "       <th>Delete</th>\n" +
                    "      </tr>");

            // the data
            String markCompleted = "Mark completed";
            String markuncomplted = "Mark uncompleted";
            for (int i = 0; i < currentUser.getTodoList().size(); i++) {
                // red if overDue
                   out.println("<TR font = \"red\">"); // funktioniert noch nicht.
                   out.println("<TD>" + currentUser.getTodoList().get(i).getId() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).getTitle() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).getCategory() + "</TD>");
                   DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
                           FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
                   String formatedDate = currentUser.getTodoList().get(i).getDueDate().format(germanFormatter);
                   out.println("<TD>" + formatedDate + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).isImportant() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).isCompleted() + "</TD>");
                   if (currentUser.getTodoList().get(i).isCompleted()) {
                       out.println("<TD><form action=\"markUncompleted.do\" method=\"get\"> <button type=\"submit\" name=\"Uncomplete\" value=\""+currentUser.getTodoList().get(i).getId()+"\">Mark Uncomplete</button></form></TD>");
                   } else { out.println("<TD><form action=\"markCompleted.do\" method=\"get\"> <button type=\"submit\" name=\"Complete\" value=\""+currentUser.getTodoList().get(i).getId()+"\">Complete Todo</button></form></TD> ");}
                   out.println("<TD><form action=\"update.do\" method=\"get\"><button type=\"submit\" name=\"update\" value=\""+currentUser.getTodoList().get(i).getId()+"\">Update</button></form></TD>");
                   out.println("<TD><form action=\"delete.do\" method=\"get\"><button type=\"submit\" name=\"delete\" value=\""+currentUser.getTodoList().get(i).getId()+"\">Delete</button></form></TD>");
                   out.println("</TR>");

            }
            out.println("</TABLE></P>");
            out.println("</body></html>");
        }

    } // end of doGET
}



