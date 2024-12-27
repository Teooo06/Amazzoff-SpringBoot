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
public class CarrelloController {
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final AcquistoService acquistoService;

    @GetMapping("/carrello")
    public String visualizzaCarrello(RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente == null) {
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
        model.addAttribute("elencoProdotti", acquistoService.getAcquisti(utente));
        model.addAttribute("utente", utente.getUsername());
        return "carrello";
    }

    @RequestMapping("/compra")
    public String compraProdotti(RedirectAttributes redirectAttributes, Model model, HttpSession session){
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente == null) {
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }

        String ris = acquistoService.compra(utente);
        model.addAttribute("msg", ris);

        return "redirect:/carrello";
    }
}
