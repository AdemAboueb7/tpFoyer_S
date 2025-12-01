package tn.esprit.tpfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.services.IChambreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chambreController")
@AllArgsConstructor
public class ChambreController {
    final IChambreService chambreService;

    @PostMapping("/addChambre")
    Chambre ajouterChambre(@RequestBody Chambre chambre){
        return chambreService.addOrUpdateChambre(chambre);
    }

    @PutMapping("/updateChambre")
    Chambre modifierChambre(@RequestBody Chambre chambre){
        return chambreService.addOrUpdateChambre(chambre);
    }

    @DeleteMapping("/deleteChambre/{idChambre}")
    void supprimerChambre(@PathVariable("idChambre") long id){
        chambreService.deleteChambre(id);
    }

    @GetMapping("/getAllChambres")
    List<Chambre> getAllChambres(){
        return chambreService.findAllChambre();
    }

    @GetMapping("/getChambreById/{idChambre}")
    Chambre getChambreById(@PathVariable("idChambre") long id){
        return chambreService.findChambreById(id);
    }

    @PutMapping("/assignReservation/{idChambre}/{idReservation}")
    Chambre assignReservationToChambre(@PathVariable("idChambre") long idChambre, @PathVariable("idReservation") String idReservation){
        return chambreService.assignReservationToChambre(idChambre, idReservation);
    }

    @PutMapping("/desassignReservation/{idChambre}/{idReservation}")
    Chambre desassignReservationFromChambre(@PathVariable("idChambre") long idChambre, @PathVariable("idReservation") String idReservation){
        return chambreService.desassignReservationFromChambre(idChambre, idReservation);
    }

    @GetMapping("/findByTypeC/{t}")
    List<Chambre> findByTypeC(@PathVariable("t") String type){
        return chambreService.findByTypeC(type);
    }

    @GetMapping("/findByNumeroChambre/{n}")
    Optional<Chambre> findByNumeroChambre(@PathVariable("n") long numero){
        return chambreService.findByNumeroChambre(numero);
    }
}