package com.manager.taskmanager_spring.controllers;

import com.manager.taskmanager_spring.dtos.CreateTaskDTO;
import com.manager.taskmanager_spring.dtos.ErrorResponseDTO;
import com.manager.taskmanager_spring.dtos.UpdateTaskDTO;
import com.manager.taskmanager_spring.entities.TaskEntity;
import com.manager.taskmanager_spring.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable String id) {
        var task = taskService.getTaskById(Integer.parseInt(id));
        if (task == null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);

    }
    @PatchMapping("tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null) ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(ParseException e) {
        if(e instanceof ParseException)
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }

}
