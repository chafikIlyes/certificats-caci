package caci.certificats.repository;

import caci.certificats.domain.Gerant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Gerant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GerantRepository extends JpaRepository<Gerant, Long> {
}
