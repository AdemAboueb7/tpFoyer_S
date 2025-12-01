package tn.esprit.tpfoyer.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universiteController")
@AllArgsConstructor
public class UniversiteController {
    final IUniversiteService universiteService;

    @PostMapping("/addUniversite")
    Universite ajouterUniversite(@RequestBody Universite universite){
        return universiteService.addOrUpdateUniversite(universite);
    }

    @PutMapping("/updateUniversite")
    Universite modifierUniversite(@RequestBody Universite universite){
        return universiteService.addOrUpdateUniversite(universite);
    }

    @DeleteMapping("/deleteUniversite/{idUniversite}")
    void supprimerUniversite(@PathVariable("idUniversite") long id){
        universiteService.deleteUniversite(id);
    }

    @GetMapping("/getAllUniversites")
    List<Universite> getAllUniversites(){
        return universiteService.findAllUniversite();
    }

    @GetMapping("/getUniversiteById/{idUniversite}")
    Universite getUniversiteById(@PathVariable("idUniversite") long id){
        return universiteService.findUniversiteById(id);
    }
}
