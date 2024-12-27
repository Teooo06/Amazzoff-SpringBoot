package it.badoni.esempio1.repository;

import it.badoni.esempio1.model.Utente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    boolean existsByUsername(String username);
    Utente findByUsernameAndPassword(String username, String password);

    Utente findByUsername(String username);

}
