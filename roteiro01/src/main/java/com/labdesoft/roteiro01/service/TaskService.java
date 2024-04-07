package com.labdesoft.roteiro01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task create(Task novaTask) {
        return taskRepository.save(novaTask);
    }

    public Task update(Task task) {

        Task taskToUpdate = taskRepository.findById(task.getId()).orElse(null);

        if (taskToUpdate != null) {
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setCompleted(task.getCompleted());
        }
        return taskRepository.save(taskToUpdate);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task complete(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            if (task.getCompleted()) {
                task.setCompleted(false);
            } else {
                task.setCompleted(true);
            }
        }
        return taskRepository.save(task);
    }

}