package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.Transactions;
import com.jhipster.reval.ui.domain.TransactionDetails;
import com.jhipster.reval.ui.repository.TransactionsRepository;
import com.jhipster.reval.ui.service.TransactionsService;
import com.jhipster.reval.ui.service.dto.TransactionsCriteria;
import com.jhipster.reval.ui.service.TransactionsQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TransactionsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TransactionsResourceIT {

    private static final Long DEFAULT_TXN_ENTITY_ID = 1L;
    private static final Long UPDATED_TXN_ENTITY_ID = 2L;
    private static final Long SMALLER_TXN_ENTITY_ID = 1L - 1L;

    private static final String DEFAULT_TXN_ENTITY_NBR = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ENTITY_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_TXN_TCD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TXN_TCD_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TXN_POST_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TXN_POST_DT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TXN_POST_DT = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_TXN_STATUS_CD = "AAAAAAAAAA";
    private static final String UPDATED_TXN_STATUS_CD = "BBBBBBBBBB";

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private TransactionsQueryService transactionsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionsMockMvc;

    private Transactions transactions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createEntity(EntityManager em) {
        Transactions transactions = new Transactions()
            .txnEntityId(DEFAULT_TXN_ENTITY_ID)
            .txnEntityNbr(DEFAULT_TXN_ENTITY_NBR)
            .txnTcdCode(DEFAULT_TXN_TCD_CODE)
            .txnPostDt(DEFAULT_TXN_POST_DT)
            .txnStatusCd(DEFAULT_TXN_STATUS_CD);
        return transactions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transactions createUpdatedEntity(EntityManager em) {
        Transactions transactions = new Transactions()
            .txnEntityId(UPDATED_TXN_ENTITY_ID)
            .txnEntityNbr(UPDATED_TXN_ENTITY_NBR)
            .txnTcdCode(UPDATED_TXN_TCD_CODE)
            .txnPostDt(UPDATED_TXN_POST_DT)
            .txnStatusCd(UPDATED_TXN_STATUS_CD);
        return transactions;
    }

    @BeforeEach
    public void initTest() {
        transactions = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactions() throws Exception {
        int databaseSizeBeforeCreate = transactionsRepository.findAll().size();

        // Create the Transactions
        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isCreated());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeCreate + 1);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getTxnEntityId()).isEqualTo(DEFAULT_TXN_ENTITY_ID);
        assertThat(testTransactions.getTxnEntityNbr()).isEqualTo(DEFAULT_TXN_ENTITY_NBR);
        assertThat(testTransactions.getTxnTcdCode()).isEqualTo(DEFAULT_TXN_TCD_CODE);
        assertThat(testTransactions.getTxnPostDt()).isEqualTo(DEFAULT_TXN_POST_DT);
        assertThat(testTransactions.getTxnStatusCd()).isEqualTo(DEFAULT_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void createTransactionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionsRepository.findAll().size();

        // Create the Transactions with an existing ID
        transactions.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTxnEntityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTxnEntityId(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTxnEntityNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTxnEntityNbr(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTxnTcdCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTxnTcdCode(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTxnPostDtIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTxnPostDt(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTxnStatusCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionsRepository.findAll().size();
        // set the field null
        transactions.setTxnStatusCd(null);

        // Create the Transactions, which fails.

        restTransactionsMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList
        restTransactionsMockMvc.perform(get("/api/transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactions.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnEntityId").value(hasItem(DEFAULT_TXN_ENTITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].txnEntityNbr").value(hasItem(DEFAULT_TXN_ENTITY_NBR)))
            .andExpect(jsonPath("$.[*].txnTcdCode").value(hasItem(DEFAULT_TXN_TCD_CODE)))
            .andExpect(jsonPath("$.[*].txnPostDt").value(hasItem(DEFAULT_TXN_POST_DT.toString())))
            .andExpect(jsonPath("$.[*].txnStatusCd").value(hasItem(DEFAULT_TXN_STATUS_CD)));
    }
    
    @Test
    @Transactional
    public void getTransactions() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get the transactions
        restTransactionsMockMvc.perform(get("/api/transactions/{id}", transactions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactions.getId().intValue()))
            .andExpect(jsonPath("$.txnEntityId").value(DEFAULT_TXN_ENTITY_ID.intValue()))
            .andExpect(jsonPath("$.txnEntityNbr").value(DEFAULT_TXN_ENTITY_NBR))
            .andExpect(jsonPath("$.txnTcdCode").value(DEFAULT_TXN_TCD_CODE))
            .andExpect(jsonPath("$.txnPostDt").value(DEFAULT_TXN_POST_DT.toString()))
            .andExpect(jsonPath("$.txnStatusCd").value(DEFAULT_TXN_STATUS_CD));
    }


    @Test
    @Transactional
    public void getTransactionsByIdFiltering() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        Long id = transactions.getId();

        defaultTransactionsShouldBeFound("id.equals=" + id);
        defaultTransactionsShouldNotBeFound("id.notEquals=" + id);

        defaultTransactionsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTransactionsShouldNotBeFound("id.greaterThan=" + id);

        defaultTransactionsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTransactionsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId equals to DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.equals=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId equals to UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.equals=" + UPDATED_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId not equals to DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.notEquals=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId not equals to UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.notEquals=" + UPDATED_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsInShouldWork() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId in DEFAULT_TXN_ENTITY_ID or UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.in=" + DEFAULT_TXN_ENTITY_ID + "," + UPDATED_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId equals to UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.in=" + UPDATED_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId is not null
        defaultTransactionsShouldBeFound("txnEntityId.specified=true");

        // Get all the transactionsList where txnEntityId is null
        defaultTransactionsShouldNotBeFound("txnEntityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId is greater than or equal to DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.greaterThanOrEqual=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId is greater than or equal to UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.greaterThanOrEqual=" + UPDATED_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId is less than or equal to DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.lessThanOrEqual=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId is less than or equal to SMALLER_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.lessThanOrEqual=" + SMALLER_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId is less than DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.lessThan=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId is less than UPDATED_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.lessThan=" + UPDATED_TXN_ENTITY_ID);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityId is greater than DEFAULT_TXN_ENTITY_ID
        defaultTransactionsShouldNotBeFound("txnEntityId.greaterThan=" + DEFAULT_TXN_ENTITY_ID);

        // Get all the transactionsList where txnEntityId is greater than SMALLER_TXN_ENTITY_ID
        defaultTransactionsShouldBeFound("txnEntityId.greaterThan=" + SMALLER_TXN_ENTITY_ID);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr equals to DEFAULT_TXN_ENTITY_NBR
        defaultTransactionsShouldBeFound("txnEntityNbr.equals=" + DEFAULT_TXN_ENTITY_NBR);

        // Get all the transactionsList where txnEntityNbr equals to UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldNotBeFound("txnEntityNbr.equals=" + UPDATED_TXN_ENTITY_NBR);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr not equals to DEFAULT_TXN_ENTITY_NBR
        defaultTransactionsShouldNotBeFound("txnEntityNbr.notEquals=" + DEFAULT_TXN_ENTITY_NBR);

        // Get all the transactionsList where txnEntityNbr not equals to UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldBeFound("txnEntityNbr.notEquals=" + UPDATED_TXN_ENTITY_NBR);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrIsInShouldWork() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr in DEFAULT_TXN_ENTITY_NBR or UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldBeFound("txnEntityNbr.in=" + DEFAULT_TXN_ENTITY_NBR + "," + UPDATED_TXN_ENTITY_NBR);

        // Get all the transactionsList where txnEntityNbr equals to UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldNotBeFound("txnEntityNbr.in=" + UPDATED_TXN_ENTITY_NBR);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr is not null
        defaultTransactionsShouldBeFound("txnEntityNbr.specified=true");

        // Get all the transactionsList where txnEntityNbr is null
        defaultTransactionsShouldNotBeFound("txnEntityNbr.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr contains DEFAULT_TXN_ENTITY_NBR
        defaultTransactionsShouldBeFound("txnEntityNbr.contains=" + DEFAULT_TXN_ENTITY_NBR);

        // Get all the transactionsList where txnEntityNbr contains UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldNotBeFound("txnEntityNbr.contains=" + UPDATED_TXN_ENTITY_NBR);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnEntityNbrNotContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnEntityNbr does not contain DEFAULT_TXN_ENTITY_NBR
        defaultTransactionsShouldNotBeFound("txnEntityNbr.doesNotContain=" + DEFAULT_TXN_ENTITY_NBR);

        // Get all the transactionsList where txnEntityNbr does not contain UPDATED_TXN_ENTITY_NBR
        defaultTransactionsShouldBeFound("txnEntityNbr.doesNotContain=" + UPDATED_TXN_ENTITY_NBR);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode equals to DEFAULT_TXN_TCD_CODE
        defaultTransactionsShouldBeFound("txnTcdCode.equals=" + DEFAULT_TXN_TCD_CODE);

        // Get all the transactionsList where txnTcdCode equals to UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldNotBeFound("txnTcdCode.equals=" + UPDATED_TXN_TCD_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode not equals to DEFAULT_TXN_TCD_CODE
        defaultTransactionsShouldNotBeFound("txnTcdCode.notEquals=" + DEFAULT_TXN_TCD_CODE);

        // Get all the transactionsList where txnTcdCode not equals to UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldBeFound("txnTcdCode.notEquals=" + UPDATED_TXN_TCD_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeIsInShouldWork() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode in DEFAULT_TXN_TCD_CODE or UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldBeFound("txnTcdCode.in=" + DEFAULT_TXN_TCD_CODE + "," + UPDATED_TXN_TCD_CODE);

        // Get all the transactionsList where txnTcdCode equals to UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldNotBeFound("txnTcdCode.in=" + UPDATED_TXN_TCD_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode is not null
        defaultTransactionsShouldBeFound("txnTcdCode.specified=true");

        // Get all the transactionsList where txnTcdCode is null
        defaultTransactionsShouldNotBeFound("txnTcdCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode contains DEFAULT_TXN_TCD_CODE
        defaultTransactionsShouldBeFound("txnTcdCode.contains=" + DEFAULT_TXN_TCD_CODE);

        // Get all the transactionsList where txnTcdCode contains UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldNotBeFound("txnTcdCode.contains=" + UPDATED_TXN_TCD_CODE);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnTcdCodeNotContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnTcdCode does not contain DEFAULT_TXN_TCD_CODE
        defaultTransactionsShouldNotBeFound("txnTcdCode.doesNotContain=" + DEFAULT_TXN_TCD_CODE);

        // Get all the transactionsList where txnTcdCode does not contain UPDATED_TXN_TCD_CODE
        defaultTransactionsShouldBeFound("txnTcdCode.doesNotContain=" + UPDATED_TXN_TCD_CODE);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt equals to DEFAULT_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.equals=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt equals to UPDATED_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.equals=" + UPDATED_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt not equals to DEFAULT_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.notEquals=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt not equals to UPDATED_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.notEquals=" + UPDATED_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsInShouldWork() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt in DEFAULT_TXN_POST_DT or UPDATED_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.in=" + DEFAULT_TXN_POST_DT + "," + UPDATED_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt equals to UPDATED_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.in=" + UPDATED_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt is not null
        defaultTransactionsShouldBeFound("txnPostDt.specified=true");

        // Get all the transactionsList where txnPostDt is null
        defaultTransactionsShouldNotBeFound("txnPostDt.specified=false");
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt is greater than or equal to DEFAULT_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.greaterThanOrEqual=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt is greater than or equal to UPDATED_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.greaterThanOrEqual=" + UPDATED_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt is less than or equal to DEFAULT_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.lessThanOrEqual=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt is less than or equal to SMALLER_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.lessThanOrEqual=" + SMALLER_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsLessThanSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt is less than DEFAULT_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.lessThan=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt is less than UPDATED_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.lessThan=" + UPDATED_TXN_POST_DT);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnPostDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnPostDt is greater than DEFAULT_TXN_POST_DT
        defaultTransactionsShouldNotBeFound("txnPostDt.greaterThan=" + DEFAULT_TXN_POST_DT);

        // Get all the transactionsList where txnPostDt is greater than SMALLER_TXN_POST_DT
        defaultTransactionsShouldBeFound("txnPostDt.greaterThan=" + SMALLER_TXN_POST_DT);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd equals to DEFAULT_TXN_STATUS_CD
        defaultTransactionsShouldBeFound("txnStatusCd.equals=" + DEFAULT_TXN_STATUS_CD);

        // Get all the transactionsList where txnStatusCd equals to UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldNotBeFound("txnStatusCd.equals=" + UPDATED_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd not equals to DEFAULT_TXN_STATUS_CD
        defaultTransactionsShouldNotBeFound("txnStatusCd.notEquals=" + DEFAULT_TXN_STATUS_CD);

        // Get all the transactionsList where txnStatusCd not equals to UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldBeFound("txnStatusCd.notEquals=" + UPDATED_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdIsInShouldWork() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd in DEFAULT_TXN_STATUS_CD or UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldBeFound("txnStatusCd.in=" + DEFAULT_TXN_STATUS_CD + "," + UPDATED_TXN_STATUS_CD);

        // Get all the transactionsList where txnStatusCd equals to UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldNotBeFound("txnStatusCd.in=" + UPDATED_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd is not null
        defaultTransactionsShouldBeFound("txnStatusCd.specified=true");

        // Get all the transactionsList where txnStatusCd is null
        defaultTransactionsShouldNotBeFound("txnStatusCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd contains DEFAULT_TXN_STATUS_CD
        defaultTransactionsShouldBeFound("txnStatusCd.contains=" + DEFAULT_TXN_STATUS_CD);

        // Get all the transactionsList where txnStatusCd contains UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldNotBeFound("txnStatusCd.contains=" + UPDATED_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void getAllTransactionsByTxnStatusCdNotContainsSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);

        // Get all the transactionsList where txnStatusCd does not contain DEFAULT_TXN_STATUS_CD
        defaultTransactionsShouldNotBeFound("txnStatusCd.doesNotContain=" + DEFAULT_TXN_STATUS_CD);

        // Get all the transactionsList where txnStatusCd does not contain UPDATED_TXN_STATUS_CD
        defaultTransactionsShouldBeFound("txnStatusCd.doesNotContain=" + UPDATED_TXN_STATUS_CD);
    }


    @Test
    @Transactional
    public void getAllTransactionsByTxnDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        transactionsRepository.saveAndFlush(transactions);
        TransactionDetails txnDetails = TransactionDetailsResourceIT.createEntity(em);
        em.persist(txnDetails);
        em.flush();
        transactions.addTxnDetails(txnDetails);
        transactionsRepository.saveAndFlush(transactions);
        Long txnDetailsId = txnDetails.getId();

        // Get all the transactionsList where txnDetails equals to txnDetailsId
        defaultTransactionsShouldBeFound("txnDetailsId.equals=" + txnDetailsId);

        // Get all the transactionsList where txnDetails equals to txnDetailsId + 1
        defaultTransactionsShouldNotBeFound("txnDetailsId.equals=" + (txnDetailsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTransactionsShouldBeFound(String filter) throws Exception {
        restTransactionsMockMvc.perform(get("/api/transactions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactions.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnEntityId").value(hasItem(DEFAULT_TXN_ENTITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].txnEntityNbr").value(hasItem(DEFAULT_TXN_ENTITY_NBR)))
            .andExpect(jsonPath("$.[*].txnTcdCode").value(hasItem(DEFAULT_TXN_TCD_CODE)))
            .andExpect(jsonPath("$.[*].txnPostDt").value(hasItem(DEFAULT_TXN_POST_DT.toString())))
            .andExpect(jsonPath("$.[*].txnStatusCd").value(hasItem(DEFAULT_TXN_STATUS_CD)));

        // Check, that the count call also returns 1
        restTransactionsMockMvc.perform(get("/api/transactions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTransactionsShouldNotBeFound(String filter) throws Exception {
        restTransactionsMockMvc.perform(get("/api/transactions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTransactionsMockMvc.perform(get("/api/transactions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTransactions() throws Exception {
        // Get the transactions
        restTransactionsMockMvc.perform(get("/api/transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactions() throws Exception {
        // Initialize the database
        transactionsService.save(transactions);

        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();

        // Update the transactions
        Transactions updatedTransactions = transactionsRepository.findById(transactions.getId()).get();
        // Disconnect from session so that the updates on updatedTransactions are not directly saved in db
        em.detach(updatedTransactions);
        updatedTransactions
            .txnEntityId(UPDATED_TXN_ENTITY_ID)
            .txnEntityNbr(UPDATED_TXN_ENTITY_NBR)
            .txnTcdCode(UPDATED_TXN_TCD_CODE)
            .txnPostDt(UPDATED_TXN_POST_DT)
            .txnStatusCd(UPDATED_TXN_STATUS_CD);

        restTransactionsMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactions)))
            .andExpect(status().isOk());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
        Transactions testTransactions = transactionsList.get(transactionsList.size() - 1);
        assertThat(testTransactions.getTxnEntityId()).isEqualTo(UPDATED_TXN_ENTITY_ID);
        assertThat(testTransactions.getTxnEntityNbr()).isEqualTo(UPDATED_TXN_ENTITY_NBR);
        assertThat(testTransactions.getTxnTcdCode()).isEqualTo(UPDATED_TXN_TCD_CODE);
        assertThat(testTransactions.getTxnPostDt()).isEqualTo(UPDATED_TXN_POST_DT);
        assertThat(testTransactions.getTxnStatusCd()).isEqualTo(UPDATED_TXN_STATUS_CD);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactions() throws Exception {
        int databaseSizeBeforeUpdate = transactionsRepository.findAll().size();

        // Create the Transactions

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionsMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transactions)))
            .andExpect(status().isBadRequest());

        // Validate the Transactions in the database
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransactions() throws Exception {
        // Initialize the database
        transactionsService.save(transactions);

        int databaseSizeBeforeDelete = transactionsRepository.findAll().size();

        // Delete the transactions
        restTransactionsMockMvc.perform(delete("/api/transactions/{id}", transactions.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transactions> transactionsList = transactionsRepository.findAll();
        assertThat(transactionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
