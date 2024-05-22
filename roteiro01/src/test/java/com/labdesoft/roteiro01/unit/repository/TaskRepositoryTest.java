package com.labdesoft.roteiro01.unit.repository;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.repository.TaskRepository;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateAndFindTask() {
        Task task = new Task("Test Task", TaskType.FEATURE, LocalDate.now(), 3, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        taskRepository.save(task);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }
}
