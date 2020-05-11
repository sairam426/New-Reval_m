package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Providers;
import com.jhipster.reval.ui.repository.ProvidersRepository;
import com.jhipster.reval.ui.service.ProvidersService;

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
 * Integration tests for the {@link ProvidersResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ProvidersResourceIT {

    private static final String DEFAULT_PRO_NBR = "AAAAAAAAAA";
    private static final String UPDATED_PRO_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRO_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRO_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_PRO_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_PRO_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_GROUP_CD = "AAAAAAAAAA";
    private static final String UPDATED_PRO_GROUP_CD = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_LICENSE_NBR = "AAAAAAAAAA";
    private static final String UPDATED_PRO_LICENSE_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_LICENSE_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_PRO_LICENSE_STATUS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRO_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRO_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PRO_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PRO_STATE_CD = "AAAAAAAAAA";
    private static final String UPDATED_PRO_STATE_CD = "BBBBBBBBBB";

    private static final Long DEFAULT_PRO_ZIP = 1L;
    private static final Long UPDATED_PRO_ZIP = 2L;

    @Autowired
    private ProvidersRepository providersRepository;

    @Autowired
    private ProvidersService providersService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProvidersMockMvc;

    private Providers providers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Providers createEntity(EntityManager em) {
        Providers providers = new Providers()
            .proNbr(DEFAULT_PRO_NBR)
            .proName(DEFAULT_PRO_NAME)
            .proShortName(DEFAULT_PRO_SHORT_NAME)
            .proStatusCd(DEFAULT_PRO_STATUS_CD)
            .proTypeCd(DEFAULT_PRO_TYPE_CD)
            .proGroupCd(DEFAULT_PRO_GROUP_CD)
            .proLicenseNbr(DEFAULT_PRO_LICENSE_NBR)
            .proLicenseStatusCd(DEFAULT_PRO_LICENSE_STATUS_CD)
            .proAddress1(DEFAULT_PRO_ADDRESS_1)
            .proAddress2(DEFAULT_PRO_ADDRESS_2)
            .proCity(DEFAULT_PRO_CITY)
            .proStateCd(DEFAULT_PRO_STATE_CD)
            .proZip(DEFAULT_PRO_ZIP);
        return providers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Providers createUpdatedEntity(EntityManager em) {
        Providers providers = new Providers()
            .proNbr(UPDATED_PRO_NBR)
            .proName(UPDATED_PRO_NAME)
            .proShortName(UPDATED_PRO_SHORT_NAME)
            .proStatusCd(UPDATED_PRO_STATUS_CD)
            .proTypeCd(UPDATED_PRO_TYPE_CD)
            .proGroupCd(UPDATED_PRO_GROUP_CD)
            .proLicenseNbr(UPDATED_PRO_LICENSE_NBR)
            .proLicenseStatusCd(UPDATED_PRO_LICENSE_STATUS_CD)
            .proAddress1(UPDATED_PRO_ADDRESS_1)
            .proAddress2(UPDATED_PRO_ADDRESS_2)
            .proCity(UPDATED_PRO_CITY)
            .proStateCd(UPDATED_PRO_STATE_CD)
            .proZip(UPDATED_PRO_ZIP);
        return providers;
    }

    @BeforeEach
    public void initTest() {
        providers = createEntity(em);
    }

    @Test
    @Transactional
    public void createProviders() throws Exception {
        int databaseSizeBeforeCreate = providersRepository.findAll().size();

        // Create the Providers
        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isCreated());

        // Validate the Providers in the database
        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeCreate + 1);
        Providers testProviders = providersList.get(providersList.size() - 1);
        assertThat(testProviders.getProNbr()).isEqualTo(DEFAULT_PRO_NBR);
        assertThat(testProviders.getProName()).isEqualTo(DEFAULT_PRO_NAME);
        assertThat(testProviders.getProShortName()).isEqualTo(DEFAULT_PRO_SHORT_NAME);
        assertThat(testProviders.getProStatusCd()).isEqualTo(DEFAULT_PRO_STATUS_CD);
        assertThat(testProviders.getProTypeCd()).isEqualTo(DEFAULT_PRO_TYPE_CD);
        assertThat(testProviders.getProGroupCd()).isEqualTo(DEFAULT_PRO_GROUP_CD);
        assertThat(testProviders.getProLicenseNbr()).isEqualTo(DEFAULT_PRO_LICENSE_NBR);
        assertThat(testProviders.getProLicenseStatusCd()).isEqualTo(DEFAULT_PRO_LICENSE_STATUS_CD);
        assertThat(testProviders.getProAddress1()).isEqualTo(DEFAULT_PRO_ADDRESS_1);
        assertThat(testProviders.getProAddress2()).isEqualTo(DEFAULT_PRO_ADDRESS_2);
        assertThat(testProviders.getProCity()).isEqualTo(DEFAULT_PRO_CITY);
        assertThat(testProviders.getProStateCd()).isEqualTo(DEFAULT_PRO_STATE_CD);
        assertThat(testProviders.getProZip()).isEqualTo(DEFAULT_PRO_ZIP);
    }

    @Test
    @Transactional
    public void createProvidersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = providersRepository.findAll().size();

        // Create the Providers with an existing ID
        providers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        // Validate the Providers in the database
        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = providersRepository.findAll().size();
        // set the field null
        providers.setProNbr(null);

        // Create the Providers, which fails.

        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = providersRepository.findAll().size();
        // set the field null
        providers.setProName(null);

        // Create the Providers, which fails.

        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = providersRepository.findAll().size();
        // set the field null
        providers.setProShortName(null);

        // Create the Providers, which fails.

        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = providersRepository.findAll().size();
        // set the field null
        providers.setProStatusCd(null);

        // Create the Providers, which fails.

        restProvidersMockMvc.perform(post("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProviders() throws Exception {
        // Initialize the database
        providersRepository.saveAndFlush(providers);

        // Get all the providersList
        restProvidersMockMvc.perform(get("/api/providers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(providers.getId().intValue())))
            .andExpect(jsonPath("$.[*].proNbr").value(hasItem(DEFAULT_PRO_NBR)))
            .andExpect(jsonPath("$.[*].proName").value(hasItem(DEFAULT_PRO_NAME)))
            .andExpect(jsonPath("$.[*].proShortName").value(hasItem(DEFAULT_PRO_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].proStatusCd").value(hasItem(DEFAULT_PRO_STATUS_CD)))
            .andExpect(jsonPath("$.[*].proTypeCd").value(hasItem(DEFAULT_PRO_TYPE_CD)))
            .andExpect(jsonPath("$.[*].proGroupCd").value(hasItem(DEFAULT_PRO_GROUP_CD)))
            .andExpect(jsonPath("$.[*].proLicenseNbr").value(hasItem(DEFAULT_PRO_LICENSE_NBR)))
            .andExpect(jsonPath("$.[*].proLicenseStatusCd").value(hasItem(DEFAULT_PRO_LICENSE_STATUS_CD)))
            .andExpect(jsonPath("$.[*].proAddress1").value(hasItem(DEFAULT_PRO_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].proAddress2").value(hasItem(DEFAULT_PRO_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].proCity").value(hasItem(DEFAULT_PRO_CITY)))
            .andExpect(jsonPath("$.[*].proStateCd").value(hasItem(DEFAULT_PRO_STATE_CD)))
            .andExpect(jsonPath("$.[*].proZip").value(hasItem(DEFAULT_PRO_ZIP.intValue())));
    }
    
    @Test
    @Transactional
    public void getProviders() throws Exception {
        // Initialize the database
        providersRepository.saveAndFlush(providers);

        // Get the providers
        restProvidersMockMvc.perform(get("/api/providers/{id}", providers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(providers.getId().intValue()))
            .andExpect(jsonPath("$.proNbr").value(DEFAULT_PRO_NBR))
            .andExpect(jsonPath("$.proName").value(DEFAULT_PRO_NAME))
            .andExpect(jsonPath("$.proShortName").value(DEFAULT_PRO_SHORT_NAME))
            .andExpect(jsonPath("$.proStatusCd").value(DEFAULT_PRO_STATUS_CD))
            .andExpect(jsonPath("$.proTypeCd").value(DEFAULT_PRO_TYPE_CD))
            .andExpect(jsonPath("$.proGroupCd").value(DEFAULT_PRO_GROUP_CD))
            .andExpect(jsonPath("$.proLicenseNbr").value(DEFAULT_PRO_LICENSE_NBR))
            .andExpect(jsonPath("$.proLicenseStatusCd").value(DEFAULT_PRO_LICENSE_STATUS_CD))
            .andExpect(jsonPath("$.proAddress1").value(DEFAULT_PRO_ADDRESS_1))
            .andExpect(jsonPath("$.proAddress2").value(DEFAULT_PRO_ADDRESS_2))
            .andExpect(jsonPath("$.proCity").value(DEFAULT_PRO_CITY))
            .andExpect(jsonPath("$.proStateCd").value(DEFAULT_PRO_STATE_CD))
            .andExpect(jsonPath("$.proZip").value(DEFAULT_PRO_ZIP.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProviders() throws Exception {
        // Get the providers
        restProvidersMockMvc.perform(get("/api/providers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProviders() throws Exception {
        // Initialize the database
        providersService.save(providers);

        int databaseSizeBeforeUpdate = providersRepository.findAll().size();

        // Update the providers
        Providers updatedProviders = providersRepository.findById(providers.getId()).get();
        // Disconnect from session so that the updates on updatedProviders are not directly saved in db
        em.detach(updatedProviders);
        updatedProviders
            .proNbr(UPDATED_PRO_NBR)
            .proName(UPDATED_PRO_NAME)
            .proShortName(UPDATED_PRO_SHORT_NAME)
            .proStatusCd(UPDATED_PRO_STATUS_CD)
            .proTypeCd(UPDATED_PRO_TYPE_CD)
            .proGroupCd(UPDATED_PRO_GROUP_CD)
            .proLicenseNbr(UPDATED_PRO_LICENSE_NBR)
            .proLicenseStatusCd(UPDATED_PRO_LICENSE_STATUS_CD)
            .proAddress1(UPDATED_PRO_ADDRESS_1)
            .proAddress2(UPDATED_PRO_ADDRESS_2)
            .proCity(UPDATED_PRO_CITY)
            .proStateCd(UPDATED_PRO_STATE_CD)
            .proZip(UPDATED_PRO_ZIP);

        restProvidersMockMvc.perform(put("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProviders)))
            .andExpect(status().isOk());

        // Validate the Providers in the database
        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeUpdate);
        Providers testProviders = providersList.get(providersList.size() - 1);
        assertThat(testProviders.getProNbr()).isEqualTo(UPDATED_PRO_NBR);
        assertThat(testProviders.getProName()).isEqualTo(UPDATED_PRO_NAME);
        assertThat(testProviders.getProShortName()).isEqualTo(UPDATED_PRO_SHORT_NAME);
        assertThat(testProviders.getProStatusCd()).isEqualTo(UPDATED_PRO_STATUS_CD);
        assertThat(testProviders.getProTypeCd()).isEqualTo(UPDATED_PRO_TYPE_CD);
        assertThat(testProviders.getProGroupCd()).isEqualTo(UPDATED_PRO_GROUP_CD);
        assertThat(testProviders.getProLicenseNbr()).isEqualTo(UPDATED_PRO_LICENSE_NBR);
        assertThat(testProviders.getProLicenseStatusCd()).isEqualTo(UPDATED_PRO_LICENSE_STATUS_CD);
        assertThat(testProviders.getProAddress1()).isEqualTo(UPDATED_PRO_ADDRESS_1);
        assertThat(testProviders.getProAddress2()).isEqualTo(UPDATED_PRO_ADDRESS_2);
        assertThat(testProviders.getProCity()).isEqualTo(UPDATED_PRO_CITY);
        assertThat(testProviders.getProStateCd()).isEqualTo(UPDATED_PRO_STATE_CD);
        assertThat(testProviders.getProZip()).isEqualTo(UPDATED_PRO_ZIP);
    }

    @Test
    @Transactional
    public void updateNonExistingProviders() throws Exception {
        int databaseSizeBeforeUpdate = providersRepository.findAll().size();

        // Create the Providers

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvidersMockMvc.perform(put("/api/providers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(providers)))
            .andExpect(status().isBadRequest());

        // Validate the Providers in the database
        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProviders() throws Exception {
        // Initialize the database
        providersService.save(providers);

        int databaseSizeBeforeDelete = providersRepository.findAll().size();

        // Delete the providers
        restProvidersMockMvc.perform(delete("/api/providers/{id}", providers.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Providers> providersList = providersRepository.findAll();
        assertThat(providersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
