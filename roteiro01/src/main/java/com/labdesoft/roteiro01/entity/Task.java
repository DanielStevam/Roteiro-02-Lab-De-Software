package com.labdesoft.roteiro01.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.persistence.Transient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Data
@Schema(description = "Todos os detalhes sobre uma tarefa.")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private TaskType type;

    @FutureOrPresent(message = "A data prevista de execução deve ser igual ou superior à data atual.")
    private LocalDate dueDate;

    private Integer dueDays;

    private Priority priority;

    @Transient
    private TaskStatus status;

    // Construtores
    public Task() {
    }

    public Task(String description, TaskType type,
            @FutureOrPresent(message = "A data prevista de execução deve ser igual ou superior à data atual.") LocalDate dueDate,
            Integer dueDays, Priority priority) {
        this.description = description;
        this.type = type;
        this.dueDate = dueDate;
        this.dueDays = dueDays;
        this.priority = priority;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getDueDays() {
        return dueDays;
    }

    public void setDueDays(Integer dueDays) {
        this.dueDays = dueDays;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
