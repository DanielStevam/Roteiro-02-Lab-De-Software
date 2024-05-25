package com.labdesoft.roteiro01.mock;

import com.labdesoft.roteiro01.entity.Task;
import com.labdesoft.roteiro01.entity.TaskType;
import com.labdesoft.roteiro01.entity.Priority;
import com.labdesoft.roteiro01.entity.TaskStatus;

import java.time.LocalDate;

public class TaskMock {
    public static Task createMockTask() {
        Task task = new Task("Mock Task", TaskType.ENHANCEMENT, LocalDate.now(), 5, Priority.HIGH);
        task.setStatus(TaskStatus.TODO);
        return task;
    }
}
