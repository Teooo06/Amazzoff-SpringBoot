package it.badoni.esempio1.controller;

import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller

@RequiredArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;

    @PostMapping("/salvaUtente")
    public String salvaUtenti(@RequestParam("username") String username,
                            @RequestParam("cognome") String cognome,
                            @RequestParam("nome") String nome,
                            @RequestParam("citta") String citta,
                            @RequestParam("email") String mail,
                            @RequestParam("dataNascita") LocalDate data,
                            @RequestParam("admin") int admin,
                            RedirectAttributes redirectAttributes)
    {
        Utente us = utenteService.getUtenteByUsername(username);
        Utente utente = new Utente(username, us.getPassword(), cognome, nome, citta, mail, data, admin );
        utenteService.aggiornaUtente(utente, username);

        String msg = "Utente salvato";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/utenti";
    }

    @PostMapping("/eliminaUtenti")
    public String eliminaUtenti(@RequestParam("user") String username,
                                RedirectAttributes redirectAttributes)
    {
        utenteService.eliminaUtente(username);
        String msg = "Utente eliminto";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/utenti";
    }

    @GetMapping("/aggiungiUtente")
    public String aggiungiUtente(@RequestParam("username1") String username,
                                 @RequestParam("cognome1") String cognome,
                                 @RequestParam("nome1") String nome,
                                 @RequestParam("citta1") String citta,
                                 @RequestParam("email1") String mail,
                                 @RequestParam("dataNascita1") LocalDate data,
                                 @RequestParam("admin1") int admin,
                                 @RequestParam("password") String pass,
                                 @RequestParam("passConf") String passC,
                                 RedirectAttributes redirectAttributes) {
        String msg;
        if (!pass.equals(passC)) {
            msg = "La password non corrisponde!";
            redirectAttributes.addAttribute("msg", msg);
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/utenti";
        }

        Utente utente = new Utente(username, pass, cognome, nome, citta, mail, data, admin);
        if (utente != null) {
            utenteService.aggiungiUtente(utente);
            msg = "Utente aggiunto con successo";
            redirectAttributes.addAttribute("msg", msg);
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:/utenti";
        }
        msg = "Errore nella generazione dell'Utente";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }

}
