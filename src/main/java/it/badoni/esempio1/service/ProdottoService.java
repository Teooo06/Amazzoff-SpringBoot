package it.badoni.esempio1.service;

import it.badoni.esempio1.model.Prodotto;
import it.badoni.esempio1.repository.ProdottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
