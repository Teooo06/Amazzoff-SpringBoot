package it.badoni.esempio1.controller;
import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Controller

@RequiredArgsConstructor
public class ProfiloController {
    private final UtenteService utenteService;

    @GetMapping("/modificaProfilo")
    public String index(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente != null) {
            model.addAttribute("utente", utente);
            return "modificaProfilo";
        }else{
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
    }


    @PostMapping("/aggiornaProfilo")
    public String aggiornaProfilo(@RequestParam("username") String username,
                                  @RequestParam("nome") String nome,
                                  @RequestParam("cognome") String cognome,
                                  @RequestParam("email") String email,
                                  @RequestParam("dataNascita") String dataNascita,
                                  @RequestParam("citta") String citta,
                                  @RequestParam("password") String password,
                                  @RequestParam("vecchiaPassword") String vecchiaPassword,
                                  RedirectAttributes redirectAttributes,
                                  HttpSession session)
    {
        Utente utente = (Utente) session.getAttribute("utente");
        LocalDate FormattedDataNascita = LocalDate.parse(dataNascita);
        int res = utenteService.aggiornaProfilo(utente, username, nome, cognome, email,
                FormattedDataNascita, citta, password, vecchiaPassword);

        String msg;
        if (res == -1) {
            msg = "Utente non trovato";
        } else if (res == -2) {
            msg = "Password errata";
        } else {
            msg = "Profilo aggiornato";

            // Aggiorna l'utente nella sessione con i nuovi dati
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setDataNascita(FormattedDataNascita);
            utente.setCitta(citta);
            utente.setPassword(password);  // Assicurati che la password venga aggiornata correttamente

            // Salva l'utente aggiornato nella sessione
            session.setAttribute("utente", utente);
        }

        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/modificaProfilo";
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
