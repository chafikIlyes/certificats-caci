package caci.certificats.web.rest;

import caci.certificats.CertificatcaciApp;
import caci.certificats.domain.Gerant;
import caci.certificats.repository.GerantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GerantResource} REST controller.
 */
@SpringBootTest(classes = CertificatcaciApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GerantResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    @Autowired
    private GerantRepository gerantRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGerantMockMvc;

    private Gerant gerant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gerant createEntity(EntityManager em) {
        Gerant gerant = new Gerant()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .email(DEFAULT_EMAIL)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .mobile(DEFAULT_MOBILE);
        return gerant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gerant createUpdatedEntity(EntityManager em) {
        Gerant gerant = new Gerant()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .mobile(UPDATED_MOBILE);
        return gerant;
    }

    @BeforeEach
    public void initTest() {
        gerant = createEntity(em);
    }

    @Test
    @Transactional
    public void createGerant() throws Exception {
        int databaseSizeBeforeCreate = gerantRepository.findAll().size();
        // Create the Gerant
        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isCreated());

        // Validate the Gerant in the database
        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeCreate + 1);
        Gerant testGerant = gerantList.get(gerantList.size() - 1);
        assertThat(testGerant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testGerant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testGerant.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testGerant.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testGerant.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testGerant.getMobile()).isEqualTo(DEFAULT_MOBILE);
    }

    @Test
    @Transactional
    public void createGerantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gerantRepository.findAll().size();

        // Create the Gerant with an existing ID
        gerant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        // Validate the Gerant in the database
        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = gerantRepository.findAll().size();
        // set the field null
        gerant.setNom(null);

        // Create the Gerant, which fails.


        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = gerantRepository.findAll().size();
        // set the field null
        gerant.setPrenom(null);

        // Create the Gerant, which fails.


        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = gerantRepository.findAll().size();
        // set the field null
        gerant.setEmail(null);

        // Create the Gerant, which fails.


        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelIsRequired() throws Exception {
        int databaseSizeBeforeTest = gerantRepository.findAll().size();
        // set the field null
        gerant.setTel(null);

        // Create the Gerant, which fails.


        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = gerantRepository.findAll().size();
        // set the field null
        gerant.setMobile(null);

        // Create the Gerant, which fails.


        restGerantMockMvc.perform(post("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGerants() throws Exception {
        // Initialize the database
        gerantRepository.saveAndFlush(gerant);

        // Get all the gerantList
        restGerantMockMvc.perform(get("/api/gerants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gerant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)));
    }
    
    @Test
    @Transactional
    public void getGerant() throws Exception {
        // Initialize the database
        gerantRepository.saveAndFlush(gerant);

        // Get the gerant
        restGerantMockMvc.perform(get("/api/gerants/{id}", gerant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gerant.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE));
    }
    @Test
    @Transactional
    public void getNonExistingGerant() throws Exception {
        // Get the gerant
        restGerantMockMvc.perform(get("/api/gerants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGerant() throws Exception {
        // Initialize the database
        gerantRepository.saveAndFlush(gerant);

        int databaseSizeBeforeUpdate = gerantRepository.findAll().size();

        // Update the gerant
        Gerant updatedGerant = gerantRepository.findById(gerant.getId()).get();
        // Disconnect from session so that the updates on updatedGerant are not directly saved in db
        em.detach(updatedGerant);
        updatedGerant
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .email(UPDATED_EMAIL)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .mobile(UPDATED_MOBILE);

        restGerantMockMvc.perform(put("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGerant)))
            .andExpect(status().isOk());

        // Validate the Gerant in the database
        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeUpdate);
        Gerant testGerant = gerantList.get(gerantList.size() - 1);
        assertThat(testGerant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testGerant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testGerant.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testGerant.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testGerant.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testGerant.getMobile()).isEqualTo(UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void updateNonExistingGerant() throws Exception {
        int databaseSizeBeforeUpdate = gerantRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGerantMockMvc.perform(put("/api/gerants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gerant)))
            .andExpect(status().isBadRequest());

        // Validate the Gerant in the database
        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGerant() throws Exception {
        // Initialize the database
        gerantRepository.saveAndFlush(gerant);

        int databaseSizeBeforeDelete = gerantRepository.findAll().size();

        // Delete the gerant
        restGerantMockMvc.perform(delete("/api/gerants/{id}", gerant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gerant> gerantList = gerantRepository.findAll();
        assertThat(gerantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
