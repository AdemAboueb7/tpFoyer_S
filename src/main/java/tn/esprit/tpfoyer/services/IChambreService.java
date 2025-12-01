package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface IChambreService {
    Chambre addOrUpdateChambre(Chambre chambre);
    void deleteChambre(long id);
    List<Chambre> findAllChambre();
    Chambre findChambreById(long id);
    //Affectation & Desaffectation
    Chambre assignReservationToChambre(long idChambre, String idReservation);
    Chambre desassignReservationFromChambre(long idChambre, String idReservation);
    //Keywords
    List<Chambre> findByTypeC(String type);
    Optional<Chambre> findByNumeroChambre(long numero);
}