package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entities.Universite;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
}
