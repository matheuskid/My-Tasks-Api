package mygroup.todo_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
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
}
