package tn.esprit.tpfoyer.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Bloc;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    List<Bloc> findByFoyerIsNull();
    List<Bloc> findByCapaciteBlocGreaterThan(long capacite);
    List<Bloc> findByNomBlocStartingWith(String prefix);
    List<Bloc> findByNomBlocStartingWithAndCapaciteBlocGreaterThan(String prefix, long capacite);

}