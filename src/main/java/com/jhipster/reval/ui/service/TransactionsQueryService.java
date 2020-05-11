package com.jhipster.reval.ui.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.jhipster.reval.ui.domain.Transactions;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.TransactionsRepository;
import com.jhipster.reval.ui.service.dto.TransactionsCriteria;

/**
 * Service for executing complex queries for {@link Transactions} entities in the database.
 * The main input is a {@link TransactionsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Transactions} or a {@link Page} of {@link Transactions} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionsQueryService extends QueryService<Transactions> {

    private final Logger log = LoggerFactory.getLogger(TransactionsQueryService.class);

    private final TransactionsRepository transactionsRepository;

    public TransactionsQueryService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    /**
     * Return a {@link List} of {@link Transactions} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Transactions> findByCriteria(TransactionsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Transactions> specification = createSpecification(criteria);
        return transactionsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Transactions} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Transactions> findByCriteria(TransactionsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Transactions> specification = createSpecification(criteria);
        return transactionsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Transactions> specification = createSpecification(criteria);
        return transactionsRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Transactions> createSpecification(TransactionsCriteria criteria) {
        Specification<Transactions> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Transactions_.id));
            }
            if (criteria.getTxnEntityId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTxnEntityId(), Transactions_.txnEntityId));
            }
            if (criteria.getTxnEntityNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTxnEntityNbr(), Transactions_.txnEntityNbr));
            }
            if (criteria.getTxnTcdCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTxnTcdCode(), Transactions_.txnTcdCode));
            }
            if (criteria.getTxnPostDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTxnPostDt(), Transactions_.txnPostDt));
            }
            if (criteria.getTxnStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTxnStatusCd(), Transactions_.txnStatusCd));
            }
            if (criteria.getTxnDetailsId() != null) {
                specification = specification.and(buildSpecification(criteria.getTxnDetailsId(),
                    root -> root.join(Transactions_.txnDetails, JoinType.LEFT).get(TransactionDetails_.id)));
            }
        }
        return specification;
    }
}
