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
import java.util.LinkedList;
import java.util.Locale;

@WebServlet("/TodoList.do")
public class TodoListServlet extends HttpServlet {
    LinkedList<TodoUser> userList;
    TodoUser currentUser;

    public void init () {
        // ServerContext initialisieren
        ServletContext sc = this.getServletContext();
        // UserListe aus ServerContext ziehen.
        userList = ( LinkedList<TodoUser>) sc.getAttribute("users");

        for (TodoUser user : userList) {
            LinkedList<Todo> todos = user.getTodoList();
            for (Todo todo : todos) {
                System.out.println(todo.getId());
            }
        }
        // und wiederum speichern im ServletContext.
        sc.setAttribute("users", userList);

        // Todo: Choose the correct user, for now, just take the first.
        currentUser = userList.get(0);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            out.println("<html><body><h1>Todo List</h1>");
            out.println("<P ALIGN='center'> <TABLE BORDER=1>");
            // table header
            out.println("  <tr>\n" +
                    "       <th>Todo ID</th>\n" +
                    "       <th>Todo</th>\n" +
                    "       <th>Category</th>\n" +
                    "       <th>Due Date</th>\n" +
                    "       <th>Important</th>\n" +
                    "       <th>Completed</th>\n" +
                    "      </tr>");

            // the data
            for (int i = 0; i < currentUser.getTodoList().size(); i++) {
                // red if overDue
                   out.println("<TR font = \"red\">");
                   out.println("<TD>" + currentUser.getTodoList().get(i).getId() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).getTitle() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).getCategory() + "</TD>");
                   DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
                           FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
                   String formatedDate = currentUser.getTodoList().get(i).getDueDate().format(germanFormatter);
                   out.println("<TD>" + formatedDate + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).isImportant() + "</TD>");
                   out.println("<TD>" + currentUser.getTodoList().get(i).isCompleted() + "</TD>");
                   out.println("</TR>");

            }
            out.println("</TABLE></P>");
            out.println("</body><html>");
        }

    } // end of doGET
}



