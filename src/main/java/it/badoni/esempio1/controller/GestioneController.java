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


@Controller

@RequiredArgsConstructor
public class GestioneController {
    private final UtenteService utenteService;
    private final ProdottoService prodottoService;
    private final AcquistoService acquistoService;


    @GetMapping("/prodotti")
    public String gestioneProdotti(Model model, HttpSession session) {
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente != null) {
            model.addAttribute("elencoProdotti", prodottoService.getProdotti());
            return "gestioneProdotti";
        }else{

            return "redirect:/";
        }
    }

    @GetMapping("/gestioneUtenti")
    public String gestioneUtenti(Model model, HttpSession session) {
        Utente utente= (Utente) session.getAttribute("utente");
        if (utente != null) {
            model.addAttribute("elencoUtenti", utenteService.getUtenti());
            return "gestioneUtenti";
        }else{
            return "redirect:/";
        }

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

