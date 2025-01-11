package com.assignment3.online_library;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    List<Libro> findByTitolo(String titolo);

    List<Libro> findByAutore(String autore);

    List<Libro> findByGenere(String genere);

    List<Libro> findByAnnoRilascio(int annoRilascio);

    List<Libro> findByTitoloAndAutore(String titolo, String autore);

    boolean existsByTitoloAndAutore(@Param("titolo") String titolo, @Param("autore") String autore);


}
