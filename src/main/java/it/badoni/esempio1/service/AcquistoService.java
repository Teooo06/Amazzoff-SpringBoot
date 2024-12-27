package it.badoni.esempio1.service;
import it.badoni.esempio1.model.Prodotto;
import it.badoni.esempio1.model.Acquisto;
import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.repository.AcquistoRepository;
import it.badoni.esempio1.repository.ProdottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
@RequiredArgsConstructor
public class AcquistoService {

    @Autowired
    private final AcquistoRepository acquistoRepository;
    private final ProdottoRepository prodottoRepository;
    @Autowired
    private UtenteService utenteService;

    public String addAcquisto(String username, String idProdotto) {
        // Recupera il prodotto dal database
        Prodotto prodotto = prodottoRepository.findById(idProdotto).orElse(null);
        if (prodotto == null) {
            return "Errore nel recupero del prodotto.";
        }

        // Crea un nuovo acquisto
        Acquisto acquisto = new Acquisto();
        acquisto.setUtente(utenteService.getUtenteByUsername(username));
        acquisto.setProdotto(prodotto);
        // Imposta la data formattata
        LocalDate dataFormattata = LocalDate.now();
        acquisto.setData(dataFormattata);
        acquisto.setCosto(prodotto.getPrezzo());

        // Salva l'acquisto
        acquistoRepository.save(acquisto);

        return prodotto.getDescrizione() + " aggiunto al carrello!";
    }

    public Object getAcquisti(Utente utente) {
        return acquistoRepository.findAllByUtente(utente);
    }

    public String compra(Utente utente) {
        // Metto gli acquisti dell'utente da carrello a acquisti
        for (Acquisto acquisto : acquistoRepository.findAllByUtente(utente)) {
            // Aggiungo acquisto dal carrello ad acquisti
            acquistoRepository.compra(utente.getUsername());
        }
        return "Acquisti effettuati!";
    }
}
