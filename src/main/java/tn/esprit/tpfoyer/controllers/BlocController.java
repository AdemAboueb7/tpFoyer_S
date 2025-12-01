package tn.esprit.tpfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.BlocDTO; // Import du DTO
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.IBlocService;

import java.util.List;

@RestController
@RequestMapping("/blocController")
@AllArgsConstructor
public class BlocController {
    final IBlocService blocService;

    @PostMapping("/addBloc")
    Bloc ajouterBloc(@RequestBody Bloc bloc){
        return blocService.addOrUpdateBloc(bloc);
    }

    @PutMapping("/updateBloc")
    Bloc modifierBloc(@RequestBody Bloc bloc){
        return blocService.addOrUpdateBloc(bloc);
    }

    @DeleteMapping("/deleteBloc/{idBloc}")
    void supprimerBloc(@PathVariable("idBloc") long id){
        blocService.deleteBloc(id);
    }

    @GetMapping("/getAllBlocs")
    List<BlocDTO> getAllBlocs(){ // Type de retour DTO
        return blocService.findAllBloc();
    }

    @GetMapping("/getBlocById/{idBloc}")
    BlocDTO getBlocById(@PathVariable("idBloc") long id){ // Type de retour DTO
        return blocService.findBlocById(id);
    }

    @PostMapping("/addBlocAndAssignFoyer/{idFoyer}")
    Bloc addBlocAndAssignFoyer(@RequestBody Bloc bloc, @PathVariable long idFoyer){
        return blocService.addBlocAndFoyer(bloc, idFoyer);
    }

    @PutMapping("/assignBlocToFoyer/{idBloc}/{idFoyer}")
    Bloc assignBlocToFoyer(@PathVariable("idBloc") long idBloc, @PathVariable("idFoyer") long idFoyer){
        return blocService.assignBlocToFoyer(idBloc, idFoyer);
    }

    @PutMapping("/desassignBlocFromFoyer/{idBloc}")
    Bloc desassignBlocFromFoyer(@PathVariable("idBloc") long idBloc){
        return blocService.desassignBlocFromFoyer(idBloc);
    }

    @GetMapping("/findByFoyerIsNull")
    List<Bloc> findByFoyerIsNull(){
        return blocService.findByFoyerIsNull();
    }

    @GetMapping("/findByCapaciteBlocGreaterThan/{c}")
    List<Bloc> findByCapaciteBlocGreaterThan(@PathVariable("c") long capacite){
        return blocService.findByCapaciteBlocGreaterThan(capacite);
    }

    @GetMapping("/findByNomBlocStartingWith/{p}")
    List<Bloc> findByNomBlocStartingWith(@PathVariable("p") String prefix){
        return blocService.findByNomBlocStartingWith(prefix);
    }

    @GetMapping("/findByNomBlocStartingWithAndCapaciteBlocGreaterThan/{p}/{c}")
    List<Bloc> findByNomBlocStartingWithAndCapaciteBlocGreaterThan(@PathVariable("p") String prefix, @PathVariable("c") long capacite){
        return blocService.findByNomBlocStartingWithAndCapaciteBlocGreaterThan(prefix, capacite);
    }

}