package org.todo.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/counter.do")
public class CounterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) counter = 0;
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body><h1>Counter Servlet</h1>"
                    + "<form action='" + response.encodeURL("counter.do") + "' method='post'>"
                    + "Counter: " + counter
                    + "<input type='submit' value='Increment'/>"
                    + "</form><body></html>");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) counter = 0;
        session.setAttribute("counter", counter + 1);
        doGet(request, response);
    }



}