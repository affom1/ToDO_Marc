package org.todo.servlets;

import org.todo.business.Todo;
import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/newTodo.do")
public class CreateTodoServlet extends HttpServlet {
    ArrayList<TodoUser> userList;
    TodoUser currentUser;
    ServletContext sc;

    public void init() {
        // ServerContext initialisieren
        sc = this.getServletContext();
        // UserListe aus ServerContext ziehen.
        userList = (ArrayList<TodoUser>) sc.getAttribute("users");

        // Todo: Choose the correct user, for now, just take the first.
        currentUser = userList.get(0);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        boolean important = false;
        String stringImportant = null;
        // important ist einfach Null wenn nicht angekreut. Mühsam...
        try {
            stringImportant = request.getParameter("important");
            if (stringImportant.equals("on")) important = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        String date = request.getParameter("dueDate");

        // creation of Todos and save them.
        currentUser.addTodo(title, category, date, important, false);
//        sc.setAttribute("users", userList);

        // send him back to the List
        response.sendRedirect(request.getContextPath() + "/TodoList.do");
    }
}
