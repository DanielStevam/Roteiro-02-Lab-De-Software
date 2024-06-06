import React from "react-dom";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";


export const TodoWrapper = () => {
    return (
        <div className="TodoWrapper">
            <h1>To Do List</h1>
            <TodoForm />
            <TodoList/>
        </div>
    )
}

