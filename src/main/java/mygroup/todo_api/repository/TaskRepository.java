package mygroup.todo_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mygroup.todo_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByUserIdAndActiveTrue(Pageable pageable, Long userId);

}
