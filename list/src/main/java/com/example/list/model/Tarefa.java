package com.example.list.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descricao;
    private String setor;
    private String status;
    private String prioridade;

    @Temporal(TemporalType.DATE)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Construtor com parâmetros
    public Tarefa(String titulo, String descricao, String setor, String status, String prioridade, Date data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.setor = setor;
        this.status = status;
        this.prioridade = prioridade;
        this.data = data;
    }

    // Construtor padrão (necessário para JPA)
    public Tarefa() {
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
