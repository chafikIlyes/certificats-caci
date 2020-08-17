package caci.certificats.web.rest;

import caci.certificats.domain.CertificatOrigine;
import caci.certificats.repository.CertificatOrigineRepository;
import caci.certificats.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link caci.certificats.domain.CertificatOrigine}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CertificatOrigineResource {

    private final Logger log = LoggerFactory.getLogger(CertificatOrigineResource.class);

    private static final String ENTITY_NAME = "certificatOrigine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificatOrigineRepository certificatOrigineRepository;

    public CertificatOrigineResource(CertificatOrigineRepository certificatOrigineRepository) {
        this.certificatOrigineRepository = certificatOrigineRepository;
    }

    /**
     * {@code POST  /certificat-origines} : Create a new certificatOrigine.
     *
     * @param certificatOrigine the certificatOrigine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificatOrigine, or with status {@code 400 (Bad Request)} if the certificatOrigine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificat-origines")
    public ResponseEntity<CertificatOrigine> createCertificatOrigine(@Valid @RequestBody CertificatOrigine certificatOrigine) throws URISyntaxException {
        log.debug("REST request to save CertificatOrigine : {}", certificatOrigine);
        if (certificatOrigine.getId() != null) {
            throw new BadRequestAlertException("A new certificatOrigine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificatOrigine result = certificatOrigineRepository.save(certificatOrigine);
        return ResponseEntity.created(new URI("/api/certificat-origines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificat-origines} : Updates an existing certificatOrigine.
     *
     * @param certificatOrigine the certificatOrigine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificatOrigine,
     * or with status {@code 400 (Bad Request)} if the certificatOrigine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificatOrigine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificat-origines")
    public ResponseEntity<CertificatOrigine> updateCertificatOrigine(@Valid @RequestBody CertificatOrigine certificatOrigine) throws URISyntaxException {
        log.debug("REST request to update CertificatOrigine : {}", certificatOrigine);
        if (certificatOrigine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificatOrigine result = certificatOrigineRepository.save(certificatOrigine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificatOrigine.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificat-origines} : get all the certificatOrigines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificatOrigines in body.
     */
    @GetMapping("/certificat-origines")
    public List<CertificatOrigine> getAllCertificatOrigines() {
        log.debug("REST request to get all CertificatOrigines");
        return certificatOrigineRepository.findAll();
    }

    /**
     * {@code GET  /certificat-origines/:id} : get the "id" certificatOrigine.
     *
     * @param id the id of the certificatOrigine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificatOrigine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificat-origines/{id}")
    public ResponseEntity<CertificatOrigine> getCertificatOrigine(@PathVariable Long id) {
        log.debug("REST request to get CertificatOrigine : {}", id);
        Optional<CertificatOrigine> certificatOrigine = certificatOrigineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(certificatOrigine);
    }

    /**
     * {@code DELETE  /certificat-origines/:id} : delete the "id" certificatOrigine.
     *
     * @param id the id of the certificatOrigine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificat-origines/{id}")
    public ResponseEntity<Void> deleteCertificatOrigine(@PathVariable Long id) {
        log.debug("REST request to delete CertificatOrigine : {}", id);
        certificatOrigineRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
