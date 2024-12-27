package it.badoni.esempio1.service;

import it.badoni.esempio1.model.Utente;
import it.badoni.esempio1.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

    public void aggiungiUtente(Utente utente) {
        utenteRepository.save(utente);
    }
    public Utente getUtenteByUsernameAndPassword(String username, String password) {
        return utenteRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Restiuisce l'elenco completo degli utenti
     * @return
     */
    public Iterable<Utente> getUtenti() {
        return utenteRepository.findAll();

    }

    public Utente getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public int aggiornaProfilo(Utente utente, String username,
                               String nome, String cognome,
                               String email, LocalDate dataNascita, String citta,
                               String password, String vecchiaPassword) {

        // Trovo l'utente loggato con username passato:
        Utente utenteLoggato = utenteRepository.findByUsername(username);
        if (utenteLoggato == null) {
            return -1;
        }

        // Modifico l'utente loggato:
        utenteLoggato.setNome(nome);
        utenteLoggato.setCognome(cognome);
        utenteLoggato.setEmail(email);
        utenteLoggato.setDataNascita(dataNascita);
        utenteLoggato.setCitta(citta);

        // Controllo se c'è una nuova password da aggiornare:
        if (password != null && !password.isEmpty()) {
            // Controllo se la vecchia password è corretta:
            if (!utenteLoggato.getPassword().equals(vecchiaPassword)) {
                return -2; // Se la vecchiaPassword è sbagliata
            }
            utenteLoggato.setPassword(password);
        }
        // Aggiorno l'utente:
        utenteRepository.save(utenteLoggato);
        return 0;
    }
}
