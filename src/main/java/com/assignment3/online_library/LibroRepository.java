package com.assignment3.online_library;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    List<Libro> findByTitolo(String titolo);

    List<Libro> findByAutore(Autore autore);

    List<Libro> findByGenere(Genere genere);

    List<Libro> findByAnnoRilascio(String annoRilascio);

}
