package it.badoni.esempio1.controller;
import it.badoni.esempio1.model.Prodotto;
import it.badoni.esempio1.service.ProdottoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

@RequiredArgsConstructor
public class ProdottoController {
    private final ProdottoService prodottoService;

    @PostMapping("/salvaProdotto")
    public String aggiornaProdotto(@RequestParam("id") String id,
                                 @RequestParam("descrizione") String descrizione,
                                 @RequestParam("prezzo") double prezzo,
                                 @RequestParam("categoria") String categoria,
                                 RedirectAttributes redirectAttributes)
    {
        Prodotto prodotto = new Prodotto(id, descrizione, prezzo, categoria);
        prodottoService.updateById(id, prodotto);

        String msg = "Prodotto " + prodotto.getDescrizione() + " salvato";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/prodotti";
    }

    @PostMapping("/eliminaProdotto")
    public String eliminaProdotto(@RequestParam("id") String id,
                                   RedirectAttributes redirectAttributes)
    {
        prodottoService.eliminaProdotto(id);

        String msg = "Prodotto eliminato";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/prodotti";
    }

    @GetMapping("/aggiungiProdotto")
    public String aggiungiProdotto(
                                @RequestParam("id") String id,
                                @RequestParam("descrizione") String descrizione,
                                @RequestParam("prezzo") double prezzo,
                                @RequestParam("categoria") String categoria,
                                RedirectAttributes redirectAttributes){

        Prodotto prodotto = new Prodotto(id, descrizione, prezzo, categoria);
        prodottoService.addProdotto(prodotto);
        String msg = "Prodotto aggiunto";
        redirectAttributes.addAttribute("msg", msg);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/prodotti";
    }
}