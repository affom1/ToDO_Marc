package org.todo.servlets;

import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/UpdateTodoWithInputs.do")
public class UpdateTodoWithInputsServlet extends HttpServlet {
    LinkedList<TodoUser> userList;
    TodoUser currentUser;
    ServletContext sc;

    public void init () {
        // ServerContext initialisieren
        sc = this.getServletContext();
        // UserListe aus ServerContext ziehen.
        userList = ( LinkedList<TodoUser>) sc.getAttribute("users");

        // und wiederum speichern im ServletContext.
        sc.setAttribute("users", userList);

        // Todo: Choose the correct user, for now, just take the first.
        currentUser = userList.get(0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        boolean important = false;
        boolean completed = false;
        String stringImportant = null;
        if (request.getParameter("completed")!=null) {
            completed=true;
        }
        // important ist einfach Null wenn nicht angekreut. Mühsam...
        try {
            stringImportant = request.getParameter("important");
            if (stringImportant.equals("on")) important = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        String date = request.getParameter("dueDate");

        // update of Todos and save them.
        currentUser.getTodoList().get(id).updateEverythingButId(title, category,date,important,completed);
        sc.setAttribute("users", userList);

        // send him back to the List
        response.sendRedirect(request.getContextPath() + "/TodoList.do");

    }
}
