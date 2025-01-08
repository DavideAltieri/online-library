package com.assignment3.online_library;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AutoreRepository extends CrudRepository<Autore, Long> {

    List<Autore> findByNome(String nome);

    List<Autore> findByCognome(String cognome);

    List<Autore> findByNomeAndCognome(String nome, String cognome);
}
