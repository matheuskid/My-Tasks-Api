package mygroup.todo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mygroup.todo_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String login);
    
}
