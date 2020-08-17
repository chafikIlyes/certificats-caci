package caci.certificats.web.rest;

import caci.certificats.CertificatcaciApp;
import caci.certificats.domain.ChargeExport;
import caci.certificats.repository.ChargeExportRepository;

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
 * Integration tests for the {@link ChargeExportResource} REST controller.
 */
@SpringBootTest(classes = CertificatcaciApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChargeExportResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_FONCTION = "AAAAAAAAAA";
    private static final String UPDATED_FONCTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SIGNATURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SIGNATURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SIGNATURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SIGNATURE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_CACHET = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CACHET = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CACHET_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CACHET_CONTENT_TYPE = "image/png";

    @Autowired
    private ChargeExportRepository chargeExportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChargeExportMockMvc;

    private ChargeExport chargeExport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeExport createEntity(EntityManager em) {
        ChargeExport chargeExport = new ChargeExport()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .fonction(DEFAULT_FONCTION)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .mobile(DEFAULT_MOBILE)
            .signature(DEFAULT_SIGNATURE)
            .signatureContentType(DEFAULT_SIGNATURE_CONTENT_TYPE)
            .cachet(DEFAULT_CACHET)
            .cachetContentType(DEFAULT_CACHET_CONTENT_TYPE);
        return chargeExport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeExport createUpdatedEntity(EntityManager em) {
        ChargeExport chargeExport = new ChargeExport()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .fonction(UPDATED_FONCTION)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .mobile(UPDATED_MOBILE)
            .signature(UPDATED_SIGNATURE)
            .signatureContentType(UPDATED_SIGNATURE_CONTENT_TYPE)
            .cachet(UPDATED_CACHET)
            .cachetContentType(UPDATED_CACHET_CONTENT_TYPE);
        return chargeExport;
    }

    @BeforeEach
    public void initTest() {
        chargeExport = createEntity(em);
    }

    @Test
    @Transactional
    public void createChargeExport() throws Exception {
        int databaseSizeBeforeCreate = chargeExportRepository.findAll().size();
        // Create the ChargeExport
        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isCreated());

        // Validate the ChargeExport in the database
        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeCreate + 1);
        ChargeExport testChargeExport = chargeExportList.get(chargeExportList.size() - 1);
        assertThat(testChargeExport.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testChargeExport.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testChargeExport.getFonction()).isEqualTo(DEFAULT_FONCTION);
        assertThat(testChargeExport.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testChargeExport.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testChargeExport.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testChargeExport.getSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testChargeExport.getSignatureContentType()).isEqualTo(DEFAULT_SIGNATURE_CONTENT_TYPE);
        assertThat(testChargeExport.getCachet()).isEqualTo(DEFAULT_CACHET);
        assertThat(testChargeExport.getCachetContentType()).isEqualTo(DEFAULT_CACHET_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createChargeExportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chargeExportRepository.findAll().size();

        // Create the ChargeExport with an existing ID
        chargeExport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        // Validate the ChargeExport in the database
        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setNom(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setPrenom(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFonctionIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setFonction(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setTel(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setFax(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeExportRepository.findAll().size();
        // set the field null
        chargeExport.setMobile(null);

        // Create the ChargeExport, which fails.


        restChargeExportMockMvc.perform(post("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChargeExports() throws Exception {
        // Initialize the database
        chargeExportRepository.saveAndFlush(chargeExport);

        // Get all the chargeExportList
        restChargeExportMockMvc.perform(get("/api/charge-exports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chargeExport.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].fonction").value(hasItem(DEFAULT_FONCTION)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].signatureContentType").value(hasItem(DEFAULT_SIGNATURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(Base64Utils.encodeToString(DEFAULT_SIGNATURE))))
            .andExpect(jsonPath("$.[*].cachetContentType").value(hasItem(DEFAULT_CACHET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].cachet").value(hasItem(Base64Utils.encodeToString(DEFAULT_CACHET))));
    }
    
    @Test
    @Transactional
    public void getChargeExport() throws Exception {
        // Initialize the database
        chargeExportRepository.saveAndFlush(chargeExport);

        // Get the chargeExport
        restChargeExportMockMvc.perform(get("/api/charge-exports/{id}", chargeExport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chargeExport.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.fonction").value(DEFAULT_FONCTION))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.signatureContentType").value(DEFAULT_SIGNATURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.signature").value(Base64Utils.encodeToString(DEFAULT_SIGNATURE)))
            .andExpect(jsonPath("$.cachetContentType").value(DEFAULT_CACHET_CONTENT_TYPE))
            .andExpect(jsonPath("$.cachet").value(Base64Utils.encodeToString(DEFAULT_CACHET)));
    }
    @Test
    @Transactional
    public void getNonExistingChargeExport() throws Exception {
        // Get the chargeExport
        restChargeExportMockMvc.perform(get("/api/charge-exports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChargeExport() throws Exception {
        // Initialize the database
        chargeExportRepository.saveAndFlush(chargeExport);

        int databaseSizeBeforeUpdate = chargeExportRepository.findAll().size();

        // Update the chargeExport
        ChargeExport updatedChargeExport = chargeExportRepository.findById(chargeExport.getId()).get();
        // Disconnect from session so that the updates on updatedChargeExport are not directly saved in db
        em.detach(updatedChargeExport);
        updatedChargeExport
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .fonction(UPDATED_FONCTION)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .mobile(UPDATED_MOBILE)
            .signature(UPDATED_SIGNATURE)
            .signatureContentType(UPDATED_SIGNATURE_CONTENT_TYPE)
            .cachet(UPDATED_CACHET)
            .cachetContentType(UPDATED_CACHET_CONTENT_TYPE);

        restChargeExportMockMvc.perform(put("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedChargeExport)))
            .andExpect(status().isOk());

        // Validate the ChargeExport in the database
        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeUpdate);
        ChargeExport testChargeExport = chargeExportList.get(chargeExportList.size() - 1);
        assertThat(testChargeExport.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testChargeExport.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testChargeExport.getFonction()).isEqualTo(UPDATED_FONCTION);
        assertThat(testChargeExport.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testChargeExport.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testChargeExport.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testChargeExport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testChargeExport.getSignatureContentType()).isEqualTo(UPDATED_SIGNATURE_CONTENT_TYPE);
        assertThat(testChargeExport.getCachet()).isEqualTo(UPDATED_CACHET);
        assertThat(testChargeExport.getCachetContentType()).isEqualTo(UPDATED_CACHET_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingChargeExport() throws Exception {
        int databaseSizeBeforeUpdate = chargeExportRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeExportMockMvc.perform(put("/api/charge-exports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeExport)))
            .andExpect(status().isBadRequest());

        // Validate the ChargeExport in the database
        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChargeExport() throws Exception {
        // Initialize the database
        chargeExportRepository.saveAndFlush(chargeExport);

        int databaseSizeBeforeDelete = chargeExportRepository.findAll().size();

        // Delete the chargeExport
        restChargeExportMockMvc.perform(delete("/api/charge-exports/{id}", chargeExport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChargeExport> chargeExportList = chargeExportRepository.findAll();
        assertThat(chargeExportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
