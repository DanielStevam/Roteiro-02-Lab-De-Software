import { EditTodoForm } from "./EditTodoForm";
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";

export const TodoList = ({
  task,
  deleteTodo,
  editTodo,
  toggleComplete,
  saveTodo,
}) => {
  return (
    <div className="Todo">
      {task.isEditing ? (
        <EditTodoForm task={task} saveTodo={saveTodo} />
      ) : (
        <>
          <p
            className={`${task.completed ? "completed" : "incompleted"}`}
            onClick={() => toggleComplete(task.id)}
          >
            {task.description}
          </p>
          <div>
            <FontAwesomeIcon
              className="edit-icon"
              icon={faPenToSquare}
              onClick={() => editTodo(task.id)}
            />
            <FontAwesomeIcon
              className="trash-icon"
              icon={faTrash}
              onClick={() => deleteTodo(task.id)}
            />
          </div>
        </>
      )}
    </div>
  );
};
