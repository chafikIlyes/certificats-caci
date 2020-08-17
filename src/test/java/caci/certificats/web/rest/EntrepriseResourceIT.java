package caci.certificats.web.rest;

import caci.certificats.CertificatcaciApp;
import caci.certificats.domain.Entreprise;
import caci.certificats.repository.EntrepriseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EntrepriseResource} REST controller.
 */
@SpringBootTest(classes = CertificatcaciApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EntrepriseResourceIT {

    private static final String DEFAULT_RAISON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_FORME_JURIDIQUE = "AAAAAAAAAA";
    private static final String UPDATED_FORME_JURIDIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_SECTER_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_SECTER_ACTIVITE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_RC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_RC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RC_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NIF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NIF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NIF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NIF_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NIS = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NIS = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_NIS_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NIS_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_CODE_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ACTIVITE_EXPORT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ACTIVITE_EXPORT = "BBBBBBBBBB";

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

    private static final String DEFAULT_TYPE_EXPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_EXPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_SOLDE_CERTIF = "AAAAAAAAAA";
    private static final String UPDATED_SOLDE_CERTIF = "BBBBBBBBBB";

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntrepriseMockMvc;

    private Entreprise entreprise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entreprise createEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
            .raisonSocial(DEFAULT_RAISON_SOCIAL)
            .formeJuridique(DEFAULT_FORME_JURIDIQUE)
            .secterActivite(DEFAULT_SECTER_ACTIVITE)
            .rc(DEFAULT_RC)
            .rcContentType(DEFAULT_RC_CONTENT_TYPE)
            .nif(DEFAULT_NIF)
            .nifContentType(DEFAULT_NIF_CONTENT_TYPE)
            .nis(DEFAULT_NIS)
            .nisContentType(DEFAULT_NIS_CONTENT_TYPE)
            .codeActivite(DEFAULT_CODE_ACTIVITE)
            .codeActiviteExport(DEFAULT_CODE_ACTIVITE_EXPORT)
            .mobile(DEFAULT_MOBILE)
            .telFix(DEFAULT_TEL_FIX)
            .email(DEFAULT_EMAIL)
            .siteWeb(DEFAULT_SITE_WEB)
            .adresse(DEFAULT_ADRESSE)
            .typeExportateur(DEFAULT_TYPE_EXPORTATEUR)
            .fax(DEFAULT_FAX)
            .soldeCertif(DEFAULT_SOLDE_CERTIF);
        return entreprise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entreprise createUpdatedEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .secterActivite(UPDATED_SECTER_ACTIVITE)
            .rc(UPDATED_RC)
            .rcContentType(UPDATED_RC_CONTENT_TYPE)
            .nif(UPDATED_NIF)
            .nifContentType(UPDATED_NIF_CONTENT_TYPE)
            .nis(UPDATED_NIS)
            .nisContentType(UPDATED_NIS_CONTENT_TYPE)
            .codeActivite(UPDATED_CODE_ACTIVITE)
            .codeActiviteExport(UPDATED_CODE_ACTIVITE_EXPORT)
            .mobile(UPDATED_MOBILE)
            .telFix(UPDATED_TEL_FIX)
            .email(UPDATED_EMAIL)
            .siteWeb(UPDATED_SITE_WEB)
            .adresse(UPDATED_ADRESSE)
            .typeExportateur(UPDATED_TYPE_EXPORTATEUR)
            .fax(UPDATED_FAX)
            .soldeCertif(UPDATED_SOLDE_CERTIF);
        return entreprise;
    }

    @BeforeEach
    public void initTest() {
        entreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntreprise() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();
        // Create the Entreprise
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isCreated());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate + 1);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testEntreprise.getFormeJuridique()).isEqualTo(DEFAULT_FORME_JURIDIQUE);
        assertThat(testEntreprise.getSecterActivite()).isEqualTo(DEFAULT_SECTER_ACTIVITE);
        assertThat(testEntreprise.getRc()).isEqualTo(DEFAULT_RC);
        assertThat(testEntreprise.getRcContentType()).isEqualTo(DEFAULT_RC_CONTENT_TYPE);
        assertThat(testEntreprise.getNif()).isEqualTo(DEFAULT_NIF);
        assertThat(testEntreprise.getNifContentType()).isEqualTo(DEFAULT_NIF_CONTENT_TYPE);
        assertThat(testEntreprise.getNis()).isEqualTo(DEFAULT_NIS);
        assertThat(testEntreprise.getNisContentType()).isEqualTo(DEFAULT_NIS_CONTENT_TYPE);
        assertThat(testEntreprise.getCodeActivite()).isEqualTo(DEFAULT_CODE_ACTIVITE);
        assertThat(testEntreprise.getCodeActiviteExport()).isEqualTo(DEFAULT_CODE_ACTIVITE_EXPORT);
        assertThat(testEntreprise.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testEntreprise.getTelFix()).isEqualTo(DEFAULT_TEL_FIX);
        assertThat(testEntreprise.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEntreprise.getSiteWeb()).isEqualTo(DEFAULT_SITE_WEB);
        assertThat(testEntreprise.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testEntreprise.getTypeExportateur()).isEqualTo(DEFAULT_TYPE_EXPORTATEUR);
        assertThat(testEntreprise.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testEntreprise.getSoldeCertif()).isEqualTo(DEFAULT_SOLDE_CERTIF);
    }

    @Test
    @Transactional
    public void createEntrepriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise with an existing ID
        entreprise.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRaisonSocialIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setRaisonSocial(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormeJuridiqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setFormeJuridique(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecterActiviteIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setSecterActivite(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeActiviteIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setCodeActivite(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setMobile(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelFixIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setTelFix(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setEmail(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = entrepriseRepository.findAll().size();
        // set the field null
        entreprise.setAdresse(null);

        // Create the Entreprise, which fails.


        restEntrepriseMockMvc.perform(post("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEntreprises() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get all the entrepriseList
        restEntrepriseMockMvc.perform(get("/api/entreprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entreprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSocial").value(hasItem(DEFAULT_RAISON_SOCIAL)))
            .andExpect(jsonPath("$.[*].formeJuridique").value(hasItem(DEFAULT_FORME_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].secterActivite").value(hasItem(DEFAULT_SECTER_ACTIVITE)))
            .andExpect(jsonPath("$.[*].rcContentType").value(hasItem(DEFAULT_RC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].rc").value(hasItem(Base64Utils.encodeToString(DEFAULT_RC))))
            .andExpect(jsonPath("$.[*].nifContentType").value(hasItem(DEFAULT_NIF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nif").value(hasItem(Base64Utils.encodeToString(DEFAULT_NIF))))
            .andExpect(jsonPath("$.[*].nisContentType").value(hasItem(DEFAULT_NIS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].nis").value(hasItem(Base64Utils.encodeToString(DEFAULT_NIS))))
            .andExpect(jsonPath("$.[*].codeActivite").value(hasItem(DEFAULT_CODE_ACTIVITE)))
            .andExpect(jsonPath("$.[*].codeActiviteExport").value(hasItem(DEFAULT_CODE_ACTIVITE_EXPORT)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].telFix").value(hasItem(DEFAULT_TEL_FIX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].siteWeb").value(hasItem(DEFAULT_SITE_WEB)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].typeExportateur").value(hasItem(DEFAULT_TYPE_EXPORTATEUR)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].soldeCertif").value(hasItem(DEFAULT_SOLDE_CERTIF)));
    }
    
    @Test
    @Transactional
    public void getEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", entreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entreprise.getId().intValue()))
            .andExpect(jsonPath("$.raisonSocial").value(DEFAULT_RAISON_SOCIAL))
            .andExpect(jsonPath("$.formeJuridique").value(DEFAULT_FORME_JURIDIQUE))
            .andExpect(jsonPath("$.secterActivite").value(DEFAULT_SECTER_ACTIVITE))
            .andExpect(jsonPath("$.rcContentType").value(DEFAULT_RC_CONTENT_TYPE))
            .andExpect(jsonPath("$.rc").value(Base64Utils.encodeToString(DEFAULT_RC)))
            .andExpect(jsonPath("$.nifContentType").value(DEFAULT_NIF_CONTENT_TYPE))
            .andExpect(jsonPath("$.nif").value(Base64Utils.encodeToString(DEFAULT_NIF)))
            .andExpect(jsonPath("$.nisContentType").value(DEFAULT_NIS_CONTENT_TYPE))
            .andExpect(jsonPath("$.nis").value(Base64Utils.encodeToString(DEFAULT_NIS)))
            .andExpect(jsonPath("$.codeActivite").value(DEFAULT_CODE_ACTIVITE))
            .andExpect(jsonPath("$.codeActiviteExport").value(DEFAULT_CODE_ACTIVITE_EXPORT))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.telFix").value(DEFAULT_TEL_FIX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.siteWeb").value(DEFAULT_SITE_WEB))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.typeExportateur").value(DEFAULT_TYPE_EXPORTATEUR))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.soldeCertif").value(DEFAULT_SOLDE_CERTIF));
    }
    @Test
    @Transactional
    public void getNonExistingEntreprise() throws Exception {
        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Update the entreprise
        Entreprise updatedEntreprise = entrepriseRepository.findById(entreprise.getId()).get();
        // Disconnect from session so that the updates on updatedEntreprise are not directly saved in db
        em.detach(updatedEntreprise);
        updatedEntreprise
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .formeJuridique(UPDATED_FORME_JURIDIQUE)
            .secterActivite(UPDATED_SECTER_ACTIVITE)
            .rc(UPDATED_RC)
            .rcContentType(UPDATED_RC_CONTENT_TYPE)
            .nif(UPDATED_NIF)
            .nifContentType(UPDATED_NIF_CONTENT_TYPE)
            .nis(UPDATED_NIS)
            .nisContentType(UPDATED_NIS_CONTENT_TYPE)
            .codeActivite(UPDATED_CODE_ACTIVITE)
            .codeActiviteExport(UPDATED_CODE_ACTIVITE_EXPORT)
            .mobile(UPDATED_MOBILE)
            .telFix(UPDATED_TEL_FIX)
            .email(UPDATED_EMAIL)
            .siteWeb(UPDATED_SITE_WEB)
            .adresse(UPDATED_ADRESSE)
            .typeExportateur(UPDATED_TYPE_EXPORTATEUR)
            .fax(UPDATED_FAX)
            .soldeCertif(UPDATED_SOLDE_CERTIF);

        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEntreprise)))
            .andExpect(status().isOk());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate);
        Entreprise testEntreprise = entrepriseList.get(entrepriseList.size() - 1);
        assertThat(testEntreprise.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testEntreprise.getFormeJuridique()).isEqualTo(UPDATED_FORME_JURIDIQUE);
        assertThat(testEntreprise.getSecterActivite()).isEqualTo(UPDATED_SECTER_ACTIVITE);
        assertThat(testEntreprise.getRc()).isEqualTo(UPDATED_RC);
        assertThat(testEntreprise.getRcContentType()).isEqualTo(UPDATED_RC_CONTENT_TYPE);
        assertThat(testEntreprise.getNif()).isEqualTo(UPDATED_NIF);
        assertThat(testEntreprise.getNifContentType()).isEqualTo(UPDATED_NIF_CONTENT_TYPE);
        assertThat(testEntreprise.getNis()).isEqualTo(UPDATED_NIS);
        assertThat(testEntreprise.getNisContentType()).isEqualTo(UPDATED_NIS_CONTENT_TYPE);
        assertThat(testEntreprise.getCodeActivite()).isEqualTo(UPDATED_CODE_ACTIVITE);
        assertThat(testEntreprise.getCodeActiviteExport()).isEqualTo(UPDATED_CODE_ACTIVITE_EXPORT);
        assertThat(testEntreprise.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testEntreprise.getTelFix()).isEqualTo(UPDATED_TEL_FIX);
        assertThat(testEntreprise.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEntreprise.getSiteWeb()).isEqualTo(UPDATED_SITE_WEB);
        assertThat(testEntreprise.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testEntreprise.getTypeExportateur()).isEqualTo(UPDATED_TYPE_EXPORTATEUR);
        assertThat(testEntreprise.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testEntreprise.getSoldeCertif()).isEqualTo(UPDATED_SOLDE_CERTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingEntreprise() throws Exception {
        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntrepriseMockMvc.perform(put("/api/entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(entreprise)))
            .andExpect(status().isBadRequest());

        // Validate the Entreprise in the database
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        int databaseSizeBeforeDelete = entrepriseRepository.findAll().size();

        // Delete the entreprise
        restEntrepriseMockMvc.perform(delete("/api/entreprises/{id}", entreprise.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entreprise> entrepriseList = entrepriseRepository.findAll();
        assertThat(entrepriseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
