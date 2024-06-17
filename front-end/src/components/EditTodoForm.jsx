import React, { useState } from "react";

export const EditTodoForm = ({ task, editTodo, saveTodo }) => {
  const [value, setValue] = useState(task.description);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (value) {
      saveTodo(task.id, value);
    }
  };

  return (
    <form className="TodoForm" onSubmit={handleSubmit}>
      <input
        type="text"
        value={value}
        onChange={(e) => setValue(e.target.value)}
        className="todo-input"
        placeholder="Descrição da Tarefa"
      />
      <button type="submit" className="todo-btn">
        Salvar
      </button>
    </form>
  );
};
