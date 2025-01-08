package com.assignment3.online_library;

import jakarta.persistence.*;

public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;

    protected Autore() {}

    public Autore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return String.format(
                "Autore[id=%d, nome='%s', cognome='%s']",
                id, nome, cognome);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

}
