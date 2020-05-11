package com.jhipster.reval.ui.repository;

import com.jhipster.reval.ui.domain.Providers;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Providers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvidersRepository extends JpaRepository<Providers, Long> {
}
