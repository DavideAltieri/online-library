package com.assignment3.online_library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroControllerTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroController libroController;

    @Test
    void testGetLibri() {
        // Mock dei dati
        Libro libro1 = new Libro(1L, "Titolo A", "Autore A", "Genere A", 2025);
        Libro libro2 = new Libro(2L, "Titolo B", "Autore B", "Genere B", 2024);
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        Model model = new ConcurrentModel();
        String result = libroController.getLibri("titolo", "asc", model);
        assertEquals("libri", result); // Verifica che venga restituito il nome della vista
        assertEquals(Arrays.asList(libro1, libro2), model.getAttribute("libri")); // Verifica che i libri siano presenti
        verify(libroRepository, times(1)).findAll(); // Verifica che il metodo findAll() sia stato chiamato
    }

    @Test
    void testShowAddLibroForm() {
        Model model = new ConcurrentModel();
        String result = libroController.showAddLibroForm(model);
        assertEquals("aggiungi_libro", result); // Verifica che venga restituito il nome della vista
    }

    @Test
    void testAddLibro() {
        Libro nuovoLibro = new Libro(null, "Titolo A", "Autore A", "Genere A", 2025);
        // Simula che il libro non esista
        when(libroRepository.findByTitoloAndAutore("Titolo A", "Autore A")).thenReturn(Collections.emptyList());
        when(libroRepository.save(nuovoLibro)).thenReturn(new Libro(1L, "Titolo A", "Autore A", "Genere A", 2025));

        Model model = new ConcurrentModel();
        String result = libroController.addLibro(nuovoLibro, model);
        assertEquals("redirect:/libri", result); // Verifica il redirect
        verify(libroRepository, times(1)).save(nuovoLibro); // Verifica che il libro sia stato salvato
    }

    @Test
    void testAddLibroAlreadyExists() {
        Libro nuovoLibro = new Libro(null, "Titolo A", "Autore A", "Genere A", 2025);

        // Simula che il libro esista già
        when(libroRepository.findByTitoloAndAutore("Titolo A", "Autore A"))
                .thenReturn(Collections.singletonList(nuovoLibro));

        Model model = new ConcurrentModel();
        String result = libroController.addLibro(nuovoLibro, model);
        assertEquals("aggiungi_libro", result); // Verifica che torni alla pagina di aggiunta
        assertEquals("Il libro esiste già nel database.", model.getAttribute("errore")); // Verifica il messaggio di errore
        verify(libroRepository, never()).save(nuovoLibro); // Verifica che il libro non sia stato salvato
    }

    @Test
    void testShowUpdateLibroForm() {
        Libro libro = new Libro(1L, "Titolo A", "Autore A", "Genere A", 2025);
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        Model model = new ConcurrentModel();
        String result = libroController.showUpdateLibroForm(1L, model);
        assertEquals("modifica_libro", result); // Verifica che venga restituito il nome della vista
        assertEquals(libro, model.getAttribute("libro")); // Verifica che il libro sia presente
    }

    @Test
    void testUpdateLibro() {
        Libro libro = new Libro(1L, "Titolo A", "Autore A", "Genere A", 2025);
        String result = libroController.updateLibro(libro);
        assertEquals("redirect:/libri", result); // Verifica il redirect
        verify(libroRepository, times(1)).save(libro); // Verifica che il libro sia stato salvato
    }

    @Test
    void testDeleteLibro() {
        String result = libroController.deleteLibro(1L);
        assertEquals("redirect:/libri", result); // Verifica il redirect
        verify(libroRepository, times(1)).deleteById(1L); // Verifica che il libro sia stato eliminato
    }
}
