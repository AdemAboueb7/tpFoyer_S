package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChambreServiceImpl implements IChambreService{
    final ChambreRepository chambreRepository;
    final ReservationRepository reservationRepository;

    @Override
    public Chambre addOrUpdateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> findAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre findChambreById(long id) {
        return chambreRepository.findById(id).get();
    }

    @Override
    public Chambre assignReservationToChambre(long idChambre, String idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        chambre.getReservation().add(reservation);
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre desassignReservationFromChambre(long idChambre, String idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        chambre.getReservation().remove(reservation);
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> findByTypeC(String type) {
        return chambreRepository.findByTypeC(type);
    }

    @Override
    public Optional<Chambre> findByNumeroChambre(long numero) {
        return chambreRepository.findByNumeroChambre(numero);
    }
}