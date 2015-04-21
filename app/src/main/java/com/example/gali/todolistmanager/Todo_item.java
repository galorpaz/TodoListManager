package com.example.gali.todolistmanager;

public class Todo_item {
    private String task;
    private String date;

    public Todo_item(String new_task, String new_date){
        this.task = new_task;
        this.date = new_date;
    }

    public String get_task(){
        return this.task;
    }

    public String get_date(){
        return this.date;
    }
}
