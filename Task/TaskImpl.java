package com.example.todolist.Task;

import javafx.concurrent.Task;

import java.util.Date;

public class TaskImpl implements Taskk{
    private String name;
    private String description;
    private Date date;
    private Complet state;
    private int id_T;

    public TaskImpl(int id_of_task,String name, String description, Date date, Complet state){
        this.id_T = id_of_task ;
        this.name = name;
        this.description = description;
        this.date = date;
        this.state = Complet.not_complated;
    }

    public TaskImpl(String name, String description, Date date) {

        this.name = name;
        this.description = description;
        this.date = date ;
        this.state = Complet.not_complated ;
    }

    @Override
    public String toString() {
        return "TaskImpl{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", state=" + state +
                ", id_T=" + id_T +
                '}';
    }

    public void editing1(TaskImpl task) {
        this.name = task.name;
        this.date = task.date;
        this.description = task.description;
    }

    public void edit(Taskk task) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.date = task.getDate();
        this.state = task.getState();
    }


    /**
     * this is impl of method mark As Completed
     */
    public void markAsCompleted(){
        this.state = Complet.complated ;
    }

    public void markAsAbandoned(){
        this.state = Complet.abandoned ;
    }

    public void markAsNotComplated(){
        this.state = Complet.not_complated ;
    }

    public int getId_T() {
        return this.id_T;
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Complet getState() {
        return this.state;
    }


}
