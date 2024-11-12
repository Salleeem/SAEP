package com.example.list.controller;

import com.example.list.model.User;
import com.example.list.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RouteController {

    @Autowired
    private UserRepository userRepository;

    // Página inicial
    @GetMapping("/")
    public String showHomePage() {
        return "index"; // Renderiza a página inicial
    }

    // Página de login
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Renderiza a página de login
    }

    // Página de cadastro
    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "cadastro"; // Renderiza a página de cadastro
    }

    // Página de dashboard
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login"; // Se não estiver logado, redireciona para login
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("userName", user.getNome());
            return "dashboard"; // Exibe o dashboard
        }

        return "redirect:/login"; // Se não encontrar o usuário, redireciona para o login
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidar a sessão
        return "redirect:/"; // Redireciona para a página inicial
    }

    @GetMapping("/cadastro-tarefa")
    public String showCadastroTarefaPage() {
        return "cadastro-tarefa"; // Nome do arquivo HTML a ser renderizado, sem extensão
    }

}
