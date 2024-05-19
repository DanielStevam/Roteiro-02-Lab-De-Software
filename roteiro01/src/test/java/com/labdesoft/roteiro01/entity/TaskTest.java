package com.labdesoft.roteiro01.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class TaskTest {

    @Test
    void testGetterSetter() {
        // Cria uma instância de Task
        Task task = new Task();

        // Define os valores dos atributos
        task.setId(1L);
        task.setDescription("Exemplo de tarefa");
        task.setType(TaskType.TYPE_A);
        task.setDueDate(LocalDate.now());
        task.setDueDays(3);
        task.setPriority(Priority.HIGH);
        task.setStatus(TaskStatus.PENDING);

        // Verifica se os valores atribuídos estão corretos
        assertEquals(1L, task.getId());
        assertEquals("Exemplo de tarefa", task.getDescription());
        assertEquals(TaskType.TYPE_A, task.getType());
        assertEquals(LocalDate.now(), task.getDueDate());
        assertEquals(3, task.getDueDays());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    void testConstructor() {
        // Define os valores dos atributos
        String description = "Exemplo de tarefa";
        TaskType type = TaskType.TYPE_A;
        LocalDate dueDate = LocalDate.now();
        Integer dueDays = 3;
        Priority priority = Priority.HIGH;

        // Cria uma instância de Task usando o construtor
        Task task = new Task(description, type, dueDate, dueDays, priority);

        // Verifica se os valores atribuídos estão corretos
        assertEquals(description, task.getDescription());
        assertEquals(type, task.getType());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(dueDays, task.getDueDays());
        assertEquals(priority, task.getPriority());
    }
}
