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

import com.jhipster.reval.ui.domain.TransactionDetails;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.TransactionDetailsRepository;
import com.jhipster.reval.ui.service.dto.TransactionDetailsCriteria;

/**
 * Service for executing complex queries for {@link TransactionDetails} entities in the database.
 * The main input is a {@link TransactionDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransactionDetails} or a {@link Page} of {@link TransactionDetails} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionDetailsQueryService extends QueryService<TransactionDetails> {

    private final Logger log = LoggerFactory.getLogger(TransactionDetailsQueryService.class);

    private final TransactionDetailsRepository transactionDetailsRepository;

    public TransactionDetailsQueryService(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    /**
     * Return a {@link List} of {@link TransactionDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionDetails> findByCriteria(TransactionDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TransactionDetails> specification = createSpecification(criteria);
        return transactionDetailsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TransactionDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionDetails> findByCriteria(TransactionDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TransactionDetails> specification = createSpecification(criteria);
        return transactionDetailsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TransactionDetails> specification = createSpecification(criteria);
        return transactionDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TransactionDetails> createSpecification(TransactionDetailsCriteria criteria) {
        Specification<TransactionDetails> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TransactionDetails_.id));
            }
            if (criteria.getTxdPrmCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTxdPrmCode(), TransactionDetails_.txdPrmCode));
            }
            if (criteria.getTxdValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTxdValue(), TransactionDetails_.txdValue));
            }
            if (criteria.getTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionId(),
                    root -> root.join(TransactionDetails_.transaction, JoinType.LEFT).get(Transactions_.id)));
            }
        }
        return specification;
    }
}
