package org.todo.servlets;

import org.todo.business.Todo;
import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/markUncompleted.do")
public class MarkUncompletedServlet extends HttpServlet {
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


        int id = Integer.parseInt(request.getParameter("Uncomplete"));
        System.out.println("Wir ändern Element: "+ id);
        // Todos mit entprechender ID als Completed markieren.
        for (Todo todo : currentUser.getTodoList()) {
            if (todo.getId()== id) {
                todo.setCompleted(false);
            }
        }
        // save them in the ServletContext
        sc.setAttribute("users", userList);
        // send him back to the List
        response.sendRedirect(request.getContextPath() + "/TodoList.do");

    }
}
