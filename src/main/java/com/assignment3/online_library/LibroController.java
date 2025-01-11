package com.assignment3.online_library;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;

/* Controller delle richieste provenienti da "libri" */
@Controller
@RequestMapping("/libri")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /* Mapping che restituisce la lista di libri da mostrare. Considera anche l'ordine richiesto
    dall'utente (ascendente o discendente) */
    @GetMapping
    public String getLibri(@RequestParam(value = "sortBy", defaultValue = "titolo") String sortBy,
                           @RequestParam(value = "order", defaultValue = "asc") String order,
                           Model model) {

        List<Libro> libri = (List<Libro>) libroRepository.findAll();

        Comparator<Libro> comparator; // Memorizza il campo che verrà utilizzato per l'ordinamento
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

        // Inverte l'ordine se richiesto dall'utente
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
    @GetMapping("/aggiungi")
    public String showAddLibroForm(Model model) {
        model.addAttribute("libro", new Libro());
        return "aggiungi_libro";
    }

    // Salva un nuovo libro nel DB
    @PostMapping("/aggiungi")
    public String addLibro(@ModelAttribute Libro libro, Model model) {
        // Verifica se il libro esiste già verificando nome e autore
        List<Libro> libriEsistenti = libroRepository.findByTitoloAndAutore(libro.getTitolo(), libro.getAutore());
        if (!libriEsistenti.isEmpty()) {
            // Se il libro esiste già, aggiunge un messaggio di errore al modello
            model.addAttribute("errore", "Il libro esiste già nel database.");
            return "aggiungi_libro";  // Torna alla pagina di aggiunta con il messaggio di errore
        }
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

