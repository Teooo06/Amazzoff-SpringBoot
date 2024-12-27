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
public class RiservatoController {
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final AcquistoService acquistoService;

    @GetMapping("/riservato")
    public String riservato(RedirectAttributes redirectAttributes,Model model, HttpSession session, @RequestParam(required = false)  String msg) {
        Utente utente= (Utente) session.getAttribute("utente");
        model.addAttribute("elencoProdotti", prodottoService.getProdotti());
        model.addAttribute("msg", msg);
        model.addAttribute("err", "");



        if (utente != null) {
            return "riservato";
        }else{
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String addAcquisto(@RequestParam("id") String idProdotto, HttpSession session, RedirectAttributes redirectAttributes) {
        Utente utente= (Utente) session.getAttribute("utente");
        String ris = acquistoService.addAcquisto(utente.getUsername(), idProdotto);
        redirectAttributes.addAttribute("msg", ris);
        return "redirect:/riservato";
    }
}
