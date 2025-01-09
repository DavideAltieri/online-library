package com.assignment3.online_library;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    List<Libro> findByTitolo(String titolo);

    List<Libro> findByAutore(String autore);

    List<Libro> findByGenere(String genere);

    List<Libro> findByAnnoRilascio(int annoRilascio);

}
