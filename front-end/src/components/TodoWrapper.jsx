import React, { useState, useEffect } from "react";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";
import { v4 as uuidv4 } from "uuid";
import todoService from "../service/todoService";

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    const fetchTodos = async () => {
      try {
        const response = await todoService.getTodo();
        setTodos(response.data);
      } catch (error) {
        console.error("Erro ao buscar todos:", error);
      }
    };

    fetchTodos();
  }, []);

  const addTodo = (todo) => {
    setTodos([...todos, { id: uuidv4(), description: todo, completed: false }]);
  };

  const deleteTodo = (id) => setTodos(todos.filter((todo) => todo.id !== id));

  const editTodo = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, isEditing: !todo.isEditing } : todo
      )
    );
  };

  return (
    <div className="TodoWrapper">
      <h1>Lista de Tarefas</h1>
      <TodoForm addTodo={addTodo} />
      {todos.map((todo) => (
        <TodoList
          key={todo.id}
          task={todo}
          deleteTodo={deleteTodo}
          editTodo={editTodo}
        />
      ))}
    </div>
  );
};
