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
    public String getLibri(@RequestParam(value = "sortBy", defaultValue = "titolo") String sortBy, Model model) {
        List<Libro> libri = (List<Libro>) libroRepository.findAll();

        // Ordinamento dinamico
        switch (sortBy) {
            case "titolo":
                libri.sort(Comparator.comparing(Libro::getTitolo));
                break;
            case "autore":
                libri.sort(Comparator.comparing(Libro::getAutore));
                break;
            case "annoRilascio":
                libri.sort(Comparator.comparingInt(Libro::getAnnoRilascio));
                break;
            case "genere":
                libri.sort(Comparator.comparing(Libro::getGenere));
                break;
            default:
                throw new IllegalArgumentException("Campo di ordinamento non valido: " + sortBy);
        }


        model.addAttribute("libri", libri);
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
        return "edit-libro";
    }

    // Aggiorna un libro
    @PostMapping("/modifica/{id}")
    public String updateLibro(@PathVariable Long id, @ModelAttribute Libro libro) {
        // libro.setId(id); // Assicura che l'ID rimanga invariato
        libroRepository.save(libro);
        return "redirect:/libri"; // Torna alla lista dei libri
    }

    // Elimina un libro
    @GetMapping("/elimina/{id}")
    public String deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return "redirect:/libri"; // Torna alla lista dei libri
    }
}

