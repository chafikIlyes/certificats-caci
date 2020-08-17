package caci.certificats.web.rest;

import caci.certificats.domain.ChargeExport;
import caci.certificats.repository.ChargeExportRepository;
import caci.certificats.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link caci.certificats.domain.ChargeExport}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChargeExportResource {

    private final Logger log = LoggerFactory.getLogger(ChargeExportResource.class);

    private static final String ENTITY_NAME = "chargeExport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChargeExportRepository chargeExportRepository;

    public ChargeExportResource(ChargeExportRepository chargeExportRepository) {
        this.chargeExportRepository = chargeExportRepository;
    }

    /**
     * {@code POST  /charge-exports} : Create a new chargeExport.
     *
     * @param chargeExport the chargeExport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chargeExport, or with status {@code 400 (Bad Request)} if the chargeExport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/charge-exports")
    public ResponseEntity<ChargeExport> createChargeExport(@Valid @RequestBody ChargeExport chargeExport) throws URISyntaxException {
        log.debug("REST request to save ChargeExport : {}", chargeExport);
        if (chargeExport.getId() != null) {
            throw new BadRequestAlertException("A new chargeExport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChargeExport result = chargeExportRepository.save(chargeExport);
        return ResponseEntity.created(new URI("/api/charge-exports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /charge-exports} : Updates an existing chargeExport.
     *
     * @param chargeExport the chargeExport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeExport,
     * or with status {@code 400 (Bad Request)} if the chargeExport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chargeExport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/charge-exports")
    public ResponseEntity<ChargeExport> updateChargeExport(@Valid @RequestBody ChargeExport chargeExport) throws URISyntaxException {
        log.debug("REST request to update ChargeExport : {}", chargeExport);
        if (chargeExport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChargeExport result = chargeExportRepository.save(chargeExport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chargeExport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /charge-exports} : get all the chargeExports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chargeExports in body.
     */
    @GetMapping("/charge-exports")
    public ResponseEntity<List<ChargeExport>> getAllChargeExports(Pageable pageable) {
        log.debug("REST request to get a page of ChargeExports");
        Page<ChargeExport> page = chargeExportRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /charge-exports/:id} : get the "id" chargeExport.
     *
     * @param id the id of the chargeExport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chargeExport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/charge-exports/{id}")
    public ResponseEntity<ChargeExport> getChargeExport(@PathVariable Long id) {
        log.debug("REST request to get ChargeExport : {}", id);
        Optional<ChargeExport> chargeExport = chargeExportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chargeExport);
    }

    /**
     * {@code DELETE  /charge-exports/:id} : delete the "id" chargeExport.
     *
     * @param id the id of the chargeExport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/charge-exports/{id}")
    public ResponseEntity<Void> deleteChargeExport(@PathVariable Long id) {
        log.debug("REST request to delete ChargeExport : {}", id);
        chargeExportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
