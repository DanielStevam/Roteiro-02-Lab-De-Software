package com.labdesoft.roteiro01.unit.repository;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.mock.TaskMock;
import com.labdesoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateAndFindTask() {
        Task task = TaskMock.createMockTask();
        taskRepository.save(task);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(1, tasks.size());
        assertEquals(task.getDescription(), tasks.get(0).getDescription());
    }
}
