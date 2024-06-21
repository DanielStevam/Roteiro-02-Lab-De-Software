import React, { useState, useEffect } from "react";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";
export const TodoWrapperService = () => {
  const [todos, setTodos] = useState([]);
  useEffect(() => {
    const savedTodos = JSON.parse(localStorage.getItem("todos")) || [];
    setTodos(savedTodos);
  }, []);
  return (
    <div className="TodoWrapper">
      <h1>Lista de Tarefas! (Service)</h1>
      <TodoForm />
      {todos.map((todo, index) => (
        <TodoList task={todo} key={index} />
      ))}
    </div>
  );
};
