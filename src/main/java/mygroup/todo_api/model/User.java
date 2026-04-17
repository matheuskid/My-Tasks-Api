package mygroup.todo_api.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aqui dizemos ao Spring quais permissões esse usuário tem.
        // Se for ADMIN, ele tem permissão de ADMIN e de USER.
        if (this.role == RoleEnum.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ADMIN"), 
                new SimpleGrantedAuthority("USER")
            );
        } 
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public Long getId() {
        return id;
    }
}
