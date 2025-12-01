package tn.esprit.tpfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.tpfoyer.dto.BlocDTO;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.mappers.BlocMapper; // ⬅️ Import
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.FoyerRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BlocServiceImpl implements IBlocService{
    final BlocRepository blocRepository;
    final BlocMapper blocMapper;
    final FoyerRepository foyerRepository;

    @Override
    public Bloc addOrUpdateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public List<BlocDTO> findAllBloc() {
        return blocRepository.findAll().stream()
                .map(blocMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BlocDTO findBlocById(long id) {
        Bloc bloc = blocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé"));

        return blocMapper.toDto(bloc); // Conversion automatique
    }

    @Override
    public Bloc addBlocAndFoyer(Bloc bloc, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé"));
        bloc.setFoyer(foyer);
        foyer.getBlocs().add(bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc assignBlocToFoyer(long idBloc, long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé"));
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé"));
        bloc.setFoyer(foyer);
        foyer.getBlocs().add(bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc desassignBlocFromFoyer(long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé"));
        Foyer ancienFoyer = bloc.getFoyer();
        if (ancienFoyer != null) {
            bloc.setFoyer(null);
            ancienFoyer.getBlocs().remove(bloc);
            return blocRepository.save(bloc);
        }
        // Si aucun Foyer n'était affecté, on retourne le Bloc tel quel.
        return bloc;
    }

    @Override
    public List<Bloc> findByFoyerIsNull() {
        return blocRepository.findByFoyerIsNull();
    }

    @Override
    public List<Bloc> findByCapaciteBlocGreaterThan(@PathVariable("c") long capacite) {
        return blocRepository.findByCapaciteBlocGreaterThan(capacite);
    }

    @Override
    public List<Bloc> findByNomBlocStartingWith(@PathVariable("t") String prefix) {
        return blocRepository.findByNomBlocStartingWith(prefix);
    }

    @Override
    public List<Bloc> findByNomBlocStartingWithAndCapaciteBlocGreaterThan(String prefix, long capacite) {
        return blocRepository.findByNomBlocStartingWithAndCapaciteBlocGreaterThan(prefix, capacite);
    }
}