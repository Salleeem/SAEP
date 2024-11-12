package com.example.list.controller;

import com.example.list.model.User;
import com.example.list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Endpoint para registrar um novo usuário
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/login?error=usuario-existente"; // Redireciona para login com erro
        }
        userRepository.save(user); // Salva o novo usuário
        return "redirect:/login"; // Redireciona para a página de login
    }

    // Endpoint para login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        User user = userRepository.findByEmailAndSenha(email, senha);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getNome());
            return "redirect:/dashboard"; // Redireciona para a dashboard após login
        }
        return "redirect:/login?erro=true"; // Se o login falhar, retorna para o login
    }
}
