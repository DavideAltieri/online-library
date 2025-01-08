package com.assignment3.online_library;

import jakarta.persistence.*;


@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    private String annoRilascio;
    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;
    @ManyToOne
    @JoinColumn(name = "genere_id")
    private Genere genere;

    protected Libro() {}

    public Libro(String titolo, Autore autore, Genere genere, String annoRilascio) {
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.annoRilascio = annoRilascio;
    }

    @Override
    public String toString() {
        return String.format(
                "Libro[id=%d, titolo='%s', autore='%s', genere='%s', annoRilascio='%s']",
                id, titolo, autore.getNome() + "" + autore.getCognome(), genere.getNome(), annoRilascio);
    }

    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore.getNome() + "" + autore.getCognome();
    }

    public String getGenere() {
        return genere.getNome();
    }

    public String getAnnoRilascio() {
        return annoRilascio;
    }
}

