package com.assignment3.online_library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LibroRepository libroRepository;

    public DataInitializer(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Creazione di alcuni libri
        Libro libro1 = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", "Fantasy", 1954);
        Libro libro2 = new Libro("1984", "George Orwell", "Sci-Fi", 1949);
        Libro libro3 = new Libro("I promessi sposi", "Alessandro Manzoni", "Romanzo", 1825);
        libroRepository.save(libro1);
        libroRepository.save(libro2);
        libroRepository.save(libro3);

        System.out.println("Dati inizializzati nel database.");
    }
}

