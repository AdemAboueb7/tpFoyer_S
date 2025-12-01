package tn.esprit.tpfoyer.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.services.IEtudiantService;
import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;

@RestController
@RequestMapping("/etudiantController")
@AllArgsConstructor
@Tag(name="Etudiant")
public class EtudiantController {
    final IEtudiantService etudiantService;

    @PostMapping("/addEtudiant")
    Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant){
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @Operation(description = "Test de l'API Update")
    @PutMapping("/updateEtudiant")
    Etudiant modifierEtudiant(@RequestBody Etudiant etudiant){
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @DeleteMapping("/deleteEtudiant/{idEtudiant}")
    void supprimerEtudiant(@PathVariable("idEtudiant") long id){
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/getAllEtudiants")
    List<Etudiant> getAllEtudiants(){
        return etudiantService.findAllEtudiant();
    }

    @GetMapping("/getEtudiantById/{idEtudiant}")
    Etudiant getEtudiantById(@PathVariable("idEtudiant") long id){
        return etudiantService.findEtudiantById(id);
    }
}
