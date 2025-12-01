package tn.esprit.tpfoyer.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservationController")
@AllArgsConstructor
public class ReservationController {
    final IReservationService reservationService;

    @PostMapping("/addReservation")
    Reservation ajouterReservation(@RequestBody Reservation reservation){
        return reservationService.addOrUpdateReservation(reservation);
    }

    @PutMapping("/updateReservation")
    Reservation modifierReservation(@RequestBody Reservation reservation){
        return reservationService.addOrUpdateReservation(reservation);
    }

    @DeleteMapping("/deleteReservation/{idReservation}")
    void supprimerReservation(@PathVariable("idReservation") String id){
        reservationService.deleteReservation(id);
    }

    @GetMapping("/getAllReservations")
    List<Reservation> getAllReservations(){
        return reservationService.findAllReservation();
    }

    @GetMapping("/getReservationById/{idReservation}")
    Reservation getReservationById(@PathVariable("idReservation") String id){
        return reservationService.findReservationById(id);
    }
}
