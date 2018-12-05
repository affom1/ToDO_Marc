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
        // Todo: schick User zurück zu Login wenn keine Session vorhanden. evtl. mit Fehlermessage, melde dich an.
        // Anmerkung, evt. muss das in doGet gemacht werden, da diese Methode sonst trotzdem öffnet.
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Session holen und User holen.
        HttpSession session = request.getSession();
        currentUser  = (TodoUser) session.getAttribute("currentUser");

        // Todos sortieren: using Lambadas --> heavy shit
        Collections.sort(currentUser.getTodoList(), (a, b) -> a.getDueDate().compareTo(b.getDueDate()));
        kategorienTodoListe = currentUser.getTodoList();
        session.setAttribute("todoList", kategorienTodoListe);

        // Kategorienliste erstellen, die tendenziell eine Teilmenge der Todoliste ist.
        ArrayList<String> categoryList = new ArrayList<>();
        if (categoryList==null) categoryList.add(currentUser.getTodoList().get(0).getCategory());   // hinzufügen wenn leer
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

        // Debugginggeschichte
        System.out.println("und nun die Kategorieliste durchlaufen");
        System.out.println("grosse der liste"+categoryList.size());
        System.out.println("grosse der Todo liste"+currentUser.getTodoList().size());
        for (int i =0;i<categoryList.size();i++) {
            System.out.println(categoryList.get(i));
        }

        // Kategorienliste in der Session speichern, JSP greift auf diese zu. Danach weiterleiten auf TodoListe jsp
        session.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/todoList.jsp").forward(request, response);

    }
    // Wird nur benötigt um Kategorie zu wählen
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {

        // Session holen und User holen.
        HttpSession session = request.getSession();
        currentUser  = (TodoUser) session.getAttribute("currentUser");

        // Gewählte Kategorie aus dem request holen
        String choosenCategory = "all";  // Standardmässig all damit alle Kategorien angezeigt werden.
        try {
            choosenCategory = request.getParameter("category"); // Null wenn das erste Mal geöffnet.
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (choosenCategory==null) choosenCategory = "all";

        // alle anzeigen, wenn all, ansonsten gewählte Kategorie anzeiegn.
        if (choosenCategory.equals("all")) {
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

        // Wenns nicht klappt mit session arbeiten anstelle des request.
        request.setAttribute("todoList",kategorienTodoListe );
        request.getRequestDispatcher("/todoList.jsp").forward(request, response);
    }




}
