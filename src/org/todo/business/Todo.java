package org.todo.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

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
        //Todo: eventuell ein wenig Error-Handling...
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
                FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        this.dueDate = LocalDate.parse(datum, germanFormatter);

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isImportant() {
        return important;
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
        if (this.dueDate.isAfter(LocalDate.now())) return true;

        return false;
    }
}
