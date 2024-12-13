package com.example.todolist.Task;

import com.example.todolist.DataBaseControler;

import java.util.ArrayList;
import java.util.Comparator;


public class TaskListImpl extends TaskList{
    private ArrayList<Taskk> tasks;

    public TaskListImpl() {
        this.tasks = new ArrayList<Taskk>();
        update();
    }

    public  void addTask(Taskk task){
        DataBaseControler.create(task) ;
        update();
    }

    public void deleteTask(Taskk task){
        DataBaseControler.deleteById(task.getId_T());
        update();
    }

    public void editTask(int idOfTask ,Taskk task){
        for (Taskk task1 : this.tasks) {
            if (task1.getId_T() == idOfTask) {
                task1.edit(task);
                DataBaseControler.update(task1);
                update();
                break;
            }
        }
    }

    public ArrayList<Taskk> displayTasks(){
        return this.tasks ;
    }

    public void sortByDate(){
        if(this.tasks!=null)
        this.tasks.sort(Comparator.comparing(item -> item.getDate()));
        else System.out.println("error1");
    }


    public ArrayList<Taskk> search(String word){
        ArrayList<Taskk> taskgoal = new ArrayList<>();
        for (Taskk task : this.tasks) {
            if (task.getName().contains(word) || task.getDescription().contains(word)) {
                taskgoal.add(task);
            }
        }
        return taskgoal;
    }
    //*********************************************************


    public ArrayList<Taskk> search2(){
        ArrayList<Taskk> taskgoal = new ArrayList<>();
        for (Taskk task : this.tasks) {
            if (task.getState()==Complet.complated()) {
                taskgoal.add(task);
            }
        }
        System.out.println("helllllllllllllo2");
        if(taskgoal.isEmpty())System.out.println("helllllllllllllo3");
        return taskgoal;
    }


    //********************************************************
    @Override
    public String toString() {
        return "TaskListImpl{" +
                "tasks=" + tasks +
                '}';
    }

    public void update(){


        if(this.tasks!=null) {
            this.tasks = DataBaseControler.findAll();
            sortByDate();
        }
        else System.out.println("error");

    }
}
