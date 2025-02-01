package com.assignment3.online_library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibroTest {

    @Test
    void testLibroConstructorAndGetters() {
        Libro libro = new Libro((long)999, "TitoloTest", "AutoreTest", "GenereTest", 2025);

        assertEquals(999, libro.getId());
        assertEquals("TitoloTest", libro.getTitolo());
        assertEquals("AutoreTest", libro.getAutore());
        assertEquals("GenereTest", libro.getGenere());
        assertEquals(2025, libro.getAnnoRilascio());
    }
}

