package it.badoni.esempio1.controller;
import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.service.ProdottoService;
import it.badoni.esempio1.service.UtenteService;
import it.badoni.esempio1.service.AcquistoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class StoricoController {
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final AcquistoService acquistoService;

    @GetMapping("/acquisti")
    public String visualizzaStoricoAcquisti(RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente == null) {
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
        model.addAttribute("elencoAcquisti", acquistoService.getAcquisti(utente));
        model.addAttribute("utente", utente.getUsername());
        return "carrello";
    }
}
