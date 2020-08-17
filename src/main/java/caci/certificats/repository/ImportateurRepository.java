package caci.certificats.repository;

import caci.certificats.domain.Importateur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Importateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImportateurRepository extends JpaRepository<Importateur, Long> {
}
