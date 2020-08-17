package caci.certificats.repository;

import caci.certificats.domain.ChargeExport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ChargeExport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargeExportRepository extends JpaRepository<ChargeExport, Long> {
}
