package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.repositories.UniversiteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UniversiteServiceImpl implements IUniversiteService {
    final UniversiteRepository universiteRepository;

    @Override
    public Universite addOrUpdateUniversite(Universite projet) {
        return universiteRepository.save(projet);
    }

    @Override
    public void deleteUniversite(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public List<Universite> findAllUniversite() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findUniversiteById(long id) {
        return universiteRepository.findById(id).get();
    }
}
