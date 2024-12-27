package it.badoni.esempio1.repository;

import it.badoni.esempio1.model.Prodotto;
import it.badoni.esempio1.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, String> {

}
