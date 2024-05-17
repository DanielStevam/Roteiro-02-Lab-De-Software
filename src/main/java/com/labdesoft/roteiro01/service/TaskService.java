package com.labdesoft.roteiro01.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labdesoft.roteiro01.entity.*;
import com.labdesoft.roteiro01.repository.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTaskPriority(Long taskId, Priority priority) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setPriority(priority);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Tarefa não encontrada com o ID: " + taskId);
        }
    }
}
