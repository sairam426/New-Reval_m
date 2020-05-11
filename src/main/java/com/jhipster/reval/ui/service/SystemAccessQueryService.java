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

import com.jhipster.reval.ui.domain.SystemAccess;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.SystemAccessRepository;
import com.jhipster.reval.ui.service.dto.SystemAccessCriteria;

/**
 * Service for executing complex queries for {@link SystemAccess} entities in the database.
 * The main input is a {@link SystemAccessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SystemAccess} or a {@link Page} of {@link SystemAccess} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SystemAccessQueryService extends QueryService<SystemAccess> {

    private final Logger log = LoggerFactory.getLogger(SystemAccessQueryService.class);

    private final SystemAccessRepository systemAccessRepository;

    public SystemAccessQueryService(SystemAccessRepository systemAccessRepository) {
        this.systemAccessRepository = systemAccessRepository;
    }

    /**
     * Return a {@link List} of {@link SystemAccess} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SystemAccess> findByCriteria(SystemAccessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SystemAccess> specification = createSpecification(criteria);
        return systemAccessRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link SystemAccess} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SystemAccess> findByCriteria(SystemAccessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SystemAccess> specification = createSpecification(criteria);
        return systemAccessRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SystemAccessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SystemAccess> specification = createSpecification(criteria);
        return systemAccessRepository.count(specification);
    }

    /**
     * Function to convert {@link SystemAccessCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SystemAccess> createSpecification(SystemAccessCriteria criteria) {
        Specification<SystemAccess> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SystemAccess_.id));
            }
            if (criteria.getSacAccessKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSacAccessKey(), SystemAccess_.sacAccessKey));
            }
            if (criteria.getSacAccessTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSacAccessTypeCd(), SystemAccess_.sacAccessTypeCd));
            }
            if (criteria.getSacAccessValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSacAccessValue(), SystemAccess_.sacAccessValue));
            }
            if (criteria.getSacAllowedInd() != null) {
                specification = specification.and(buildSpecification(criteria.getSacAllowedInd(), SystemAccess_.sacAllowedInd));
            }
        }
        return specification;
    }
}
