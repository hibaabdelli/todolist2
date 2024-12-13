package com.example.todolist.Task;

import java.util.ArrayList;

public abstract  class TaskList {
    public abstract void addTask(Taskk task);
    public abstract void deleteTask(Taskk task);
    public abstract void editTask(int idOfTask,Taskk task);
    public abstract ArrayList<Taskk> displayTasks();
    public abstract void sortByDate() ;
    public abstract ArrayList<Taskk> search(String word) ;
}
