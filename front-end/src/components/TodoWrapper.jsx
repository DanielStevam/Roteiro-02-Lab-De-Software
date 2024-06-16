import React, { useState } from 'react';
import axios from 'axios';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([
    { id: 1, description: 'Tarefa exemplo', completed: false }
  ]);

  const addTodo = async (description) => {
    const newTodo = {
      id: Math.random(),
      description: description,
      type: "FEATURE",
      dueDate: new Date().toISOString().split('T')[0], // Data atual no formato yyyy-mm-dd
      dueDays: 0,
      priority: "LOW",
      status: "TODO"
    };

    try {
      const response = await axios.post('http://localhost:8080/tasks', newTodo, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      setTodos([...todos, response.data]);
    } catch (error) {
      console.error('Error creating task:', error);
    }
  };

  return (
    <div className='TodoWrapper'>
      <h1>Lista de Tarefas</h1>
      <TodoForm addTodo={addTodo} />
      {todos.map((item) => 
        <TodoList key={item.id} task={item} />
      )}
    </div>
  );
};
