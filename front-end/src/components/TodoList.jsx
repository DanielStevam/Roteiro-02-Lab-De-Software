import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import {
  Card,
  CardContent,
  Typography,
  IconButton,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Grid,
} from "@mui/material";
import { EditTodoForm } from "./EditTodoForm";
import { DeleteTodoForm } from "./DeleteTodoForm";
import todoService from "../services/todoService";

const getStatusStyles = (status) => {
  switch (status) {
    case "FEITO":
      return {
        borderColor: "success.main",
        backgroundColor: "rgba(76, 175, 80, 0.1)",
      };
    case "EM_PROGRESSO":
      return {
        borderColor: "warning.main",
        backgroundColor: "rgba(255, 193, 7, 0.1)",
      };
    case "PENDENTE":
      return {
        borderColor: "info.main",
        backgroundColor: "rgba(33, 150, 243, 0.1)",
      };
    default:
      return {
        borderColor: "default",
        backgroundColor: "transparent",
      };
  }
};

export const TodoList = ({ task, deleteTodo, editTodo, saveTodo }) => {
  const [isEditOpen, setIsEditOpen] = useState(false);
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);
  const [currentTask, setCurrentTask] = useState(null);
  const [status, setStatus] = useState(task.status || "TODO");

  const getType = () => {
    if (task.dueDate && task.dueDays && task.dueDays !== 0) return "Prazo";
    if (task.dueDate) return "Data";
    return "Livre";
  };

  const calculateDueDays = (dueDate) => {
    const currentDate = new Date();
    const dueDateObj = new Date(dueDate);
    const diffTime = dueDateObj - currentDate;
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  };

  const handleStatusChange = async (event) => {
    const newStatus = event.target.value;
    setStatus(newStatus);

    const updatedTask = {
      description: task.description,
      type: task.type,
      priority: task.priority,
      dueDate: task.dueDate,
      dueDays: task.dueDays,
      status: newStatus,
    };

    try {
      await todoService.updateTodo(task.id, updatedTask);
    } catch (error) {
      console.error("Erro ao atualizar o status da tarefa:", error);
    }
  };

  const handleEditOpen = (task) => {
    setCurrentTask(task);
    setIsEditOpen(true);
  };

  const handleEditClose = () => {
    setCurrentTask(null);
    setIsEditOpen(false);
  };

  const handleDeleteOpen = () => setIsDeleteOpen(true);
  const handleDeleteClose = () => setIsDeleteOpen(false);

  const handleDeleteConfirm = async () => {
    await deleteTodo(task.id);
    handleDeleteClose();
  };

  return (
    <>
      <Grid item xs={12} sm={6} md={4}>
        <Card
          className="Todo"
          sx={{
            marginBottom: 2,
            height: 350,
            borderColor: getStatusStyles(status).borderColor,
            borderWidth: 2,
            borderStyle: "solid",
            backgroundColor: getStatusStyles(status).backgroundColor,
          }}
        >
          <CardContent>
            <Typography variant="h5" component="div">
              {task.description}
            </Typography>
            <Typography color="textSecondary">Tipo: {getType()}</Typography>
            <Typography color="textSecondary">
              Tipo de Task: {task.type}
            </Typography>
            <Typography color="textSecondary">
              Prioridade: {task.priority}
            </Typography>
            {task.dueDate && (
              <Typography color="textSecondary">
                Data de Vencimento: {task.dueDate}
              </Typography>
            )}
            {task.dueDate && (
              <Typography color="textSecondary">
                Deadline: {calculateDueDays(task.dueDate)} dias
              </Typography>
            )}
            {task.dueDays !== null && (
              <Typography color="textSecondary">
                Prazo: {task.dueDays}
              </Typography>
            )}
            <FormControl fullWidth margin="normal">
              <InputLabel>Status</InputLabel>
              <Select value={status} onChange={handleStatusChange}>
                <MenuItem value="TODO">To do</MenuItem>
                <MenuItem value="IN_PROGRESS">In progress</MenuItem>
                <MenuItem value="DONE">Done</MenuItem>
                <MenuItem value="PENDING">Pending</MenuItem>
              </Select>
            </FormControl>
            <div>
              <IconButton onClick={() => handleEditOpen(task)}>
                <FontAwesomeIcon icon={faPenToSquare} />
              </IconButton>
              <IconButton onClick={handleDeleteOpen}>
                <FontAwesomeIcon icon={faTrash} />
              </IconButton>
            </div>
          </CardContent>
        </Card>
      </Grid>
      {currentTask && (
        <EditTodoForm
          task={currentTask}
          saveTodo={saveTodo}
          open={isEditOpen}
          onClose={handleEditClose}
        />
      )}
      <DeleteTodoForm
        open={isDeleteOpen}
        onClose={handleDeleteClose}
        onConfirm={handleDeleteConfirm}
        taskTitle={task.description}
      />
    </>
  );
};
