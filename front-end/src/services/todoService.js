import axios from "../axios";

const todoService = {
  getTodo() {
    return axios.get("/tasks");
  },
  updateTodo(id, body) {
    return axios.put(`/tasks/${id}`, body);
  },
  deleteTodo(id) {
    return axios.delete(`/tasks/${id}`);
  },
  criarTodo(body) {
    return axios.post("/tasks", body);
  },
};

export default todoService;
