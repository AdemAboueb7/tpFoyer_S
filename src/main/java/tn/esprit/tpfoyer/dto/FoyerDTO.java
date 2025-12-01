package tn.esprit.tpfoyer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoyerDTO {
    // On masque l'ID et les relations (Universite, Blocs)
    private String nomFoyer;
    private long capaciteFoyer;
}