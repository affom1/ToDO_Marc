package org.todo.servlets;

import org.todo.business.Todo;
import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet("/update.do")
public class UpdateTodoServlet extends HttpServlet {
    ArrayList<TodoUser> userList;
    TodoUser currentUser;
    ServletContext sc;

    public void init () {
        // ServerContext initialisieren

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Session holen und User holen.
        HttpSession session = request.getSession();
        currentUser  = (TodoUser) session.getAttribute("currentUser");

        int id = Integer.parseInt(request.getParameter("update"));
        response.setContentType("text/html");
        String important="";
        String completed ="";
        Todo todo =null;

        for (int i = 0;i<currentUser.getTodoList().size();i++) {
            if (currentUser.getTodoList().get(i).getId() == id) {
                todo = currentUser.getTodoList().get(i);
                if (todo.isImportant())important = "checked";
                if (todo.isCompleted())completed = "checked";
                System.out.println(important);
                System.out.println(completed);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html>" +
                    "<head>\n" +
                    "    <title>Update Todo</title>\n" +
                    "</head>" +
                    " <body>\n" +
                    "   <h1>Update existing Todo</h1>\n" +
                    "<ul>\n" +
                    "   <li><a href=\"CreateTodo.html\">Create new Todo</a></li>\n" +
                    "    <li><a href=\"Login.do\">LogOut</a></li>\n" +
                    "    <li><a href=\"todoListNew.do\">Todo List</a></li>\n" +
                    "</ul>" +
                    "<form action=\"UpdateTodoWithInputs.do\" method=\"get\" value=\""+id+"\">\n" +
                    "    Title <input type=\"text\" name=\"title\" value=\""+todo.getTitle()+"\"/>\n" +
                    "    <br/><br/>\n" +
                    "    Category <input type=\"text\" name=\"category\" value=\""+todo.getCategory()+"\"/>\n" +
                    "    <br/><br/>\n" +
                    "    Due date: <input type=\"date\" name=\"dueDate\" value=\""+todo.getDueDate()+"\"/>\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\""+id+"\"/>\n"+
                    "    <br/><br/>\n" +
                    "    Important? <input type=\"checkbox\" name=\"important\" value=\"important\" "+important+"/>\n" +
                    "    <br/><br/>\n" +
                    "    Completed? <input type=\"checkbox\" name=\"completed\" value=\"completed\" "+completed+"/>\n" +
                    "    <br/><br/>\n" +
                    "    <input type=\"submit\" value=\"Update your Todo\"/>\n" +
                    "</form>" +
                    "</body>\n" +
                    "</html>"
            );
        }
    }
}
