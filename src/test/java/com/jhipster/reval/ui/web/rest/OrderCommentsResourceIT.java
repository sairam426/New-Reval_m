package com.jhipster.reval.ui.web.rest;

import com.jhipster.reval.ui.RevalApp;
import com.jhipster.reval.ui.domain.OrderComments;
import com.jhipster.reval.ui.domain.Orders;
import com.jhipster.reval.ui.repository.OrderCommentsRepository;
import com.jhipster.reval.ui.service.OrderCommentsService;
import com.jhipster.reval.ui.service.dto.OrderCommentsCriteria;
import com.jhipster.reval.ui.service.OrderCommentsQueryService;

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
 * Integration tests for the {@link OrderCommentsResource} REST controller.
 */
@SpringBootTest(classes = RevalApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class OrderCommentsResourceIT {

    private static final String DEFAULT_OCM_COMMENT_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_OCM_COMMENT_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_OCM_COMMENT_SUB_TYPE_CD = "AAAAAAAAAA";
    private static final String UPDATED_OCM_COMMENT_SUB_TYPE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_OCM_COMMENT_BY = "AAAAAAAAAA";
    private static final String UPDATED_OCM_COMMENT_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OCM_COMMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OCM_COMMENT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_OCM_COMMENT_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_OCM_COMMENT_IMP_IND = false;
    private static final Boolean UPDATED_OCM_COMMENT_IMP_IND = true;

    private static final String DEFAULT_OCM_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_OCM_COMMENT = "BBBBBBBBBB";

    @Autowired
    private OrderCommentsRepository orderCommentsRepository;

    @Autowired
    private OrderCommentsService orderCommentsService;

    @Autowired
    private OrderCommentsQueryService orderCommentsQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderCommentsMockMvc;

    private OrderComments orderComments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderComments createEntity(EntityManager em) {
        OrderComments orderComments = new OrderComments()
            .ocmCommentTypeCd(DEFAULT_OCM_COMMENT_TYPE_CD)
            .ocmCommentSubTypeCd(DEFAULT_OCM_COMMENT_SUB_TYPE_CD)
            .ocmCommentBy(DEFAULT_OCM_COMMENT_BY)
            .ocmCommentDate(DEFAULT_OCM_COMMENT_DATE)
            .ocmCommentImpInd(DEFAULT_OCM_COMMENT_IMP_IND)
            .ocmComment(DEFAULT_OCM_COMMENT);
        return orderComments;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderComments createUpdatedEntity(EntityManager em) {
        OrderComments orderComments = new OrderComments()
            .ocmCommentTypeCd(UPDATED_OCM_COMMENT_TYPE_CD)
            .ocmCommentSubTypeCd(UPDATED_OCM_COMMENT_SUB_TYPE_CD)
            .ocmCommentBy(UPDATED_OCM_COMMENT_BY)
            .ocmCommentDate(UPDATED_OCM_COMMENT_DATE)
            .ocmCommentImpInd(UPDATED_OCM_COMMENT_IMP_IND)
            .ocmComment(UPDATED_OCM_COMMENT);
        return orderComments;
    }

    @BeforeEach
    public void initTest() {
        orderComments = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderComments() throws Exception {
        int databaseSizeBeforeCreate = orderCommentsRepository.findAll().size();

        // Create the OrderComments
        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isCreated());

        // Validate the OrderComments in the database
        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeCreate + 1);
        OrderComments testOrderComments = orderCommentsList.get(orderCommentsList.size() - 1);
        assertThat(testOrderComments.getOcmCommentTypeCd()).isEqualTo(DEFAULT_OCM_COMMENT_TYPE_CD);
        assertThat(testOrderComments.getOcmCommentSubTypeCd()).isEqualTo(DEFAULT_OCM_COMMENT_SUB_TYPE_CD);
        assertThat(testOrderComments.getOcmCommentBy()).isEqualTo(DEFAULT_OCM_COMMENT_BY);
        assertThat(testOrderComments.getOcmCommentDate()).isEqualTo(DEFAULT_OCM_COMMENT_DATE);
        assertThat(testOrderComments.isOcmCommentImpInd()).isEqualTo(DEFAULT_OCM_COMMENT_IMP_IND);
        assertThat(testOrderComments.getOcmComment()).isEqualTo(DEFAULT_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void createOrderCommentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderCommentsRepository.findAll().size();

        // Create the OrderComments with an existing ID
        orderComments.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        // Validate the OrderComments in the database
        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOcmCommentTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmCommentTypeCd(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOcmCommentSubTypeCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmCommentSubTypeCd(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOcmCommentByIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmCommentBy(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOcmCommentDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmCommentDate(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOcmCommentImpIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmCommentImpInd(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOcmCommentIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderCommentsRepository.findAll().size();
        // set the field null
        orderComments.setOcmComment(null);

        // Create the OrderComments, which fails.

        restOrderCommentsMockMvc.perform(post("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrderComments() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList
        restOrderCommentsMockMvc.perform(get("/api/order-comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderComments.getId().intValue())))
            .andExpect(jsonPath("$.[*].ocmCommentTypeCd").value(hasItem(DEFAULT_OCM_COMMENT_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ocmCommentSubTypeCd").value(hasItem(DEFAULT_OCM_COMMENT_SUB_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ocmCommentBy").value(hasItem(DEFAULT_OCM_COMMENT_BY)))
            .andExpect(jsonPath("$.[*].ocmCommentDate").value(hasItem(DEFAULT_OCM_COMMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].ocmCommentImpInd").value(hasItem(DEFAULT_OCM_COMMENT_IMP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ocmComment").value(hasItem(DEFAULT_OCM_COMMENT)));
    }
    
    @Test
    @Transactional
    public void getOrderComments() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get the orderComments
        restOrderCommentsMockMvc.perform(get("/api/order-comments/{id}", orderComments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderComments.getId().intValue()))
            .andExpect(jsonPath("$.ocmCommentTypeCd").value(DEFAULT_OCM_COMMENT_TYPE_CD))
            .andExpect(jsonPath("$.ocmCommentSubTypeCd").value(DEFAULT_OCM_COMMENT_SUB_TYPE_CD))
            .andExpect(jsonPath("$.ocmCommentBy").value(DEFAULT_OCM_COMMENT_BY))
            .andExpect(jsonPath("$.ocmCommentDate").value(DEFAULT_OCM_COMMENT_DATE.toString()))
            .andExpect(jsonPath("$.ocmCommentImpInd").value(DEFAULT_OCM_COMMENT_IMP_IND.booleanValue()))
            .andExpect(jsonPath("$.ocmComment").value(DEFAULT_OCM_COMMENT));
    }


    @Test
    @Transactional
    public void getOrderCommentsByIdFiltering() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        Long id = orderComments.getId();

        defaultOrderCommentsShouldBeFound("id.equals=" + id);
        defaultOrderCommentsShouldNotBeFound("id.notEquals=" + id);

        defaultOrderCommentsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultOrderCommentsShouldNotBeFound("id.greaterThan=" + id);

        defaultOrderCommentsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultOrderCommentsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd equals to DEFAULT_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.equals=" + DEFAULT_OCM_COMMENT_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentTypeCd equals to UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.equals=" + UPDATED_OCM_COMMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd not equals to DEFAULT_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.notEquals=" + DEFAULT_OCM_COMMENT_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentTypeCd not equals to UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.notEquals=" + UPDATED_OCM_COMMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd in DEFAULT_OCM_COMMENT_TYPE_CD or UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.in=" + DEFAULT_OCM_COMMENT_TYPE_CD + "," + UPDATED_OCM_COMMENT_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentTypeCd equals to UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.in=" + UPDATED_OCM_COMMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd is not null
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.specified=true");

        // Get all the orderCommentsList where ocmCommentTypeCd is null
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd contains DEFAULT_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.contains=" + DEFAULT_OCM_COMMENT_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentTypeCd contains UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.contains=" + UPDATED_OCM_COMMENT_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentTypeCd does not contain DEFAULT_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentTypeCd.doesNotContain=" + DEFAULT_OCM_COMMENT_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentTypeCd does not contain UPDATED_OCM_COMMENT_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentTypeCd.doesNotContain=" + UPDATED_OCM_COMMENT_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd equals to DEFAULT_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.equals=" + DEFAULT_OCM_COMMENT_SUB_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentSubTypeCd equals to UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.equals=" + UPDATED_OCM_COMMENT_SUB_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd not equals to DEFAULT_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.notEquals=" + DEFAULT_OCM_COMMENT_SUB_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentSubTypeCd not equals to UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.notEquals=" + UPDATED_OCM_COMMENT_SUB_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd in DEFAULT_OCM_COMMENT_SUB_TYPE_CD or UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.in=" + DEFAULT_OCM_COMMENT_SUB_TYPE_CD + "," + UPDATED_OCM_COMMENT_SUB_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentSubTypeCd equals to UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.in=" + UPDATED_OCM_COMMENT_SUB_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd is not null
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.specified=true");

        // Get all the orderCommentsList where ocmCommentSubTypeCd is null
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd contains DEFAULT_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.contains=" + DEFAULT_OCM_COMMENT_SUB_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentSubTypeCd contains UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.contains=" + UPDATED_OCM_COMMENT_SUB_TYPE_CD);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentSubTypeCdNotContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentSubTypeCd does not contain DEFAULT_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldNotBeFound("ocmCommentSubTypeCd.doesNotContain=" + DEFAULT_OCM_COMMENT_SUB_TYPE_CD);

        // Get all the orderCommentsList where ocmCommentSubTypeCd does not contain UPDATED_OCM_COMMENT_SUB_TYPE_CD
        defaultOrderCommentsShouldBeFound("ocmCommentSubTypeCd.doesNotContain=" + UPDATED_OCM_COMMENT_SUB_TYPE_CD);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy equals to DEFAULT_OCM_COMMENT_BY
        defaultOrderCommentsShouldBeFound("ocmCommentBy.equals=" + DEFAULT_OCM_COMMENT_BY);

        // Get all the orderCommentsList where ocmCommentBy equals to UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.equals=" + UPDATED_OCM_COMMENT_BY);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy not equals to DEFAULT_OCM_COMMENT_BY
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.notEquals=" + DEFAULT_OCM_COMMENT_BY);

        // Get all the orderCommentsList where ocmCommentBy not equals to UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldBeFound("ocmCommentBy.notEquals=" + UPDATED_OCM_COMMENT_BY);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy in DEFAULT_OCM_COMMENT_BY or UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldBeFound("ocmCommentBy.in=" + DEFAULT_OCM_COMMENT_BY + "," + UPDATED_OCM_COMMENT_BY);

        // Get all the orderCommentsList where ocmCommentBy equals to UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.in=" + UPDATED_OCM_COMMENT_BY);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy is not null
        defaultOrderCommentsShouldBeFound("ocmCommentBy.specified=true");

        // Get all the orderCommentsList where ocmCommentBy is null
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy contains DEFAULT_OCM_COMMENT_BY
        defaultOrderCommentsShouldBeFound("ocmCommentBy.contains=" + DEFAULT_OCM_COMMENT_BY);

        // Get all the orderCommentsList where ocmCommentBy contains UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.contains=" + UPDATED_OCM_COMMENT_BY);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentByNotContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentBy does not contain DEFAULT_OCM_COMMENT_BY
        defaultOrderCommentsShouldNotBeFound("ocmCommentBy.doesNotContain=" + DEFAULT_OCM_COMMENT_BY);

        // Get all the orderCommentsList where ocmCommentBy does not contain UPDATED_OCM_COMMENT_BY
        defaultOrderCommentsShouldBeFound("ocmCommentBy.doesNotContain=" + UPDATED_OCM_COMMENT_BY);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate equals to DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.equals=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate equals to UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.equals=" + UPDATED_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate not equals to DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.notEquals=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate not equals to UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.notEquals=" + UPDATED_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate in DEFAULT_OCM_COMMENT_DATE or UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.in=" + DEFAULT_OCM_COMMENT_DATE + "," + UPDATED_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate equals to UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.in=" + UPDATED_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate is not null
        defaultOrderCommentsShouldBeFound("ocmCommentDate.specified=true");

        // Get all the orderCommentsList where ocmCommentDate is null
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate is greater than or equal to DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.greaterThanOrEqual=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate is greater than or equal to UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.greaterThanOrEqual=" + UPDATED_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate is less than or equal to DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.lessThanOrEqual=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate is less than or equal to SMALLER_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.lessThanOrEqual=" + SMALLER_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate is less than DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.lessThan=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate is less than UPDATED_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.lessThan=" + UPDATED_OCM_COMMENT_DATE);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentDate is greater than DEFAULT_OCM_COMMENT_DATE
        defaultOrderCommentsShouldNotBeFound("ocmCommentDate.greaterThan=" + DEFAULT_OCM_COMMENT_DATE);

        // Get all the orderCommentsList where ocmCommentDate is greater than SMALLER_OCM_COMMENT_DATE
        defaultOrderCommentsShouldBeFound("ocmCommentDate.greaterThan=" + SMALLER_OCM_COMMENT_DATE);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentImpIndIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentImpInd equals to DEFAULT_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldBeFound("ocmCommentImpInd.equals=" + DEFAULT_OCM_COMMENT_IMP_IND);

        // Get all the orderCommentsList where ocmCommentImpInd equals to UPDATED_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldNotBeFound("ocmCommentImpInd.equals=" + UPDATED_OCM_COMMENT_IMP_IND);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentImpIndIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentImpInd not equals to DEFAULT_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldNotBeFound("ocmCommentImpInd.notEquals=" + DEFAULT_OCM_COMMENT_IMP_IND);

        // Get all the orderCommentsList where ocmCommentImpInd not equals to UPDATED_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldBeFound("ocmCommentImpInd.notEquals=" + UPDATED_OCM_COMMENT_IMP_IND);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentImpIndIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentImpInd in DEFAULT_OCM_COMMENT_IMP_IND or UPDATED_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldBeFound("ocmCommentImpInd.in=" + DEFAULT_OCM_COMMENT_IMP_IND + "," + UPDATED_OCM_COMMENT_IMP_IND);

        // Get all the orderCommentsList where ocmCommentImpInd equals to UPDATED_OCM_COMMENT_IMP_IND
        defaultOrderCommentsShouldNotBeFound("ocmCommentImpInd.in=" + UPDATED_OCM_COMMENT_IMP_IND);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentImpIndIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmCommentImpInd is not null
        defaultOrderCommentsShouldBeFound("ocmCommentImpInd.specified=true");

        // Get all the orderCommentsList where ocmCommentImpInd is null
        defaultOrderCommentsShouldNotBeFound("ocmCommentImpInd.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment equals to DEFAULT_OCM_COMMENT
        defaultOrderCommentsShouldBeFound("ocmComment.equals=" + DEFAULT_OCM_COMMENT);

        // Get all the orderCommentsList where ocmComment equals to UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldNotBeFound("ocmComment.equals=" + UPDATED_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment not equals to DEFAULT_OCM_COMMENT
        defaultOrderCommentsShouldNotBeFound("ocmComment.notEquals=" + DEFAULT_OCM_COMMENT);

        // Get all the orderCommentsList where ocmComment not equals to UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldBeFound("ocmComment.notEquals=" + UPDATED_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentIsInShouldWork() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment in DEFAULT_OCM_COMMENT or UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldBeFound("ocmComment.in=" + DEFAULT_OCM_COMMENT + "," + UPDATED_OCM_COMMENT);

        // Get all the orderCommentsList where ocmComment equals to UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldNotBeFound("ocmComment.in=" + UPDATED_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment is not null
        defaultOrderCommentsShouldBeFound("ocmComment.specified=true");

        // Get all the orderCommentsList where ocmComment is null
        defaultOrderCommentsShouldNotBeFound("ocmComment.specified=false");
    }
                @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment contains DEFAULT_OCM_COMMENT
        defaultOrderCommentsShouldBeFound("ocmComment.contains=" + DEFAULT_OCM_COMMENT);

        // Get all the orderCommentsList where ocmComment contains UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldNotBeFound("ocmComment.contains=" + UPDATED_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void getAllOrderCommentsByOcmCommentNotContainsSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);

        // Get all the orderCommentsList where ocmComment does not contain DEFAULT_OCM_COMMENT
        defaultOrderCommentsShouldNotBeFound("ocmComment.doesNotContain=" + DEFAULT_OCM_COMMENT);

        // Get all the orderCommentsList where ocmComment does not contain UPDATED_OCM_COMMENT
        defaultOrderCommentsShouldBeFound("ocmComment.doesNotContain=" + UPDATED_OCM_COMMENT);
    }


    @Test
    @Transactional
    public void getAllOrderCommentsByOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCommentsRepository.saveAndFlush(orderComments);
        Orders order = OrdersResourceIT.createEntity(em);
        em.persist(order);
        em.flush();
        orderComments.setOrder(order);
        orderCommentsRepository.saveAndFlush(orderComments);
        Long orderId = order.getId();

        // Get all the orderCommentsList where order equals to orderId
        defaultOrderCommentsShouldBeFound("orderId.equals=" + orderId);

        // Get all the orderCommentsList where order equals to orderId + 1
        defaultOrderCommentsShouldNotBeFound("orderId.equals=" + (orderId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOrderCommentsShouldBeFound(String filter) throws Exception {
        restOrderCommentsMockMvc.perform(get("/api/order-comments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderComments.getId().intValue())))
            .andExpect(jsonPath("$.[*].ocmCommentTypeCd").value(hasItem(DEFAULT_OCM_COMMENT_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ocmCommentSubTypeCd").value(hasItem(DEFAULT_OCM_COMMENT_SUB_TYPE_CD)))
            .andExpect(jsonPath("$.[*].ocmCommentBy").value(hasItem(DEFAULT_OCM_COMMENT_BY)))
            .andExpect(jsonPath("$.[*].ocmCommentDate").value(hasItem(DEFAULT_OCM_COMMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].ocmCommentImpInd").value(hasItem(DEFAULT_OCM_COMMENT_IMP_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].ocmComment").value(hasItem(DEFAULT_OCM_COMMENT)));

        // Check, that the count call also returns 1
        restOrderCommentsMockMvc.perform(get("/api/order-comments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOrderCommentsShouldNotBeFound(String filter) throws Exception {
        restOrderCommentsMockMvc.perform(get("/api/order-comments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrderCommentsMockMvc.perform(get("/api/order-comments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrderComments() throws Exception {
        // Get the orderComments
        restOrderCommentsMockMvc.perform(get("/api/order-comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderComments() throws Exception {
        // Initialize the database
        orderCommentsService.save(orderComments);

        int databaseSizeBeforeUpdate = orderCommentsRepository.findAll().size();

        // Update the orderComments
        OrderComments updatedOrderComments = orderCommentsRepository.findById(orderComments.getId()).get();
        // Disconnect from session so that the updates on updatedOrderComments are not directly saved in db
        em.detach(updatedOrderComments);
        updatedOrderComments
            .ocmCommentTypeCd(UPDATED_OCM_COMMENT_TYPE_CD)
            .ocmCommentSubTypeCd(UPDATED_OCM_COMMENT_SUB_TYPE_CD)
            .ocmCommentBy(UPDATED_OCM_COMMENT_BY)
            .ocmCommentDate(UPDATED_OCM_COMMENT_DATE)
            .ocmCommentImpInd(UPDATED_OCM_COMMENT_IMP_IND)
            .ocmComment(UPDATED_OCM_COMMENT);

        restOrderCommentsMockMvc.perform(put("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrderComments)))
            .andExpect(status().isOk());

        // Validate the OrderComments in the database
        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeUpdate);
        OrderComments testOrderComments = orderCommentsList.get(orderCommentsList.size() - 1);
        assertThat(testOrderComments.getOcmCommentTypeCd()).isEqualTo(UPDATED_OCM_COMMENT_TYPE_CD);
        assertThat(testOrderComments.getOcmCommentSubTypeCd()).isEqualTo(UPDATED_OCM_COMMENT_SUB_TYPE_CD);
        assertThat(testOrderComments.getOcmCommentBy()).isEqualTo(UPDATED_OCM_COMMENT_BY);
        assertThat(testOrderComments.getOcmCommentDate()).isEqualTo(UPDATED_OCM_COMMENT_DATE);
        assertThat(testOrderComments.isOcmCommentImpInd()).isEqualTo(UPDATED_OCM_COMMENT_IMP_IND);
        assertThat(testOrderComments.getOcmComment()).isEqualTo(UPDATED_OCM_COMMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderComments() throws Exception {
        int databaseSizeBeforeUpdate = orderCommentsRepository.findAll().size();

        // Create the OrderComments

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderCommentsMockMvc.perform(put("/api/order-comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderComments)))
            .andExpect(status().isBadRequest());

        // Validate the OrderComments in the database
        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderComments() throws Exception {
        // Initialize the database
        orderCommentsService.save(orderComments);

        int databaseSizeBeforeDelete = orderCommentsRepository.findAll().size();

        // Delete the orderComments
        restOrderCommentsMockMvc.perform(delete("/api/order-comments/{id}", orderComments.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderComments> orderCommentsList = orderCommentsRepository.findAll();
        assertThat(orderCommentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
