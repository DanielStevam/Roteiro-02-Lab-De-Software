package com.labdesoft.roteiro01.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.controller.TaskController;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;
import com.labdesoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllTasks() throws Exception {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        Mockito.when(taskService.findAllTasks()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test Task"));
    }

    @Test
    public void createTask() throws Exception {
        Task task = new Task("New Task", TaskType.BUG, LocalDate.now(), 5, Priority.MEDIUM);
        task.setStatus(TaskStatus.TODO);
        Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New Task"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task("Updated Task", TaskType.BUG, LocalDate.now(), 5, Priority.MEDIUM);
        task.setId(1L);
        task.setStatus(TaskStatus.IN_PROGRESS);
        Mockito.when(taskService.updateTask(Mockito.any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Task"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNoContent());
    }
}
