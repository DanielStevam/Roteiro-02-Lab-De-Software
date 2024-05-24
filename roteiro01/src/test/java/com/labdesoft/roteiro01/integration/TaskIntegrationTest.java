package com.labdesoft.roteiro01.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;
import com.labdesoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateAndGetTask() throws Exception {
        Task task = new Task("Integration Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Integration Test Task"));

        List<Task> tasks = taskRepository.findAll();
        assertEquals(1, tasks.size());
        assertEquals("Integration Test Task", tasks.get(0).getDescription());
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task1 = new Task("Integration Task 1", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task1.setStatus(TaskStatus.TODO);
        Task task2 = new Task("Integration Task 2", TaskType.BUG, LocalDate.now(), 5, Priority.MEDIUM);
        task2.setStatus(TaskStatus.IN_PROGRESS);

        taskRepository.saveAll(List.of(task1, task2));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Integration Task 1"))
                .andExpect(jsonPath("$[1].description").value("Integration Task 2"));
    }
}
