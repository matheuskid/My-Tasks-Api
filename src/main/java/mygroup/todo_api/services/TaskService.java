package mygroup.todo_api.services;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import mygroup.todo_api.model.Task;
import mygroup.todo_api.model.TaskRequestDTO;
import mygroup.todo_api.model.TaskResponseDTO;
import mygroup.todo_api.model.User;
import mygroup.todo_api.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDTO> listAll(Pageable pageable) {
        User user = resolveUser();
                            
        List<Task> activeTasks = taskRepository.findByUserIdAndActiveTrue(pageable, user.getId()).getContent();

        return activeTasks.stream()
            .map(this::convertToResponseDTO)
            .toList();
    }

    public TaskResponseDTO create(TaskRequestDTO newTask) {
        
        Task task = new Task();
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());  
        task.setDueDate(newTask.getDueDate());
        task.setActive(true);
        task.setCompleted(false);
        task.setUser(resolveUser());

        taskRepository.save(task);

        return convertToResponseDTO(task);
    }

    public void softDelete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setActive(false);
        taskRepository.save(task);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO updTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setTitle(updTask.getTitle());
        task.setDescription(updTask.getDescription());
        task.setDueDate(updTask.getDueDate());
        taskRepository.save(task);

        return convertToResponseDTO(task);
    }

    public TaskResponseDTO updateCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);

        return convertToResponseDTO(task);
    }

    private User resolveUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    private TaskResponseDTO convertToResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());

        return dto;
    }
    
}
