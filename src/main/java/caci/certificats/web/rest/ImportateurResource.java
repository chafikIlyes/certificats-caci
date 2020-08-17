package caci.certificats.web.rest;

import caci.certificats.domain.Importateur;
import caci.certificats.repository.ImportateurRepository;
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
 * REST controller for managing {@link caci.certificats.domain.Importateur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ImportateurResource {

    private final Logger log = LoggerFactory.getLogger(ImportateurResource.class);

    private static final String ENTITY_NAME = "importateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImportateurRepository importateurRepository;

    public ImportateurResource(ImportateurRepository importateurRepository) {
        this.importateurRepository = importateurRepository;
    }

    /**
     * {@code POST  /importateurs} : Create a new importateur.
     *
     * @param importateur the importateur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new importateur, or with status {@code 400 (Bad Request)} if the importateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/importateurs")
    public ResponseEntity<Importateur> createImportateur(@Valid @RequestBody Importateur importateur) throws URISyntaxException {
        log.debug("REST request to save Importateur : {}", importateur);
        if (importateur.getId() != null) {
            throw new BadRequestAlertException("A new importateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Importateur result = importateurRepository.save(importateur);
        return ResponseEntity.created(new URI("/api/importateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /importateurs} : Updates an existing importateur.
     *
     * @param importateur the importateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated importateur,
     * or with status {@code 400 (Bad Request)} if the importateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the importateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/importateurs")
    public ResponseEntity<Importateur> updateImportateur(@Valid @RequestBody Importateur importateur) throws URISyntaxException {
        log.debug("REST request to update Importateur : {}", importateur);
        if (importateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Importateur result = importateurRepository.save(importateur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, importateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /importateurs} : get all the importateurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of importateurs in body.
     */
    @GetMapping("/importateurs")
    public ResponseEntity<List<Importateur>> getAllImportateurs(Pageable pageable) {
        log.debug("REST request to get a page of Importateurs");
        Page<Importateur> page = importateurRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /importateurs/:id} : get the "id" importateur.
     *
     * @param id the id of the importateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the importateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/importateurs/{id}")
    public ResponseEntity<Importateur> getImportateur(@PathVariable Long id) {
        log.debug("REST request to get Importateur : {}", id);
        Optional<Importateur> importateur = importateurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(importateur);
    }

    /**
     * {@code DELETE  /importateurs/:id} : delete the "id" importateur.
     *
     * @param id the id of the importateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/importateurs/{id}")
    public ResponseEntity<Void> deleteImportateur(@PathVariable Long id) {
        log.debug("REST request to delete Importateur : {}", id);
        importateurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
