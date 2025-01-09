package com.assignment3.online_library;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libri")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {

        this.libroRepository = libroRepository;

    }

    @GetMapping
    public List<Libro> getAllLibri() {
        return (List<Libro>) libroRepository.findAll();
    }

    @PostMapping
    public Libro addLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libro) {

        // Trova il libro esistente
        Libro existingLibro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con ID: " + id));

        // Aggiorna i campi
        if (libro.getTitolo() != null) {
            existingLibro.setTitolo(libro.getTitolo());
        }
        if (libro.getAnnoRilascio() >= 0) {
            existingLibro.setAnnoRilascio(libro.getAnnoRilascio());
        }
        if (libro.getAutore() != null) {
            existingLibro.setAutore(libro.getAutore());
        }
        if (libro.getGenere() != null) {
            existingLibro.setGenere(libro.getGenere());
        }
        // Salva il libro aggiornato
        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}

