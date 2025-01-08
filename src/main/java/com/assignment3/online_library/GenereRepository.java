package com.assignment3.online_library;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenereRepository extends CrudRepository<Genere, Long> {

    List<Genere> findByNome(String nome);

}
