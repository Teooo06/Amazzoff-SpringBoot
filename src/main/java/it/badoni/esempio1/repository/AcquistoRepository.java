package it.badoni.esempio1.repository;

import it.badoni.esempio1.model.Acquisto;
import it.badoni.esempio1.model.Utente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, String> {
    List<Acquisto> findAllByUtente(Utente utente);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = ""+
    "INSERT INTO acquisti (idAcquisto, username, idProdotto, data, costo)"+
    "SELECT idAcquisto, username, idProdotto, data, costo"+
    "FROM carrello"+
    "WHERE username = :utente;")
    /*
    +
    "DELETE FROM carrello"+
    "WHERE username = :utente;"
    )
    */
    void compra(@Param(value = "utente") String username);


    /*
    Come fare query native
    Scrivimi una query che aggiunga nella tabella acquisto tutti gli acquisti dell'utente leggendoli dalla tabella carrello

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM acquisto WHERE id = :id")
    public void deleteById(@Param(value = "id") string id);
    */
}
