package mygroup.todo_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean completed = false;
    
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDate updatedAt;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate dueDate;
    
    private LocalDate deletedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 1. No-Argument Constructor (Required by JPA)
    public Task() {
    }

    // 2. All-Arguments Constructor (Optional, useful for testing)
    public Task(String title, String description, boolean completed, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.active = true;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // 3. Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getCompleted() { 
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() { return dueDate; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDate getDeletedAt() { return deletedAt; }

    public void setDeletedAt(LocalDate deletedAt) { this.deletedAt = deletedAt; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    // 4. toString() (Optional, good for debugging)
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", active=" + active +
                ", completed=" + completed +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", dueDate=" + dueDate +
                ", deletedAt=" + deletedAt +
                '}';
    }
}