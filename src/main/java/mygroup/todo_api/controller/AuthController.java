package mygroup.todo_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import mygroup.todo_api.model.AuthDTO;
import mygroup.todo_api.model.LoginResponseDTO;
import mygroup.todo_api.model.User;
import mygroup.todo_api.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
        // 1. Cria um objeto de autenticação com login/senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        
        // 2. O Spring Security tenta autenticar (vai ao banco, checa o hash do BCrypt)
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 3. Se deu certo, gera o token para o usuário
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }
}
