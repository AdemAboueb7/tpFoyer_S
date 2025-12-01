package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.dto.FoyerDTO;
import tn.esprit.tpfoyer.repositories.FoyerRepository;
import java.util.stream.Collectors;

import java.util.List;

@AllArgsConstructor
@Service
public class FoyerServiceImpl implements IFoyerService {
    final FoyerRepository foyerRepository;

    @Override
    public Foyer addOrUpdateFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(long id) {
        foyerRepository.deleteById(id);
    }

    public FoyerDTO convertToDTO(Foyer foyer) {
        FoyerDTO dto = new FoyerDTO();
        dto.setNomFoyer(foyer.getNomFoyer());
        dto.setCapaciteFoyer(foyer.getCapaciteFoyer());
        return dto;
    }

    @Override
    public List<FoyerDTO> findAllFoyer() { // ⬅️ Signature mise à jour pour DTO
        return foyerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FoyerDTO findFoyerById(long id) { // ⬅️ Signature mise à jour pour DTO
        Foyer foyer = foyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé"));

        return convertToDTO(foyer);
    }

}