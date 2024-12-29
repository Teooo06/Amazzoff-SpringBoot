package it.badoni.esempio1.service;
import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.repository.StoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoricoService {

    @Autowired
    private final StoricoRepository storicoRepository;
    @Autowired
    private UtenteService utenteService;

    public Object getAcquisti(Utente utente) {
        return storicoRepository.findAllByUtente(utente);
    }
}
