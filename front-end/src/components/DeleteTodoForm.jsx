import React from "react";
import { Modal, Box, Typography, Button } from "@mui/material";

export const DeleteTodoForm = ({ open, onClose, onConfirm, taskTitle }) => {
  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={{ ...modalStyle, width: 300 }}>
        <Typography variant="h6" component="h2">
          Confirmar Exclusão
        </Typography>
        <Typography sx={{ mt: 2 }}>
          Você tem certeza que deseja excluir a tarefa "{taskTitle}"?
        </Typography>
        <Box sx={{ mt: 3, display: "flex", justifyContent: "space-between" }}>
          <Button variant="contained" color="primary" onClick={onClose}>
            Cancelar
          </Button>
          <Button variant="contained" color="secondary" onClick={onConfirm}>
            Excluir
          </Button>
        </Box>
      </Box>
    </Modal>
  );
};

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};
