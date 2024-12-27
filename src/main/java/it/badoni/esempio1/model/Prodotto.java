package it.badoni.esempio1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="prodotto")
public class Prodotto {
    @Id
    private String id;
    private String descrizione;
    private Double prezzo;
    private String categoria;

    public String getPrezzoStr(){
        return String.format("%.2f", prezzo);
    }
}
