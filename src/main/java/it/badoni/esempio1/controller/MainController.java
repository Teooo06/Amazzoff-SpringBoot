package it.badoni.esempio1.controller;
import it.badoni.esempio1.model.Prodotto;
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

import java.time.LocalDate;


@Controller

@RequiredArgsConstructor
public class MainController {
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final AcquistoService acquistoService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false)  String msg) {
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping("/utenti")
    public String utenti(Model model) {
        model.addAttribute("utenti",utenteService.getUtenti());
        return "utenti";
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes, Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        //
        Utente utente=utenteService.getUtenteByUsernameAndPassword(username, password);
        if (utente != null) {
            session.setAttribute("utente", utente);
            return "redirect:/riservato";
        }else{
            redirectAttributes.addAttribute("msg", "Credenziali errate");
            return "redirect:/";
        }
    }

    @GetMapping("/formProdotto")
    public String formProdotto( Model model) {
        model.addAttribute("prodotto",new Prodotto());
        return "prodotto";
    }

    @PostMapping("/updateProdotto")
    public String updateProdotto(@ModelAttribute("prodotto") Prodotto prodotto, Model model) {
        model.addAttribute("prodotto",new Prodotto());
        prodottoService.addProdotto(prodotto);
        return "prodotto";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/registrazione")
    public String toRegPage(){
        return "/registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazione(RedirectAttributes redirectAttributes, Model model, HttpSession session,
                                @RequestParam("username") String username,
                                @RequestParam("cognome") String cognome,
                                @RequestParam("nome") String nome,
                                @RequestParam("citta") String citta,
                                @RequestParam("mail") String mail,
                                @RequestParam("data") String data,
                                @RequestParam("password") String pass,
                                @RequestParam("confPassword") String confPass){
        if (!pass.equals(confPass)){
            redirectAttributes.addAttribute("msg", "La password non corrisponde");
        }
        else {
            Utente utente = new Utente(username, pass, cognome, nome, citta, mail, LocalDate.parse(data));
            if (utente != null) {
                utenteService.aggiungiUtente(utente);
                session.setAttribute("utente", utente);
                return "redirect:/riservato";
            }
        }
        return "redirect:/";
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public String handleException(Exception ex) {
            // Log dell'errore
            ex.printStackTrace();
            return "error";  // Restituisce una pagina di errore personalizzata
        }
    }
}
