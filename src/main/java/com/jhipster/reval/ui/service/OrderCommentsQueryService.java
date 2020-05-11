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

import com.jhipster.reval.ui.domain.OrderComments;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.OrderCommentsRepository;
import com.jhipster.reval.ui.service.dto.OrderCommentsCriteria;

/**
 * Service for executing complex queries for {@link OrderComments} entities in the database.
 * The main input is a {@link OrderCommentsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderComments} or a {@link Page} of {@link OrderComments} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderCommentsQueryService extends QueryService<OrderComments> {

    private final Logger log = LoggerFactory.getLogger(OrderCommentsQueryService.class);

    private final OrderCommentsRepository orderCommentsRepository;

    public OrderCommentsQueryService(OrderCommentsRepository orderCommentsRepository) {
        this.orderCommentsRepository = orderCommentsRepository;
    }

    /**
     * Return a {@link List} of {@link OrderComments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderComments> findByCriteria(OrderCommentsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrderComments> specification = createSpecification(criteria);
        return orderCommentsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OrderComments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderComments> findByCriteria(OrderCommentsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrderComments> specification = createSpecification(criteria);
        return orderCommentsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderCommentsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrderComments> specification = createSpecification(criteria);
        return orderCommentsRepository.count(specification);
    }

    /**
     * Function to convert {@link OrderCommentsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OrderComments> createSpecification(OrderCommentsCriteria criteria) {
        Specification<OrderComments> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrderComments_.id));
            }
            if (criteria.getOcmCommentTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOcmCommentTypeCd(), OrderComments_.ocmCommentTypeCd));
            }
            if (criteria.getOcmCommentSubTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOcmCommentSubTypeCd(), OrderComments_.ocmCommentSubTypeCd));
            }
            if (criteria.getOcmCommentBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOcmCommentBy(), OrderComments_.ocmCommentBy));
            }
            if (criteria.getOcmCommentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOcmCommentDate(), OrderComments_.ocmCommentDate));
            }
            if (criteria.getOcmCommentImpInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOcmCommentImpInd(), OrderComments_.ocmCommentImpInd));
            }
            if (criteria.getOcmComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOcmComment(), OrderComments_.ocmComment));
            }
            if (criteria.getOrderId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrderId(),
                    root -> root.join(OrderComments_.order, JoinType.LEFT).get(Orders_.id)));
            }
        }
        return specification;
    }
}
