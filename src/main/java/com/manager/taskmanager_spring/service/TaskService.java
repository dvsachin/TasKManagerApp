package com.manager.taskmanager_spring.service;

import com.manager.taskmanager_spring.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTitle(title);
        taskEntity.setDescription(description);
        taskEntity.setDeadline(deadlineFormatter.parse(deadline)); // TODO : validate Data format YYYY-MM-DD
        taskEntity.setCompleted(false);
        tasks.add(taskEntity);
        taskId++;

        return taskEntity;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(int id) {
        for (TaskEntity task : tasks) {
            if (task.getId() == id) return task;
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task == null) {
            return null;
        }
        if(description != null) task.setDescription(description);
        if(deadline != null) task.setDeadline(deadlineFormatter.parse(deadline));
        if (completed != null) task.setCompleted(completed);
        return task;
    }
}
