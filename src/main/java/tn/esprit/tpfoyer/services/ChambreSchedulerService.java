package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.ChambreRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ChambreSchedulerService {

    final BlocRepository blocRepository;
    final ChambreRepository chambreRepository;

    @Scheduled(fixedRate = 60000) // Runs every minute (60000 ms)
    @Transactional
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {
            System.out.println("Bloc => " + bloc.getNomBloc() + " ayant une capacité " + bloc.getCapaciteBloc());
            
            Set<Chambre> chambres = bloc.getChambres();
            
            if (chambres != null && !chambres.isEmpty()) {
                System.out.println("La liste des chambres pour ce bloc:");
                System.out.println("NuoNomber : " + chambres.size() + " type: SIMPLE");
                chambres.forEach(c -> System.out.println("*********************"));
            } else {
                System.out.println("Pas de chambre disponible dans ce bloc");
                System.out.println("*********************");
            }
        }
    }

    @Scheduled(fixedRate = 300000) // Runs every 5 minutes (300000 ms)
    @Transactional
    public void pourcentageChambreParTypeChambre() {
        List<Chambre> allChambres = chambreRepository.findAll();
        long totalChambres = allChambres.size();

        System.out.println("Nombre total des chambres: " + totalChambres);

        // Count chambers by type
        long simpleCount = allChambres.stream()
                .filter(c -> c.getTypeC() == TypeChambre.SIMPLE)
                .count();
        long doubleCount = allChambres.stream()
                .filter(c -> c.getTypeC() == TypeChambre.DOUBLE)
                .count();
        long tripleCount = allChambres.stream()
                .filter(c -> c.getTypeC() == TypeChambre.TRIPLE)
                .count();

        // Calculate percentages
        double simplePercentage = totalChambres > 0 ? (simpleCount * 100.0) / totalChambres : 0.0;
        double doublePercentage = totalChambres > 0 ? (doubleCount * 100.0) / totalChambres : 0.0;
        double triplePercentage = totalChambres > 0 ? (tripleCount * 100.0) / totalChambres : 0.0;

        System.out.println("Le pourcentage des chambres pour le type SIMPLE est égale à " + simplePercentage);
        System.out.println("Le pourcentage des chambres pour le type DOUBLE est égale à " + doublePercentage);
        System.out.println("Le pourcentage des chambres pour le type TRIPLE est égale à " + triplePercentage);
    }

    @Scheduled(fixedRate = 300000) // Runs every 5 minutes (300000 ms)
    @Transactional
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        List<Chambre> allChambres = chambreRepository.findAll();

        for (Chambre chambre : allChambres) {
            long placesReserved = chambre.getReservation() != null ? chambre.getReservation().size() : 0;
            long placesDisponibles = 0;

            // Determine capacity based on type
            if (chambre.getTypeC() == TypeChambre.SIMPLE) {
                placesDisponibles = 1 - placesReserved;
                if (placesDisponibles <= 0) {
                    System.out.println("La chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est complete");
                } else {
                    System.out.println("Le nombre de place disponible pour la chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est " + placesDisponibles);
                }
            } else if (chambre.getTypeC() == TypeChambre.DOUBLE) {
                placesDisponibles = 2 - placesReserved;
                if (placesDisponibles <= 0) {
                    System.out.println("La chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est complete");
                } else {
                    System.out.println("Le nombre de place disponible pour la chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est " + placesDisponibles);
                }
            } else if (chambre.getTypeC() == TypeChambre.TRIPLE) {
                placesDisponibles = 3 - placesReserved;
                if (placesDisponibles <= 0) {
                    System.out.println("La chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est complete");
                } else {
                    System.out.println("Le nombre de place disponible pour la chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est " + placesDisponibles);
                }
            }
        }
    }
}
