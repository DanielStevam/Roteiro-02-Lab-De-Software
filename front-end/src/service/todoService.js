import axios from "../axios";

const todoService = {
  getTodo() {
    return axios.get("/tasks");
  },
};

export default todoService;
