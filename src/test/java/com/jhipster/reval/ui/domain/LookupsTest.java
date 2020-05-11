package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class LookupsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lookups.class);
        Lookups lookups1 = new Lookups();
        lookups1.setId(1L);
        Lookups lookups2 = new Lookups();
        lookups2.setId(lookups1.getId());
        assertThat(lookups1).isEqualTo(lookups2);
        lookups2.setId(2L);
        assertThat(lookups1).isNotEqualTo(lookups2);
        lookups1.setId(null);
        assertThat(lookups1).isNotEqualTo(lookups2);
    }
}
