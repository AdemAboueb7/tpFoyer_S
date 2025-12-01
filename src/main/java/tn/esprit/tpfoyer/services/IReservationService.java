package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addOrUpdateReservation(Reservation reservation);
    void deleteReservation(String id);
    List<Reservation> findAllReservation();
    Reservation findReservationById(String id);
}
