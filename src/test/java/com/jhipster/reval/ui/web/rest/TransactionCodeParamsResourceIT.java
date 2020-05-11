package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.TransactionCodeParams;
import com.jhipster.reval.ui.repository.TransactionCodeParamsRepository;
import com.jhipster.reval.ui.service.TransactionCodeParamsService;

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
 * Integration tests for the {@link TransactionCodeParamsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TransactionCodeParamsResourceIT {

    private static final String DEFAULT_TCP_TCD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TCP_TCD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TCP_PARAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TCP_PARAM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TCP_PARAM_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TCP_PARAM_DESC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TCP_ENABLED_IND = false;
    private static final Boolean UPDATED_TCP_ENABLED_IND = true;

    private static final String DEFAULT_TCP_PARAM_DATA_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_TCP_PARAM_DATA_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_TCP_PARAM_LENGTH = "AAAAAAAAAA";
    private static final String UPDATED_TCP_PARAM_LENGTH = "BBBBBBBBBB";

    private static final String DEFAULT_TCP_DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_TCP_DEFAULT_VALUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TCP_PARAM_LOV_VALIDATION_IND = false;
    private static final Boolean UPDATED_TCP_PARAM_LOV_VALIDATION_IND = true;

    private static final String DEFAULT_TCP_PARAM_LKT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TCP_PARAM_LKT_TYPE = "BBBBBBBBBB";

    @Autowired
    private TransactionCodeParamsRepository transactionCodeParamsRepository;

    @Autowired
    private TransactionCodeParamsService transactionCodeParamsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionCodeParamsMockMvc;

    private TransactionCodeParams transactionCodeParams;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionCodeParams createEntity(EntityManager em) {
        TransactionCodeParams transactionCodeParams = new TransactionCodeParams()
            .tcpTcdCode(DEFAULT_TCP_TCD_CODE)
            .tcpParamName(DEFAULT_TCP_PARAM_NAME)
            .tcpParamDesc(DEFAULT_TCP_PARAM_DESC)
            .tcpEnabledInd(DEFAULT_TCP_ENABLED_IND)
            .tcpParamDataTypeCd(DEFAULT_TCP_PARAM_DATA_TYPE_CD)
            .tcpParamLength(DEFAULT_TCP_PARAM_LENGTH)
            .tcpDefaultValue(DEFAULT_TCP_DEFAULT_VALUE)
            .tcpParamLovValidationInd(DEFAULT_TCP_PARAM_LOV_VALIDATION_IND)
            .tcpParamLktType(DEFAULT_TCP_PARAM_LKT_TYPE);
        return transactionCodeParams;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionCodeParams createUpdatedEntity(EntityManager em) {
        TransactionCodeParams transactionCodeParams = new TransactionCodeParams()
            .tcpTcdCode(UPDATED_TCP_TCD_CODE)
            .tcpParamName(UPDATED_TCP_PARAM_NAME)
            .tcpParamDesc(UPDATED_TCP_PARAM_DESC)
            .tcpEnabledInd(UPDATED_TCP_ENABLED_IND)
            .tcpParamDataTypeCd(UPDATED_TCP_PARAM_DATA_TYPE_CD)
            .tcpParamLength(UPDATED_TCP_PARAM_LENGTH)
            .tcpDefaultValue(UPDATED_TCP_DEFAULT_VALUE)
            .tcpParamLovValidationInd(UPDATED_TCP_PARAM_LOV_VALIDATION_IND)
            .tcpParamLktType(UPDATED_TCP_PARAM_LKT_TYPE);
        return transactionCodeParams;
    }

    @BeforeEach
    public void initTest() {
        transactionCodeParams = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionCodeParams() throws Exception {
        int databaseSizeBeforeCreate = transactionCodeParamsRepository.findAll().size();

        // Create the TransactionCodeParams
        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isCreated());

        // Validate the TransactionCodeParams in the database
        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionCodeParams testTransactionCodeParams = transactionCodeParamsList.get(transactionCodeParamsList.size() - 1);
        assertThat(testTransactionCodeParams.getTcpTcdCode()).isEqualTo(DEFAULT_TCP_TCD_CODE);
        assertThat(testTransactionCodeParams.getTcpParamName()).isEqualTo(DEFAULT_TCP_PARAM_NAME);
        assertThat(testTransactionCodeParams.getTcpParamDesc()).isEqualTo(DEFAULT_TCP_PARAM_DESC);
        assertThat(testTransactionCodeParams.isTcpEnabledInd()).isEqualTo(DEFAULT_TCP_ENABLED_IND);
        assertThat(testTransactionCodeParams.getTcpParamDataTypeCd()).isEqualTo(DEFAULT_TCP_PARAM_DATA_TYPE_CD);
        assertThat(testTransactionCodeParams.getTcpParamLength()).isEqualTo(DEFAULT_TCP_PARAM_LENGTH);
        assertThat(testTransactionCodeParams.getTcpDefaultValue()).isEqualTo(DEFAULT_TCP_DEFAULT_VALUE);
        assertThat(testTransactionCodeParams.isTcpParamLovValidationInd()).isEqualTo(DEFAULT_TCP_PARAM_LOV_VALIDATION_IND);
        assertThat(testTransactionCodeParams.getTcpParamLktType()).isEqualTo(DEFAULT_TCP_PARAM_LKT_TYPE);
    }

    @Test
    @Transactional
    public void createTransactionCodeParamsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionCodeParamsRepository.findAll().size();

        // Create the TransactionCodeParams with an existing ID
        transactionCodeParams.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionCodeParams in the database
        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTcpTcdCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpTcdCode(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamName(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamDesc(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpEnabledIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpEnabledInd(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamDataTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamDataTypeCd(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamLength(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpDefaultValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpDefaultValue(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamLovValidationIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamLovValidationInd(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTcpParamLktTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionCodeParamsRepository.findAll().size();
        // set the field null
        transactionCodeParams.setTcpParamLktType(null);

        // Create the TransactionCodeParams, which fails.

        restTransactionCodeParamsMockMvc.perform(post("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionCodeParams() throws Exception {
        // Initialize the database
        transactionCodeParamsRepository.saveAndFlush(transactionCodeParams);

        // Get all the transactionCodeParamsList
        restTransactionCodeParamsMockMvc.perform(get("/api/transaction-code-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionCodeParams.getId().intValue())))
            .andExpect(jsonPath("$.[*].tcpTcdCode").value(hasItem(DEFAULT_TCP_TCD_CODE)))
            .andExpect(jsonPath("$.[*].tcpParamName").value(hasItem(DEFAULT_TCP_PARAM_NAME)))
            .andExpect(jsonPath("$.[*].tcpParamDesc").value(hasItem(DEFAULT_TCP_PARAM_DESC)))
            .andExpect(jsonPath("$.[*].tcpEnabledInd").value(hasItem(DEFAULT_TCP_ENABLED_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].tcpParamDataTypeCd").value(hasItem(DEFAULT_TCP_PARAM_DATA_TYPE_CD)))
            .andExpect(jsonPath("$.[*].tcpParamLength").value(hasItem(DEFAULT_TCP_PARAM_LENGTH)))
            .andExpect(jsonPath("$.[*].tcpDefaultValue").value(hasItem(DEFAULT_TCP_DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].tcpParamLovValidationInd").value(hasItem(DEFAULT_TCP_PARAM_LOV_VALIDATION_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].tcpParamLktType").value(hasItem(DEFAULT_TCP_PARAM_LKT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getTransactionCodeParams() throws Exception {
        // Initialize the database
        transactionCodeParamsRepository.saveAndFlush(transactionCodeParams);

        // Get the transactionCodeParams
        restTransactionCodeParamsMockMvc.perform(get("/api/transaction-code-params/{id}", transactionCodeParams.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionCodeParams.getId().intValue()))
            .andExpect(jsonPath("$.tcpTcdCode").value(DEFAULT_TCP_TCD_CODE))
            .andExpect(jsonPath("$.tcpParamName").value(DEFAULT_TCP_PARAM_NAME))
            .andExpect(jsonPath("$.tcpParamDesc").value(DEFAULT_TCP_PARAM_DESC))
            .andExpect(jsonPath("$.tcpEnabledInd").value(DEFAULT_TCP_ENABLED_IND.booleanValue()))
            .andExpect(jsonPath("$.tcpParamDataTypeCd").value(DEFAULT_TCP_PARAM_DATA_TYPE_CD))
            .andExpect(jsonPath("$.tcpParamLength").value(DEFAULT_TCP_PARAM_LENGTH))
            .andExpect(jsonPath("$.tcpDefaultValue").value(DEFAULT_TCP_DEFAULT_VALUE))
            .andExpect(jsonPath("$.tcpParamLovValidationInd").value(DEFAULT_TCP_PARAM_LOV_VALIDATION_IND.booleanValue()))
            .andExpect(jsonPath("$.tcpParamLktType").value(DEFAULT_TCP_PARAM_LKT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingTransactionCodeParams() throws Exception {
        // Get the transactionCodeParams
        restTransactionCodeParamsMockMvc.perform(get("/api/transaction-code-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionCodeParams() throws Exception {
        // Initialize the database
        transactionCodeParamsService.save(transactionCodeParams);

        int databaseSizeBeforeUpdate = transactionCodeParamsRepository.findAll().size();

        // Update the transactionCodeParams
        TransactionCodeParams updatedTransactionCodeParams = transactionCodeParamsRepository.findById(transactionCodeParams.getId()).get();
        // Disconnect from session so that the updates on updatedTransactionCodeParams are not directly saved in db
        em.detach(updatedTransactionCodeParams);
        updatedTransactionCodeParams
            .tcpTcdCode(UPDATED_TCP_TCD_CODE)
            .tcpParamName(UPDATED_TCP_PARAM_NAME)
            .tcpParamDesc(UPDATED_TCP_PARAM_DESC)
            .tcpEnabledInd(UPDATED_TCP_ENABLED_IND)
            .tcpParamDataTypeCd(UPDATED_TCP_PARAM_DATA_TYPE_CD)
            .tcpParamLength(UPDATED_TCP_PARAM_LENGTH)
            .tcpDefaultValue(UPDATED_TCP_DEFAULT_VALUE)
            .tcpParamLovValidationInd(UPDATED_TCP_PARAM_LOV_VALIDATION_IND)
            .tcpParamLktType(UPDATED_TCP_PARAM_LKT_TYPE);

        restTransactionCodeParamsMockMvc.perform(put("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactionCodeParams)))
            .andExpect(status().isOk());

        // Validate the TransactionCodeParams in the database
        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeUpdate);
        TransactionCodeParams testTransactionCodeParams = transactionCodeParamsList.get(transactionCodeParamsList.size() - 1);
        assertThat(testTransactionCodeParams.getTcpTcdCode()).isEqualTo(UPDATED_TCP_TCD_CODE);
        assertThat(testTransactionCodeParams.getTcpParamName()).isEqualTo(UPDATED_TCP_PARAM_NAME);
        assertThat(testTransactionCodeParams.getTcpParamDesc()).isEqualTo(UPDATED_TCP_PARAM_DESC);
        assertThat(testTransactionCodeParams.isTcpEnabledInd()).isEqualTo(UPDATED_TCP_ENABLED_IND);
        assertThat(testTransactionCodeParams.getTcpParamDataTypeCd()).isEqualTo(UPDATED_TCP_PARAM_DATA_TYPE_CD);
        assertThat(testTransactionCodeParams.getTcpParamLength()).isEqualTo(UPDATED_TCP_PARAM_LENGTH);
        assertThat(testTransactionCodeParams.getTcpDefaultValue()).isEqualTo(UPDATED_TCP_DEFAULT_VALUE);
        assertThat(testTransactionCodeParams.isTcpParamLovValidationInd()).isEqualTo(UPDATED_TCP_PARAM_LOV_VALIDATION_IND);
        assertThat(testTransactionCodeParams.getTcpParamLktType()).isEqualTo(UPDATED_TCP_PARAM_LKT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionCodeParams() throws Exception {
        int databaseSizeBeforeUpdate = transactionCodeParamsRepository.findAll().size();

        // Create the TransactionCodeParams

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionCodeParamsMockMvc.perform(put("/api/transaction-code-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactionCodeParams)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionCodeParams in the database
        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactionCodeParams() throws Exception {
        // Initialize the database
        transactionCodeParamsService.save(transactionCodeParams);

        int databaseSizeBeforeDelete = transactionCodeParamsRepository.findAll().size();

        // Delete the transactionCodeParams
        restTransactionCodeParamsMockMvc.perform(delete("/api/transaction-code-params/{id}", transactionCodeParams.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransactionCodeParams> transactionCodeParamsList = transactionCodeParamsRepository.findAll();
        assertThat(transactionCodeParamsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
