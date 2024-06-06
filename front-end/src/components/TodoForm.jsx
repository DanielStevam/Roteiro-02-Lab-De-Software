import React from "react";
export const TodoForm = () => {
    return (
        <form className="TodoForm">
            <input
                type="text"
                className="todo-input"
                placeholder="What's the news?"
            />
            <button type="submit" className="todo-btn">+</button>
        </form>
    )
}