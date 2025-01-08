package com.assignment3.online_library;

import jakarta.persistence.*;

public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    protected Genere() {}

    public Genere(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format(
                "Autore[id=%d, nome='%s']",
                id, nome);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
