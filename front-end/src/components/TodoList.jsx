import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  IconButton,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Paper,
} from "@mui/material";
import { EditTodoForm } from "./EditTodoForm";
import { DeleteTodoForm } from "./DeleteTodoForm";
import todoService from "../services/todoService";

export const TodoList = ({ todos, deleteTodo, editTodo, saveTodo }) => {
  const [isEditOpen, setIsEditOpen] = useState(false);
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);
  const [currentTask, setCurrentTask] = useState(null);
  const [status, setStatus] = useState(null);

  const handleStatusChange = async (event, task) => {
    const newStatus = event.target.value;
    setStatus(newStatus);

    const updatedTask = {
      ...task,
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

  const handleDeleteOpen = (task) => {
    setCurrentTask(task);
    setIsDeleteOpen(true);
  };

  const handleDeleteClose = () => {
    setCurrentTask(null);
    setIsDeleteOpen(false);
  };

  const handleDeleteConfirm = async () => {
    if (currentTask) {
      await deleteTodo(currentTask.id);
      handleDeleteClose();
    }
  };

  return (
    <Paper>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Descrição</TableCell>
            <TableCell>Tipo</TableCell>
            <TableCell>Prioridade</TableCell>
            <TableCell>Data de Vencimento</TableCell>
            <TableCell>Prazo</TableCell>
            <TableCell>Status</TableCell>
            <TableCell>Ações</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {todos.map((task) => (
            <TableRow key={task.id}>
              <TableCell>{task.description}</TableCell>
              <TableCell>{task.type}</TableCell>
              <TableCell>{task.priority}</TableCell>
              <TableCell>{task.dueDate}</TableCell>
              <TableCell>{task.dueDays}</TableCell>
              <TableCell>
                <FormControl fullWidth>
                  <InputLabel>Status</InputLabel>
                  <Select
                    value={task.status || "A_FAZER"}
                    onChange={(event) => handleStatusChange(event, task)}
                  >
                    <MenuItem value="A_FAZER">A fazer</MenuItem>
                    <MenuItem value="EM_PROGRESSO">Em progresso</MenuItem>
                    <MenuItem value="FEITO">Feito</MenuItem>
                    <MenuItem value="PENDENTE">Pendente</MenuItem>
                  </Select>
                </FormControl>
              </TableCell>
              <TableCell>
                <IconButton onClick={() => handleEditOpen(task)}>
                  <FontAwesomeIcon icon={faPenToSquare} />
                </IconButton>
                <IconButton onClick={() => handleDeleteOpen(task)}>
                  <FontAwesomeIcon icon={faTrash} />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
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
        taskTitle={currentTask ? currentTask.description : ""}
      />
    </Paper>
  );
};
