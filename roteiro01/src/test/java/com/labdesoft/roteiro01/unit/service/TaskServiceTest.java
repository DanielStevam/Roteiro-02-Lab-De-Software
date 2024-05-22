package com.labdesoft.roteiro01.unit.service;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.service.TaskService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    public TaskServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTasks() {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

        List<Task> tasks = taskService.findAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }

    @Test
    public void createTask() {
        Task task = new Task("New Task", TaskType.BUG, LocalDate.now(), 5, Priority.MEDIUM);
        task.setStatus(TaskStatus.TODO);
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        assertEquals("New Task", createdTask.getDescription());
    }
}
