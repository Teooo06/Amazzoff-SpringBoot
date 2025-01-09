package it.badoni.esempio1.service;

import it.badoni.esempio1.model.Prodotto;
import it.badoni.esempio1.repository.ProdottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public void addProdotto(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
    }

    public Iterable<Prodotto> getProdotti() {
        return prodottoRepository.findAll();
    }

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public Prodotto updateById(String id, Prodotto prodotto) {
        // Verifica se il prodotto esiste
        if (prodottoRepository.existsById(id)) {
            prodotto.setId(id);  // Assicurati di mantenere l'ID del prodotto esistente
            return prodottoRepository.save(prodotto); // Salva l'entità aggiornata
        } else {
            throw new IllegalArgumentException("Prodotto non trovato con id: " + id);
        }
    }

    public void eliminaProdotto(String id) {
        prodottoRepository.deleteById(id);
    }
}
