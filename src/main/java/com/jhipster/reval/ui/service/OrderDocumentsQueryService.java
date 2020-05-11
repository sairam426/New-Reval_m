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

import com.jhipster.reval.ui.domain.OrderDocuments;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.OrderDocumentsRepository;
import com.jhipster.reval.ui.service.dto.OrderDocumentsCriteria;

/**
 * Service for executing complex queries for {@link OrderDocuments} entities in the database.
 * The main input is a {@link OrderDocumentsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderDocuments} or a {@link Page} of {@link OrderDocuments} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderDocumentsQueryService extends QueryService<OrderDocuments> {

    private final Logger log = LoggerFactory.getLogger(OrderDocumentsQueryService.class);

    private final OrderDocumentsRepository orderDocumentsRepository;

    public OrderDocumentsQueryService(OrderDocumentsRepository orderDocumentsRepository) {
        this.orderDocumentsRepository = orderDocumentsRepository;
    }

    /**
     * Return a {@link List} of {@link OrderDocuments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderDocuments> findByCriteria(OrderDocumentsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrderDocuments> specification = createSpecification(criteria);
        return orderDocumentsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OrderDocuments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDocuments> findByCriteria(OrderDocumentsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderDocuments> specification = createSpecification(criteria);
        return orderDocumentsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderDocumentsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrderDocuments> specification = createSpecification(criteria);
        return orderDocumentsRepository.count(specification);
    }

    /**
     * Function to convert {@link OrderDocumentsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OrderDocuments> createSpecification(OrderDocumentsCriteria criteria) {
        Specification<OrderDocuments> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrderDocuments_.id));
            }
            if (criteria.getOdoDocumentMimeTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoDocumentMimeTypeCd(), OrderDocuments_.odoDocumentMimeTypeCd));
            }
            if (criteria.getOdoDocumentTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoDocumentTypeCd(), OrderDocuments_.odoDocumentTypeCd));
            }
            if (criteria.getOdoExternalStorageLink() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoExternalStorageLink(), OrderDocuments_.odoExternalStorageLink));
            }
            if (criteria.getOdoExternalStorageFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoExternalStorageFileName(), OrderDocuments_.odoExternalStorageFileName));
            }
            if (criteria.getOdoDocumentDueToCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoDocumentDueToCd(), OrderDocuments_.odoDocumentDueToCd));
            }
            if (criteria.getOdoDocumentDueFromCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoDocumentDueFromCd(), OrderDocuments_.odoDocumentDueFromCd));
            }
            if (criteria.getOdoDocumentDueDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOdoDocumentDueDt(), OrderDocuments_.odoDocumentDueDt));
            }
            if (criteria.getOdoDocumentRcvdDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOdoDocumentRcvdDt(), OrderDocuments_.odoDocumentRcvdDt));
            }
            if (criteria.getOdoDocumentStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOdoDocumentStatusCd(), OrderDocuments_.odoDocumentStatusCd));
            }
            if (criteria.getOrderId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrderId(),
                    root -> root.join(OrderDocuments_.order, JoinType.LEFT).get(Orders_.id)));
            }
        }
        return specification;
    }
}
