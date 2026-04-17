package mygroup.todo_api.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mygroup.todo_api.services.TaskService;
import mygroup.todo_api.model.TaskRequestDTO;
import mygroup.todo_api.model.TaskResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Lista todas as tarefas ativas por usuário", description = "Retorna apenas as tarefas que não sofreram soft delete")
    @GetMapping
    public List<TaskResponseDTO> getTasks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) 
        {   
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            return taskService.listAll(pageable);
    }

    @Operation(summary = "Cria uma nova tarefa")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskRequestDTO));
    }

    @Operation(summary = "Atualiza o status da tarefa")
    @PatchMapping("/{id}/completed")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateCompleted(id));
    }

    @Operation(summary = "Atualiza uma tarefa existente")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, taskDetails));
    }

    @Operation(summary = "Realiza a exclusão lógica de uma tarefa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.softDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
