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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateAndGetTask() throws Exception {
        Task task = new Task("New Task", TaskType.BUG, LocalDate.now(), 5, Priority.MEDIUM);
        task.setStatus(TaskStatus.TODO);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New Task"));

        List<Task> tasks = taskRepository.findAll();
        assertEquals(1, tasks.size());
        assertEquals("New Task", tasks.get(0).getDescription());
    }
}
