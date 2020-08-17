package caci.certificats.repository;

import caci.certificats.domain.CertificatOrigine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CertificatOrigine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificatOrigineRepository extends JpaRepository<CertificatOrigine, Long> {
}
