package caci.certificats.web.rest;

import caci.certificats.domain.Gerant;
import caci.certificats.repository.GerantRepository;
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
 * REST controller for managing {@link caci.certificats.domain.Gerant}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GerantResource {

    private final Logger log = LoggerFactory.getLogger(GerantResource.class);

    private static final String ENTITY_NAME = "gerant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GerantRepository gerantRepository;

    public GerantResource(GerantRepository gerantRepository) {
        this.gerantRepository = gerantRepository;
    }

    /**
     * {@code POST  /gerants} : Create a new gerant.
     *
     * @param gerant the gerant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gerant, or with status {@code 400 (Bad Request)} if the gerant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gerants")
    public ResponseEntity<Gerant> createGerant(@Valid @RequestBody Gerant gerant) throws URISyntaxException {
        log.debug("REST request to save Gerant : {}", gerant);
        if (gerant.getId() != null) {
            throw new BadRequestAlertException("A new gerant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gerant result = gerantRepository.save(gerant);
        return ResponseEntity.created(new URI("/api/gerants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gerants} : Updates an existing gerant.
     *
     * @param gerant the gerant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gerant,
     * or with status {@code 400 (Bad Request)} if the gerant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gerant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gerants")
    public ResponseEntity<Gerant> updateGerant(@Valid @RequestBody Gerant gerant) throws URISyntaxException {
        log.debug("REST request to update Gerant : {}", gerant);
        if (gerant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gerant result = gerantRepository.save(gerant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gerant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gerants} : get all the gerants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gerants in body.
     */
    @GetMapping("/gerants")
    public ResponseEntity<List<Gerant>> getAllGerants(Pageable pageable) {
        log.debug("REST request to get a page of Gerants");
        Page<Gerant> page = gerantRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gerants/:id} : get the "id" gerant.
     *
     * @param id the id of the gerant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gerant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gerants/{id}")
    public ResponseEntity<Gerant> getGerant(@PathVariable Long id) {
        log.debug("REST request to get Gerant : {}", id);
        Optional<Gerant> gerant = gerantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gerant);
    }

    /**
     * {@code DELETE  /gerants/:id} : delete the "id" gerant.
     *
     * @param id the id of the gerant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gerants/{id}")
    public ResponseEntity<Void> deleteGerant(@PathVariable Long id) {
        log.debug("REST request to delete Gerant : {}", id);
        gerantRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
