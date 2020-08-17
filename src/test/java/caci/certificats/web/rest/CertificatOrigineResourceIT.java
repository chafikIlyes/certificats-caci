package caci.certificats.web.rest;

import caci.certificats.CertificatcaciApp;
import caci.certificats.domain.CertificatOrigine;
import caci.certificats.repository.CertificatOrigineRepository;

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
 * Integration tests for the {@link CertificatOrigineResource} REST controller.
 */
@SpringBootTest(classes = CertificatcaciApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CertificatOrigineResourceIT {

    private static final String DEFAULT_NOM_EXPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_EXPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_EXPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_EXPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PRODUCTEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PRODUCTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_PRODUCTEUR = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_PRODUCTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_IMPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_IMPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_IMPORTATEUR = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_IMPORTATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_ORIGINE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_AUTRE_ORIGINE = false;
    private static final Boolean UPDATED_AUTRE_ORIGINE = true;

    private static final String DEFAULT_PAY_AUTRE_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_PAY_AUTRE_ORIGINE = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL_TRANSPORT = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL_TRANSPORT = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_CERTIFICAT = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_CERTIFICAT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CERTIFICATS = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CERTIFICATS = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SIGNATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SIGNATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_SIGNATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_SIGNATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_SIGNATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SIGNATAIRE = "BBBBBBBBBB";

    @Autowired
    private CertificatOrigineRepository certificatOrigineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificatOrigineMockMvc;

    private CertificatOrigine certificatOrigine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CertificatOrigine createEntity(EntityManager em) {
        CertificatOrigine certificatOrigine = new CertificatOrigine()
            .nomExportateur(DEFAULT_NOM_EXPORTATEUR)
            .adresseExportateur(DEFAULT_ADRESSE_EXPORTATEUR)
            .nomProducteur(DEFAULT_NOM_PRODUCTEUR)
            .adresseProducteur(DEFAULT_ADRESSE_PRODUCTEUR)
            .nomImportateur(DEFAULT_NOM_IMPORTATEUR)
            .adresseImportateur(DEFAULT_ADRESSE_IMPORTATEUR)
            .payOrigine(DEFAULT_PAY_ORIGINE)
            .autreOrigine(DEFAULT_AUTRE_ORIGINE)
            .payAutreOrigine(DEFAULT_PAY_AUTRE_ORIGINE)
            .detailTransport(DEFAULT_DETAIL_TRANSPORT)
            .observation(DEFAULT_OBSERVATION)
            .etatCertificat(DEFAULT_ETAT_CERTIFICAT)
            .typeCertificats(DEFAULT_TYPE_CERTIFICATS)
            .nomSignataire(DEFAULT_NOM_SIGNATAIRE)
            .prenomSignataire(DEFAULT_PRENOM_SIGNATAIRE)
            .emailSignataire(DEFAULT_EMAIL_SIGNATAIRE);
        return certificatOrigine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CertificatOrigine createUpdatedEntity(EntityManager em) {
        CertificatOrigine certificatOrigine = new CertificatOrigine()
            .nomExportateur(UPDATED_NOM_EXPORTATEUR)
            .adresseExportateur(UPDATED_ADRESSE_EXPORTATEUR)
            .nomProducteur(UPDATED_NOM_PRODUCTEUR)
            .adresseProducteur(UPDATED_ADRESSE_PRODUCTEUR)
            .nomImportateur(UPDATED_NOM_IMPORTATEUR)
            .adresseImportateur(UPDATED_ADRESSE_IMPORTATEUR)
            .payOrigine(UPDATED_PAY_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .payAutreOrigine(UPDATED_PAY_AUTRE_ORIGINE)
            .detailTransport(UPDATED_DETAIL_TRANSPORT)
            .observation(UPDATED_OBSERVATION)
            .etatCertificat(UPDATED_ETAT_CERTIFICAT)
            .typeCertificats(UPDATED_TYPE_CERTIFICATS)
            .nomSignataire(UPDATED_NOM_SIGNATAIRE)
            .prenomSignataire(UPDATED_PRENOM_SIGNATAIRE)
            .emailSignataire(UPDATED_EMAIL_SIGNATAIRE);
        return certificatOrigine;
    }

    @BeforeEach
    public void initTest() {
        certificatOrigine = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificatOrigine() throws Exception {
        int databaseSizeBeforeCreate = certificatOrigineRepository.findAll().size();
        // Create the CertificatOrigine
        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isCreated());

        // Validate the CertificatOrigine in the database
        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeCreate + 1);
        CertificatOrigine testCertificatOrigine = certificatOrigineList.get(certificatOrigineList.size() - 1);
        assertThat(testCertificatOrigine.getNomExportateur()).isEqualTo(DEFAULT_NOM_EXPORTATEUR);
        assertThat(testCertificatOrigine.getAdresseExportateur()).isEqualTo(DEFAULT_ADRESSE_EXPORTATEUR);
        assertThat(testCertificatOrigine.getNomProducteur()).isEqualTo(DEFAULT_NOM_PRODUCTEUR);
        assertThat(testCertificatOrigine.getAdresseProducteur()).isEqualTo(DEFAULT_ADRESSE_PRODUCTEUR);
        assertThat(testCertificatOrigine.getNomImportateur()).isEqualTo(DEFAULT_NOM_IMPORTATEUR);
        assertThat(testCertificatOrigine.getAdresseImportateur()).isEqualTo(DEFAULT_ADRESSE_IMPORTATEUR);
        assertThat(testCertificatOrigine.getPayOrigine()).isEqualTo(DEFAULT_PAY_ORIGINE);
        assertThat(testCertificatOrigine.isAutreOrigine()).isEqualTo(DEFAULT_AUTRE_ORIGINE);
        assertThat(testCertificatOrigine.getPayAutreOrigine()).isEqualTo(DEFAULT_PAY_AUTRE_ORIGINE);
        assertThat(testCertificatOrigine.getDetailTransport()).isEqualTo(DEFAULT_DETAIL_TRANSPORT);
        assertThat(testCertificatOrigine.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
        assertThat(testCertificatOrigine.getEtatCertificat()).isEqualTo(DEFAULT_ETAT_CERTIFICAT);
        assertThat(testCertificatOrigine.getTypeCertificats()).isEqualTo(DEFAULT_TYPE_CERTIFICATS);
        assertThat(testCertificatOrigine.getNomSignataire()).isEqualTo(DEFAULT_NOM_SIGNATAIRE);
        assertThat(testCertificatOrigine.getPrenomSignataire()).isEqualTo(DEFAULT_PRENOM_SIGNATAIRE);
        assertThat(testCertificatOrigine.getEmailSignataire()).isEqualTo(DEFAULT_EMAIL_SIGNATAIRE);
    }

    @Test
    @Transactional
    public void createCertificatOrigineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificatOrigineRepository.findAll().size();

        // Create the CertificatOrigine with an existing ID
        certificatOrigine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        // Validate the CertificatOrigine in the database
        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomExportateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setNomExportateur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseExportateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setAdresseExportateur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomProducteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setNomProducteur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseProducteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setAdresseProducteur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomImportateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setNomImportateur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseImportateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setAdresseImportateur(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPayOrigineIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setPayOrigine(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDetailTransportIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificatOrigineRepository.findAll().size();
        // set the field null
        certificatOrigine.setDetailTransport(null);

        // Create the CertificatOrigine, which fails.


        restCertificatOrigineMockMvc.perform(post("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCertificatOrigines() throws Exception {
        // Initialize the database
        certificatOrigineRepository.saveAndFlush(certificatOrigine);

        // Get all the certificatOrigineList
        restCertificatOrigineMockMvc.perform(get("/api/certificat-origines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificatOrigine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomExportateur").value(hasItem(DEFAULT_NOM_EXPORTATEUR)))
            .andExpect(jsonPath("$.[*].adresseExportateur").value(hasItem(DEFAULT_ADRESSE_EXPORTATEUR)))
            .andExpect(jsonPath("$.[*].nomProducteur").value(hasItem(DEFAULT_NOM_PRODUCTEUR)))
            .andExpect(jsonPath("$.[*].adresseProducteur").value(hasItem(DEFAULT_ADRESSE_PRODUCTEUR)))
            .andExpect(jsonPath("$.[*].nomImportateur").value(hasItem(DEFAULT_NOM_IMPORTATEUR)))
            .andExpect(jsonPath("$.[*].adresseImportateur").value(hasItem(DEFAULT_ADRESSE_IMPORTATEUR)))
            .andExpect(jsonPath("$.[*].payOrigine").value(hasItem(DEFAULT_PAY_ORIGINE)))
            .andExpect(jsonPath("$.[*].autreOrigine").value(hasItem(DEFAULT_AUTRE_ORIGINE.booleanValue())))
            .andExpect(jsonPath("$.[*].payAutreOrigine").value(hasItem(DEFAULT_PAY_AUTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].detailTransport").value(hasItem(DEFAULT_DETAIL_TRANSPORT)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)))
            .andExpect(jsonPath("$.[*].etatCertificat").value(hasItem(DEFAULT_ETAT_CERTIFICAT)))
            .andExpect(jsonPath("$.[*].typeCertificats").value(hasItem(DEFAULT_TYPE_CERTIFICATS)))
            .andExpect(jsonPath("$.[*].nomSignataire").value(hasItem(DEFAULT_NOM_SIGNATAIRE)))
            .andExpect(jsonPath("$.[*].prenomSignataire").value(hasItem(DEFAULT_PRENOM_SIGNATAIRE)))
            .andExpect(jsonPath("$.[*].emailSignataire").value(hasItem(DEFAULT_EMAIL_SIGNATAIRE)));
    }
    
    @Test
    @Transactional
    public void getCertificatOrigine() throws Exception {
        // Initialize the database
        certificatOrigineRepository.saveAndFlush(certificatOrigine);

        // Get the certificatOrigine
        restCertificatOrigineMockMvc.perform(get("/api/certificat-origines/{id}", certificatOrigine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificatOrigine.getId().intValue()))
            .andExpect(jsonPath("$.nomExportateur").value(DEFAULT_NOM_EXPORTATEUR))
            .andExpect(jsonPath("$.adresseExportateur").value(DEFAULT_ADRESSE_EXPORTATEUR))
            .andExpect(jsonPath("$.nomProducteur").value(DEFAULT_NOM_PRODUCTEUR))
            .andExpect(jsonPath("$.adresseProducteur").value(DEFAULT_ADRESSE_PRODUCTEUR))
            .andExpect(jsonPath("$.nomImportateur").value(DEFAULT_NOM_IMPORTATEUR))
            .andExpect(jsonPath("$.adresseImportateur").value(DEFAULT_ADRESSE_IMPORTATEUR))
            .andExpect(jsonPath("$.payOrigine").value(DEFAULT_PAY_ORIGINE))
            .andExpect(jsonPath("$.autreOrigine").value(DEFAULT_AUTRE_ORIGINE.booleanValue()))
            .andExpect(jsonPath("$.payAutreOrigine").value(DEFAULT_PAY_AUTRE_ORIGINE))
            .andExpect(jsonPath("$.detailTransport").value(DEFAULT_DETAIL_TRANSPORT))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION))
            .andExpect(jsonPath("$.etatCertificat").value(DEFAULT_ETAT_CERTIFICAT))
            .andExpect(jsonPath("$.typeCertificats").value(DEFAULT_TYPE_CERTIFICATS))
            .andExpect(jsonPath("$.nomSignataire").value(DEFAULT_NOM_SIGNATAIRE))
            .andExpect(jsonPath("$.prenomSignataire").value(DEFAULT_PRENOM_SIGNATAIRE))
            .andExpect(jsonPath("$.emailSignataire").value(DEFAULT_EMAIL_SIGNATAIRE));
    }
    @Test
    @Transactional
    public void getNonExistingCertificatOrigine() throws Exception {
        // Get the certificatOrigine
        restCertificatOrigineMockMvc.perform(get("/api/certificat-origines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificatOrigine() throws Exception {
        // Initialize the database
        certificatOrigineRepository.saveAndFlush(certificatOrigine);

        int databaseSizeBeforeUpdate = certificatOrigineRepository.findAll().size();

        // Update the certificatOrigine
        CertificatOrigine updatedCertificatOrigine = certificatOrigineRepository.findById(certificatOrigine.getId()).get();
        // Disconnect from session so that the updates on updatedCertificatOrigine are not directly saved in db
        em.detach(updatedCertificatOrigine);
        updatedCertificatOrigine
            .nomExportateur(UPDATED_NOM_EXPORTATEUR)
            .adresseExportateur(UPDATED_ADRESSE_EXPORTATEUR)
            .nomProducteur(UPDATED_NOM_PRODUCTEUR)
            .adresseProducteur(UPDATED_ADRESSE_PRODUCTEUR)
            .nomImportateur(UPDATED_NOM_IMPORTATEUR)
            .adresseImportateur(UPDATED_ADRESSE_IMPORTATEUR)
            .payOrigine(UPDATED_PAY_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .payAutreOrigine(UPDATED_PAY_AUTRE_ORIGINE)
            .detailTransport(UPDATED_DETAIL_TRANSPORT)
            .observation(UPDATED_OBSERVATION)
            .etatCertificat(UPDATED_ETAT_CERTIFICAT)
            .typeCertificats(UPDATED_TYPE_CERTIFICATS)
            .nomSignataire(UPDATED_NOM_SIGNATAIRE)
            .prenomSignataire(UPDATED_PRENOM_SIGNATAIRE)
            .emailSignataire(UPDATED_EMAIL_SIGNATAIRE);

        restCertificatOrigineMockMvc.perform(put("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCertificatOrigine)))
            .andExpect(status().isOk());

        // Validate the CertificatOrigine in the database
        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeUpdate);
        CertificatOrigine testCertificatOrigine = certificatOrigineList.get(certificatOrigineList.size() - 1);
        assertThat(testCertificatOrigine.getNomExportateur()).isEqualTo(UPDATED_NOM_EXPORTATEUR);
        assertThat(testCertificatOrigine.getAdresseExportateur()).isEqualTo(UPDATED_ADRESSE_EXPORTATEUR);
        assertThat(testCertificatOrigine.getNomProducteur()).isEqualTo(UPDATED_NOM_PRODUCTEUR);
        assertThat(testCertificatOrigine.getAdresseProducteur()).isEqualTo(UPDATED_ADRESSE_PRODUCTEUR);
        assertThat(testCertificatOrigine.getNomImportateur()).isEqualTo(UPDATED_NOM_IMPORTATEUR);
        assertThat(testCertificatOrigine.getAdresseImportateur()).isEqualTo(UPDATED_ADRESSE_IMPORTATEUR);
        assertThat(testCertificatOrigine.getPayOrigine()).isEqualTo(UPDATED_PAY_ORIGINE);
        assertThat(testCertificatOrigine.isAutreOrigine()).isEqualTo(UPDATED_AUTRE_ORIGINE);
        assertThat(testCertificatOrigine.getPayAutreOrigine()).isEqualTo(UPDATED_PAY_AUTRE_ORIGINE);
        assertThat(testCertificatOrigine.getDetailTransport()).isEqualTo(UPDATED_DETAIL_TRANSPORT);
        assertThat(testCertificatOrigine.getObservation()).isEqualTo(UPDATED_OBSERVATION);
        assertThat(testCertificatOrigine.getEtatCertificat()).isEqualTo(UPDATED_ETAT_CERTIFICAT);
        assertThat(testCertificatOrigine.getTypeCertificats()).isEqualTo(UPDATED_TYPE_CERTIFICATS);
        assertThat(testCertificatOrigine.getNomSignataire()).isEqualTo(UPDATED_NOM_SIGNATAIRE);
        assertThat(testCertificatOrigine.getPrenomSignataire()).isEqualTo(UPDATED_PRENOM_SIGNATAIRE);
        assertThat(testCertificatOrigine.getEmailSignataire()).isEqualTo(UPDATED_EMAIL_SIGNATAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificatOrigine() throws Exception {
        int databaseSizeBeforeUpdate = certificatOrigineRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificatOrigineMockMvc.perform(put("/api/certificat-origines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(certificatOrigine)))
            .andExpect(status().isBadRequest());

        // Validate the CertificatOrigine in the database
        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificatOrigine() throws Exception {
        // Initialize the database
        certificatOrigineRepository.saveAndFlush(certificatOrigine);

        int databaseSizeBeforeDelete = certificatOrigineRepository.findAll().size();

        // Delete the certificatOrigine
        restCertificatOrigineMockMvc.perform(delete("/api/certificat-origines/{id}", certificatOrigine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CertificatOrigine> certificatOrigineList = certificatOrigineRepository.findAll();
        assertThat(certificatOrigineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
