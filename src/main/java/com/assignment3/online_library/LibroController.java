package com.assignment3.online_library;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/libri")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /*
    //Visualizza tutti i libri
    @GetMapping
    public String getAllLibri(Model model) {
        List<Libro> libri = (List<Libro>) libroRepository.findAll();
        model.addAttribute("libri", libri);
        return "libri";
    }

     */

    @GetMapping
    public String getLibri(@RequestParam(value = "sortBy", defaultValue = "titolo") String sortBy,
                           @RequestParam(value = "order", defaultValue = "asc") String order,
                           Model model) {

        List<Libro> libri = (List<Libro>) libroRepository.findAll();

        Comparator<Libro> comparator;
        // Ordinamento dinamico
        switch (sortBy) {
            case "titolo":
                comparator = Comparator.comparing(Libro::getTitolo);
                break;
            case "autore":
                comparator = Comparator.comparing(Libro::getAutore);
                break;
            case "annoRilascio":
                comparator = Comparator.comparingInt(Libro::getAnnoRilascio);
                break;
            case "genere":
                comparator = Comparator.comparing(Libro::getGenere);
                break;
            default:
                throw new IllegalArgumentException("Campo di ordinamento non valido: " + sortBy);
        }

        // Inverti l'ordine se necessario
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        libri.sort(comparator);
        model.addAttribute("libri", libri);
        model.addAttribute("currentSortBy", sortBy);
        model.addAttribute("currentOrder", order);
        return "libri";
    }

    // Mostra il form per aggiungere un nuovo libro
    @GetMapping("/nuovo")
    public String showAddLibroForm(Model model) {
        model.addAttribute("libro", new Libro()); // Modello vuoto per il form
        return "add-libro";
    }

    // Salva un nuovo libro
    @PostMapping
    public String addLibro(@ModelAttribute Libro libro) {
        libroRepository.save(libro);
        return "redirect:/libri"; // Torna alla lista dei libri
    }

    // Mostra il form per modificare un libro
    @GetMapping("/modifica/{id}")
    public String showUpdateLibroForm(@PathVariable Long id, Model model) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato con ID: " + id));
        model.addAttribute("libro", libro);
        return "modifica_libro";
    }

    // Aggiorna un libro
    @PostMapping("/modifica")
    public String updateLibro(Libro libro) {
        System.out.println("Libro ID: " + libro.getId());
        System.out.println("Titolo: " + libro.getTitolo());
        System.out.println("Autore: " + libro.getAutore());
        System.out.println("Anno di Rilascio: " + libro.getAnnoRilascio());
        System.out.println("Genere: " + libro.getGenere());
        libroRepository.save(libro); // Salva il libro aggiornato
        return "redirect:/libri";   // Ritorna alla lista dei libri
    }

    // Elimina un libro
    @GetMapping("/elimina/{id}")
    public String deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return "redirect:/libri"; // Torna alla lista dei libri
    }
}

