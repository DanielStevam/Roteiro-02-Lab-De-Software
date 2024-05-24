package com.labdesoft.roteiro01.unit.repository;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List; // Certifique-se de importar List
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateAndFindTask() {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        taskRepository.save(task);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(1, tasks.size());
        Task retrievedTask = tasks.get(0);
        assertEquals("Test Task", retrievedTask.getDescription());
    }

    @Test
    public void testFindById() {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        task = taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertTrue(foundTask.isPresent());
        assertEquals(task.getId(), foundTask.get().getId());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        task = taskRepository.save(task);

        taskRepository.delete(task);
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertFalse(foundTask.isPresent());
    }
}
