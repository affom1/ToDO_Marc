package org.todo.servlets;

import org.todo.business.TodoUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;

@WebListener()
public class UserListListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public UserListListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      // get the ServletContext from the event, that is created from the listener
        ServletContext sc = sce.getServletContext();
        ArrayList<TodoUser> userList = (ArrayList<TodoUser>) sc.getAttribute("users"); // This should retrieve a null object, as it should be empty
        if (userList == null) { // ist es sowieso
            System.out.println("Noch keine User erstellt. User werden erstellt");
            userList = new ArrayList<>();

            // create a bunch of Samples, uncomment if necessary
            userList.add(new TodoUser("Freddy Dummy", "password1"));
            userList.get(0).addTodo("Grossmutter besuchen", "Freizeit", "2018-11-24", false, false);
            userList.get(0).addTodo("Grossmutter befragen", "Freizeit", "2018-12-24", true, false);
            userList.get(0).addTodo("Von Wolf flüchten", "Panik", "2018-11-13", false, true);
            userList.get(0).addTodo("Im Wald stolpern", "Dumm", "2018-11-26", false, false);
            userList.get(0).addTodo("Wolf töten", "Panik", "2018-11-12", true, true);
            userList.get(0).addTodo("Salami essen", "Panik", "2019-11-12", false, false);
            userList.get(0).addTodo("Joggen", "Freizeit", "2019-08-12", true, false);
            // save them in the ServletContext
            sc.setAttribute("users", userList);
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the MarkCompletedServlet Context
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
