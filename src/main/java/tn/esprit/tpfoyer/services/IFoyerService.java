package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.dto.FoyerDTO; // Importez le DTO

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdateFoyer(Foyer foyer);
    void deleteFoyer(long id);
    List<FoyerDTO> findAllFoyer();
    FoyerDTO findFoyerById(long id);
}