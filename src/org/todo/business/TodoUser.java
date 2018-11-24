package org.todo.business;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoUser {

    private String name;
    private String password;
    private LinkedList<Todo> todoList;
    private static final AtomicInteger count = new AtomicInteger(0);

    public TodoUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.todoList = new LinkedList<>();
    }

    public void addTodo(String title, String category, String datum, boolean important, boolean completed){
        todoList.add(new Todo (count.incrementAndGet(), title, category, datum, important, completed));
    }

    public LinkedList<Todo> getTodoList() {
        return this.todoList;
    }

    public void updateTodo(){

    }

    public void removeTodo(int index){

    }

    public LinkedList<String> getCategories() {
        LinkedList<String> categoryList = new LinkedList<>();
        for (Todo todo : todoList) {
            categoryList.add(todo.getCategory());
        }
        return categoryList;
    }


}
