package it.badoni.esempio1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table (name="carrello")
public class Acquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAcquisto")
    private int idAcquisto;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Utente utente;


    @ManyToOne
    @JoinColumn(name = "idProdotto", referencedColumnName = "id")
    private Prodotto prodotto;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "costo")
    private Double costo;
}
