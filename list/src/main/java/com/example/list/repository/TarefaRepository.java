package com.example.list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.list.model.Tarefa;
import com.example.list.model.User;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByUser(User user); // Método para buscar tarefas do usuário
}
