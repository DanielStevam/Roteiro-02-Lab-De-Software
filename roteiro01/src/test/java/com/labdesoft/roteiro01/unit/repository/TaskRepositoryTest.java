package com.labdesoft.roteiro01.unit.repository;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.mock.TaskMock;
import com.labdesoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        // Limpar o repositório antes de cada teste
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateAndFindTask() {
        // Criar uma tarefa mock
        Task task = TaskMock.createMockTask();
        // Salvar a tarefa no repositório
        taskRepository.save(task);

        // Recuperar todas as tarefas do repositório
        List<Task> tasks = taskRepository.findAll();
        // Verificar se a tarefa foi salva corretamente
        assertEquals(1, tasks.size());
        assertEquals(task.getDescription(), tasks.get(0).getDescription());
    }
}
