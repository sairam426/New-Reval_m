package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class CompaniesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Companies.class);
        Companies companies1 = new Companies();
        companies1.setId(1L);
        Companies companies2 = new Companies();
        companies2.setId(companies1.getId());
        assertThat(companies1).isEqualTo(companies2);
        companies2.setId(2L);
        assertThat(companies1).isNotEqualTo(companies2);
        companies1.setId(null);
        assertThat(companies1).isNotEqualTo(companies2);
    }
}
