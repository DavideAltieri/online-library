package com.assignment3.online_library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void testHomePage() {
        HomeController homeController = new HomeController();

        String result = homeController.redirectToLibri();

        assertEquals("redirect:/libri", result); // Verifica che il redirect sia corretto
    }
}
