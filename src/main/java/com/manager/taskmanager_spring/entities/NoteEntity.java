package com.manager.taskmanager_spring.entities;

import lombok.Data;

@Data
public class NoteEntity {
    protected int id;
    protected String title;
    protected String body;
}
