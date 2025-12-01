package tn.esprit.tpfoyer.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.FoyerDTO; // Importez le DTO
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.IFoyerService;

import java.util.List;

@RestController
@RequestMapping("/foyerController")
@AllArgsConstructor
public class FoyerController {
    final IFoyerService foyerService;

    @PostMapping("/addFoyer")
    Foyer ajouterFoyer(@RequestBody Foyer foyer){
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @PutMapping("/updateFoyer")
    Foyer modifierFoyer(@RequestBody Foyer foyer){
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @DeleteMapping("/deleteFoyer/{idFoyer}")
    void supprimerFoyer(@PathVariable("idFoyer") long id){
        foyerService.deleteFoyer(id);
    }

    @GetMapping("/getAllFoyers")
    List<FoyerDTO> getAllFoyers(){ // Type de retour DTO
        return foyerService.findAllFoyer();
    }

    @GetMapping("/getFoyerById/{idFoyer}")
    FoyerDTO getFoyerById(@PathVariable("idFoyer") long id){ // Type de retour DTO
        return foyerService.findFoyerById(id);
    }
}