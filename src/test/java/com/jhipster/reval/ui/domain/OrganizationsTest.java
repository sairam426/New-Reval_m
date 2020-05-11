package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class OrganizationsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organizations.class);
        Organizations organizations1 = new Organizations();
        organizations1.setId(1L);
        Organizations organizations2 = new Organizations();
        organizations2.setId(organizations1.getId());
        assertThat(organizations1).isEqualTo(organizations2);
        organizations2.setId(2L);
        assertThat(organizations1).isNotEqualTo(organizations2);
        organizations1.setId(null);
        assertThat(organizations1).isNotEqualTo(organizations2);
    }
}
