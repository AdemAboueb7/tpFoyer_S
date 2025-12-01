package tn.esprit.tpfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    @Id
    String idReservation;
    long anneeUniversitaire;
    boolean estValide;

    @ManyToMany
    @JsonIgnore

    Set<Etudiant> etudiants;
}
