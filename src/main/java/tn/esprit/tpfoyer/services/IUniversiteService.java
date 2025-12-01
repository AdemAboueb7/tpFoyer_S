package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addOrUpdateUniversite(Universite universite);
    void deleteUniversite(long id);
    List<Universite> findAllUniversite();
    Universite findUniversiteById(long id);

}
