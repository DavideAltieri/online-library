package com.assignment3.online_library;

import jakarta.persistence.*;

/* Classe entità che rappresenta un libro */
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    private int annoRilascio;
    private String autore;
    private String genere;

    protected Libro() {}

    public Libro(Long id, String titolo, String autore, String genere, int annoRilascio) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.annoRilascio = annoRilascio;
    }

    @Override
    public String toString() {
        return String.format(
                "Libro[id=%d, titolo='%s', autore='%s', genere='%s', annoRilascio='%s']",
                id, titolo, autore, genere, annoRilascio);
    }

    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public int getAnnoRilascio() {
        return annoRilascio;
    }

}

