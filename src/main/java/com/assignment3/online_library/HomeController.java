package com.assignment3.online_library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /* Indirizza automaticamente l'utente alla pagina dei libri (che funge da home dell'applicazione) */
    @GetMapping("/")
    public String redirectToLibri() {
        return "redirect:/libri";
    }
}
