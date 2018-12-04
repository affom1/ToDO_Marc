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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@WebServlet("/todoListNew.do")
public class TodoListServletNew extends HttpServlet {
    ArrayList<TodoUser> userList;
    TodoUser currentUser;
    ArrayList<Todo> kategorienTodoListe = new ArrayList<>();

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
        kategorienTodoListe = currentUser.getTodoList();
        session.setAttribute("todoList", kategorienTodoListe);


        // Kategorienliste erstellen.
        ArrayList<String> categoryList = new ArrayList<>();
        if (categoryList.isEmpty()) categoryList.add(currentUser.getTodoList().get(0).getCategory());   // hinzufügen wenn leer
        for (int i = 1;i<currentUser.getTodoList().size();i++) { // durch den ersten brauchen wir nciht zu iterieren
           boolean doesExist = true;
            for (String category : categoryList) {
                if (currentUser.getTodoList().get(i).getCategory().equals(category)) {  //wenn die Kategorie gleich ist:
                    System.out.println("Die Kategorie " + category +" existiert bereits und wird nciht hinzugefügt.");
                    doesExist = true;
                    break;
                } else {
                    System.out.println("neu hinzufügen: "+currentUser.getTodoList().get(i).getCategory());
                    doesExist=false;
                }
            }
            if (!doesExist) categoryList.add(new String(currentUser.getTodoList().get(i).getCategory()));

        }

        System.out.println("und nun die Kategorieliste durchlaufen");
        System.out.println("grosse der liste"+categoryList.size());
        System.out.println("grosse der Todo liste"+currentUser.getTodoList().size());
        for (int i =0;i<categoryList.size();i++) {
            System.out.println(categoryList.get(i));
        }

        session.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("/todoList.jsp").forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        HttpSession session = request.getSession();

        String choosenCategory = request.getParameter("category");

        if (choosenCategory.equals("all}")) {
            System.out.println("if wird erreicht");
            kategorienTodoListe = currentUser.getTodoList();
        } else {
            System.out.println("else wird erreicht.");
            System.out.println(currentUser.getTodoList().size());

            kategorienTodoListe = null;
            kategorienTodoListe = new ArrayList<>();
            System.out.println(currentUser.getTodoList().size());
            for (Todo todo1 : currentUser.getTodoList()) {
                System.out.println("Das Kategorie todo sieht so aus: "+todo1.getCategory());
                System.out.println("Das choosen Todo sieht so aus: "+choosenCategory);
                if (todo1.getCategory().equals(choosenCategory)) {
                    kategorienTodoListe.add(todo1);
                    System.out.println(todo1);
                }
            }
        }
        System.out.println("es wurde vermutlich nichts erreicht.");


        System.out.println(choosenCategory);
        // Kategorie an das JSP schicken, vorher sortieren
        Collections.sort(kategorienTodoListe, (a, b) -> a.getDueDate().compareTo(b.getDueDate()));
        session.setAttribute("todoList",kategorienTodoListe );


        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }




}
