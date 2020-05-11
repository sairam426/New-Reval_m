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

import com.jhipster.reval.ui.domain.Lookups;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.LookupsRepository;
import com.jhipster.reval.ui.service.dto.LookupsCriteria;

/**
 * Service for executing complex queries for {@link Lookups} entities in the database.
 * The main input is a {@link LookupsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Lookups} or a {@link Page} of {@link Lookups} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LookupsQueryService extends QueryService<Lookups> {

    private final Logger log = LoggerFactory.getLogger(LookupsQueryService.class);

    private final LookupsRepository lookupsRepository;

    public LookupsQueryService(LookupsRepository lookupsRepository) {
        this.lookupsRepository = lookupsRepository;
    }

    /**
     * Return a {@link List} of {@link Lookups} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Lookups> findByCriteria(LookupsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Lookups> specification = createSpecification(criteria);
        return lookupsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Lookups} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Lookups> findByCriteria(LookupsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Lookups> specification = createSpecification(criteria);
        return lookupsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LookupsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Lookups> specification = createSpecification(criteria);
        return lookupsRepository.count(specification);
    }

    /**
     * Function to convert {@link LookupsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Lookups> createSpecification(LookupsCriteria criteria) {
        Specification<Lookups> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Lookups_.id));
            }
            if (criteria.getLkcCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLkcCode(), Lookups_.lkcCode));
            }
            if (criteria.getLkcSubCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLkcSubCode(), Lookups_.lkcSubCode));
            }
            if (criteria.getLkcSort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLkcSort(), Lookups_.lkcSort));
            }
            if (criteria.getLkcEnabledInd() != null) {
                specification = specification.and(buildSpecification(criteria.getLkcEnabledInd(), Lookups_.lkcEnabledInd));
            }
            if (criteria.getLkcDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLkcDesc(), Lookups_.lkcDesc));
            }
            if (criteria.getLookUpTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getLookUpTypeId(),
                    root -> root.join(Lookups_.lookUpType, JoinType.LEFT).get(LookupTypes_.id)));
            }
        }
        return specification;
    }
}
