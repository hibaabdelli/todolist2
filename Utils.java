package com.example.todolist;

public class Utils {
    public static java.sql.Date getSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
}
