package com.assignment3.online_library;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    List<Libro> findByTitoloAndAutore(String titolo, String autore);

}
