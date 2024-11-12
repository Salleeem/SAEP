package com.example.list.controller;

import com.example.list.model.Tarefa;
import com.example.list.repository.TarefaRepository;
import com.example.list.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UserRepository userRepository;

    // Cria uma nova tarefa associada ao usuário
    @PostMapping("/create")
    public String createTask(HttpSession session,
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String setor,
            @RequestParam(defaultValue = "A Fazer") String status,
            @RequestParam String prioridade) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {   
            return "redirect:/login"; // Se não houver um userId na sessão, redireciona para o login
        }

        // Agora você pode usar o userId que vem da sessão para buscar o usuário
        return userRepository.findById(userId).map(user -> {
            Tarefa tarefa = new Tarefa(titulo, descricao, setor, status, prioridade, new Date());
            tarefa.setUser(user); // Associa o usuário à tarefa
            tarefaRepository.save(tarefa);
            return "redirect:/dashboard"; // Redireciona para a dashboard após salvar
        }).orElse("redirect:/erro"); // Se não encontrar o usuário, redireciona para erro
    }

}
