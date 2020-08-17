package caci.certificats.web.rest;

import caci.certificats.CertificatcaciApp;
import caci.certificats.domain.Importateur;
import caci.certificats.repository.ImportateurRepository;

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
 * Integration tests for the {@link ImportateurResource} REST controller.
 */
@SpringBootTest(classes = CertificatcaciApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImportateurResourceIT {

    private static final String DEFAULT_RAISON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_FORME_JURIDIQUE = "AAAAAAAAAA";
    private static final String UPDATED_FORME_JURIDIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_SECTER_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_SECTER_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_FIX = "AAAAAAAAAA";
    private static final String UPDATED_TEL_FIX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_WEB = "AAAAAAAAAA";
    private static final String UPDATED_SITE_WEB = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_IMPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_IMPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    @Autowired
    private ImportateurRepository importateurRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImportateurMockMvc;

    private Importateur importateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Importateur createEntity(EntityManager em) {
        Importateur importateur = new Importateur()
            .raisonSocial(DEFAULT_RAISON_SOCIAL)
            .formeJuridique(DEFAULT_FORME_JURIDIQUE)
            .secterActivite(DEFAULT_SECTER_ACTIVITE)
            .mobile(DEFAULT_MOBILE)
            .telFix(DEFAULT_TEL_FIX)
            .email(DEFAULT_EMAIL)
            .siteWeb(DEFAULT_SITE_WEB)
            .adresse(DEFAULT_ADRESSE)
            .typeImportateur(DEFAULT_TYPE_IMPORTATEUR)
            .fax(DEFAULT_FAX);
        return importateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Importateur createUpdatedEntity(EntityManager em) {
        Importateur importateur = new Importateur()
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .secterActivite(UPDATED_SECTER_ACTIVITE)
            .mobile(UPDATED_MOBILE)
            .telFix(UPDATED_TEL_FIX)
            .email(UPDATED_EMAIL)
            .siteWeb(UPDATED_SITE_WEB)
            .adresse(UPDATED_ADRESSE)
            .typeImportateur(UPDATED_TYPE_IMPORTATEUR)
            .fax(UPDATED_FAX);
        return importateur;
    }

    @BeforeEach
    public void initTest() {
        importateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createImportateur() throws Exception {
        int databaseSizeBeforeCreate = importateurRepository.findAll().size();
        // Create the Importateur
        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isCreated());

        // Validate the Importateur in the database
        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeCreate + 1);
        Importateur testImportateur = importateurList.get(importateurList.size() - 1);
        assertThat(testImportateur.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testImportateur.getFormeJuridique()).isEqualTo(DEFAULT_FORME_JURIDIQUE);
        assertThat(testImportateur.getSecterActivite()).isEqualTo(DEFAULT_SECTER_ACTIVITE);
        assertThat(testImportateur.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testImportateur.getTelFix()).isEqualTo(DEFAULT_TEL_FIX);
        assertThat(testImportateur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testImportateur.getSiteWeb()).isEqualTo(DEFAULT_SITE_WEB);
        assertThat(testImportateur.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testImportateur.getTypeImportateur()).isEqualTo(DEFAULT_TYPE_IMPORTATEUR);
        assertThat(testImportateur.getFax()).isEqualTo(DEFAULT_FAX);
    }

    @Test
    @Transactional
    public void createImportateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = importateurRepository.findAll().size();

        // Create the Importateur with an existing ID
        importateur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        // Validate the Importateur in the database
        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRaisonSocialIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setRaisonSocial(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormeJuridiqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setFormeJuridique(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecterActiviteIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setSecterActivite(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setMobile(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelFixIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setTelFix(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setEmail(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = importateurRepository.findAll().size();
        // set the field null
        importateur.setAdresse(null);

        // Create the Importateur, which fails.


        restImportateurMockMvc.perform(post("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllImportateurs() throws Exception {
        // Initialize the database
        importateurRepository.saveAndFlush(importateur);

        // Get all the importateurList
        restImportateurMockMvc.perform(get("/api/importateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(importateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSocial").value(hasItem(DEFAULT_RAISON_SOCIAL)))
            .andExpect(jsonPath("$.[*].formeJuridique").value(hasItem(DEFAULT_FORME_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].secterActivite").value(hasItem(DEFAULT_SECTER_ACTIVITE)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].telFix").value(hasItem(DEFAULT_TEL_FIX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].siteWeb").value(hasItem(DEFAULT_SITE_WEB)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].typeImportateur").value(hasItem(DEFAULT_TYPE_IMPORTATEUR)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)));
    }
    
    @Test
    @Transactional
    public void getImportateur() throws Exception {
        // Initialize the database
        importateurRepository.saveAndFlush(importateur);

        // Get the importateur
        restImportateurMockMvc.perform(get("/api/importateurs/{id}", importateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(importateur.getId().intValue()))
            .andExpect(jsonPath("$.raisonSocial").value(DEFAULT_RAISON_SOCIAL))
            .andExpect(jsonPath("$.formeJuridique").value(DEFAULT_FORME_JURIDIQUE))
            .andExpect(jsonPath("$.secterActivite").value(DEFAULT_SECTER_ACTIVITE))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.telFix").value(DEFAULT_TEL_FIX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.siteWeb").value(DEFAULT_SITE_WEB))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.typeImportateur").value(DEFAULT_TYPE_IMPORTATEUR))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX));
    }
    @Test
    @Transactional
    public void getNonExistingImportateur() throws Exception {
        // Get the importateur
        restImportateurMockMvc.perform(get("/api/importateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImportateur() throws Exception {
        // Initialize the database
        importateurRepository.saveAndFlush(importateur);

        int databaseSizeBeforeUpdate = importateurRepository.findAll().size();

        // Update the importateur
        Importateur updatedImportateur = importateurRepository.findById(importateur.getId()).get();
        // Disconnect from session so that the updates on updatedImportateur are not directly saved in db
        em.detach(updatedImportateur);
        updatedImportateur
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .secterActivite(UPDATED_SECTER_ACTIVITE)
            .mobile(UPDATED_MOBILE)
            .telFix(UPDATED_TEL_FIX)
            .email(UPDATED_EMAIL)
            .siteWeb(UPDATED_SITE_WEB)
            .adresse(UPDATED_ADRESSE)
            .typeImportateur(UPDATED_TYPE_IMPORTATEUR)
            .fax(UPDATED_FAX);

        restImportateurMockMvc.perform(put("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImportateur)))
            .andExpect(status().isOk());

        // Validate the Importateur in the database
        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeUpdate);
        Importateur testImportateur = importateurList.get(importateurList.size() - 1);
        assertThat(testImportateur.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testImportateur.getFormeJuridique()).isEqualTo(UPDATED_FORME_JURIDIQUE);
        assertThat(testImportateur.getSecterActivite()).isEqualTo(UPDATED_SECTER_ACTIVITE);
        assertThat(testImportateur.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testImportateur.getTelFix()).isEqualTo(UPDATED_TEL_FIX);
        assertThat(testImportateur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testImportateur.getSiteWeb()).isEqualTo(UPDATED_SITE_WEB);
        assertThat(testImportateur.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testImportateur.getTypeImportateur()).isEqualTo(UPDATED_TYPE_IMPORTATEUR);
        assertThat(testImportateur.getFax()).isEqualTo(UPDATED_FAX);
    }

    @Test
    @Transactional
    public void updateNonExistingImportateur() throws Exception {
        int databaseSizeBeforeUpdate = importateurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImportateurMockMvc.perform(put("/api/importateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(importateur)))
            .andExpect(status().isBadRequest());

        // Validate the Importateur in the database
        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImportateur() throws Exception {
        // Initialize the database
        importateurRepository.saveAndFlush(importateur);

        int databaseSizeBeforeDelete = importateurRepository.findAll().size();

        // Delete the importateur
        restImportateurMockMvc.perform(delete("/api/importateurs/{id}", importateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Importateur> importateurList = importateurRepository.findAll();
        assertThat(importateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
