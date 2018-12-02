package org.todo.servlets;

import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/todoListNew.do")
public class TodoListServletNew extends HttpServlet {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
//        Integer counter = (Integer) session.getAttribute("counter");

        session.setAttribute("aktuellerUser", currentUser.getName());
//        if (counter == null) session.setAttribute("counter", 0);

        // Todos sortieren: using Lambadas --> heavy shit
        Collections.sort(currentUser.getTodoList(), (a, b) -> a.getDueDate().compareTo(b.getDueDate()));
        session.setAttribute("todoList", currentUser.getTodoList());
        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        HttpSession session = request.getSession();

        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }




}
