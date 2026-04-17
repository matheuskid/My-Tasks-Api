package mygroup.todo_api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDTO {

    @NotBlank(message = "O título não pode ser vazio!")
    @NotNull(message = "O título não pode ser nulo!")
    private String title;

    @NotBlank(message = "A descrição não pode ser vazia!")
    @NotNull(message = "A descrição não pode ser nula!")
    private String description;

    @NotNull(message = "A data não pode ser nula!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    public TaskRequestDTO(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}