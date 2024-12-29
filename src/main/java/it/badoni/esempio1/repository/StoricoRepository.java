package it.badoni.esempio1.repository;

import it.badoni.esempio1.model.Storico;
import it.badoni.esempio1.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoricoRepository extends JpaRepository<Storico, String> {
    List<Storico> findAllByUtente(Utente utente);

}
