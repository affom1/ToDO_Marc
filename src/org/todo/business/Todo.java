package org.todo.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

public class Todo {

    private int id;
    private String title;
    private String category;
    private LocalDate dueDate;
    private boolean important;
    private boolean completed;

    public Todo(int id, String title, String category, String datum, boolean important, boolean completed) {
        this.id = id;
        this.title = title;
        this.category = category;
        // Datumskreation
        DateTimeFormatter marcFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.dueDate = LocalDate.parse(datum, marcFormatter);
        this.important = important;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void updateEverythingButId (String title, String category, String datum, boolean important, boolean completed) {
        this.title=title;
        this.category=category;
        DateTimeFormatter marcFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        this.dueDate = LocalDate.parse(datum, marcFormatter);
        this.important=important;
        this.completed=completed;
    }
    public LocalDate getDueDate() {

        return dueDate;

    }
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");

        return dueDate.format(formatter);
    }
    public boolean isImportant() {
        return important;
    }

    public boolean isImportantAndOverdue() {
        if ((important) && (isOverdue() )) {
            return true;
        }

        return false;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isOverdue () {
        if (this.dueDate.isAfter(LocalDate.now())) return false; // das Due Date ist nach heute --> somit NICHT overdue

        return true;
    }

}
