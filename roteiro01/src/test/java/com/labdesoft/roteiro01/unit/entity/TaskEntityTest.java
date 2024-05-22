package com.labdesoft.roteiro01.unit.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskStatus;
import com.labdesoft.roteiro01.entity.TaskType;

import java.time.LocalDate;

class TaskEntityTest {

    @Test
    void testGetterSetter() {
        // Crio uma instância de Task
        Task task = new Task();

        // Defino os valores dos atributos
        task.setId(1L);
        task.setDescription("Exemplo de tarefa");
        task.setType(TaskType.FEATURE);
        task.setDueDate(LocalDate.now());
        task.setDueDays(3);
        task.setPriority(Priority.HIGH);
        task.setStatus(TaskStatus.TODO);

        // Verifico se os valores atribuídos estão corretos
        assertEquals(1L, task.getId());
        assertEquals("Exemplo de tarefa", task.getDescription());
        assertEquals(TaskType.FEATURE, task.getType());
        assertEquals(LocalDate.now(), task.getDueDate());
        assertEquals(3, task.getDueDays());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    // teste
    @Test
    void testConstructor() {
        // Defino os valores dos atributos
        String description = "Exemplo de tarefa";
        TaskType type = TaskType.BUG;
        LocalDate dueDate = LocalDate.now();
        Integer dueDays = 3;
        Priority priority = Priority.HIGH;

        // Crio uma instância de Task usando o construtor
        Task task = new Task(description, type, dueDate, dueDays, priority);

        // Verifico se os valores atribuídos estão corretos
        assertEquals(description, task.getDescription());
        assertEquals(type, task.getType());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(dueDays, task.getDueDays());
        assertEquals(priority, task.getPriority());
    }
}
