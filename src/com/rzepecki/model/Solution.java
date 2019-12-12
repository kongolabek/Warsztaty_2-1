package com.rzepecki.model;

import java.sql.Date;
import java.time.LocalDateTime;


public class Solution {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private int exercise_id;
    //private Exercise exercise_id;
    private int users_id;
    //private User users_id;

    public Solution() {}

    public Solution(int exercise_id, int users_id) {
        this.created = LocalDateTime.now();
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public Solution(String description){
        this.updated = LocalDateTime.now();
        this.description = description;
    }

    /*
    public Solution(LocalDateTime created, LocalDateTime updated, String description, int exercise_id, int users_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        java.sql.Date sqlDate = java.sql.Date.valueOf(created.toLocalDate());
        return sqlDate;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Date getUpdated() {
        if (updated==null){
            return null;
        }else{
            java.sql.Date sqlDate = java.sql.Date.valueOf(updated.toLocalDate());
            return sqlDate;
        }
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + users_id +
                '}';
    }
}
