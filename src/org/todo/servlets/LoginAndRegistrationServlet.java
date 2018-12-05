package org.todo.servlets;

import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/LoginAndRegister.do")
public class LoginAndRegistrationServlet extends HttpServlet {
    ArrayList<TodoUser> userList;
    TodoUser currentUser;

    public void init() {
        ServletContext sc = this.getServletContext();
        // UserListe aus ServerContext ziehen.
        userList = ( ArrayList<TodoUser>) sc.getAttribute("users");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name ="hallo";
        String password="arsch";
//        if (request.getParameter("Anmelden").equals("login")) {
            System.out.println("Wir sind drin");
            name = request.getParameter("name_login");
            password = request.getParameter("passwd_login");
//        }





        System.out.println(name);
        System.out.println(password);

        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
