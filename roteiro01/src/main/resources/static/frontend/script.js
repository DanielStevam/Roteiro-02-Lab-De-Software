// script.js

// Função para buscar as tarefas do backend e atualizar a interface
function fetchTasks() {
  fetch("/tasks")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Erro ao buscar as tarefas");
      }
      return response.json();
    })
    .then((tasks) => {
      // Limpar a lista de tarefas atual
      const taskList = document.getElementById("task-list");
      taskList.innerHTML = "";

      // Iterar sobre as tarefas recebidas do backend
      tasks.forEach((task) => {
        // Criar um item de lista para cada tarefa
        const listItem = document.createElement("li");
        listItem.textContent = task.description;

        // Adicionar o item de lista à lista de tarefas
        taskList.appendChild(listItem);
      });
    })
    .catch((error) => {
      console.error("Erro ao buscar as tarefas:", error);
    });
}

// Função para enviar uma nova tarefa para o backend
function createTask(description) {
  fetch("/tasks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ description }),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Erro ao criar a tarefa");
      }
      return response.json();
    })
    .then((newTask) => {
      // Atualizar a lista de tarefas após a criação bem-sucedida
      fetchTasks();
    })
    .catch((error) => {
      console.error("Erro ao criar a tarefa:", error);
    });
}

// Event listener para o formulário de criação de tarefas
document.getElementById("task-form").addEventListener("submit", (event) => {
  event.preventDefault(); // Evitar o comportamento padrão de recarregar a página

  // Obter a descrição da nova tarefa do formulário
  const description = document.getElementById("task-description").value;

  // Chamar a função para criar a nova tarefa
  createTask(description);

  // Limpar o campo de descrição após a criação da tarefa
  document.getElementById("task-description").value = "";
});

// Chamar a função para buscar as tarefas quando a página carregar
window.onload = fetchTasks;
