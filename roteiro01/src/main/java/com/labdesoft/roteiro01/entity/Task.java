package com.labdesoft.roteiro01.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "Todos os detalhes sobre uma tarefa. ")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Boolean completed;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", description=" + description + ", completed=" + completed + "]";
    }

}