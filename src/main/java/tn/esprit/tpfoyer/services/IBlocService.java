package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.dto.BlocDTO; // Import du DTO

import java.util.List;

public interface IBlocService {

    Bloc addOrUpdateBloc(Bloc bloc);
    void deleteBloc(long id);
    //DTO
    List<BlocDTO> findAllBloc();
    BlocDTO findBlocById(long id);
    //Affectation & Désaffectation
    Bloc addBlocAndFoyer(Bloc bloc, Long idFoyer);
    Bloc assignBlocToFoyer(long idBloc, long idFoyer);
    Bloc desassignBlocFromFoyer(long idBloc);
    //Keywords
    List<Bloc> findByFoyerIsNull();
    List<Bloc> findByCapaciteBlocGreaterThan(long capacite);
    List<Bloc> findByNomBlocStartingWith(String prefix);
    List<Bloc> findByNomBlocStartingWithAndCapaciteBlocGreaterThan(String prefix, long capacite);

}