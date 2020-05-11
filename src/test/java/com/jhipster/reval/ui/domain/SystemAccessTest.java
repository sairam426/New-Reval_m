package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class SystemAccessTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemAccess.class);
        SystemAccess systemAccess1 = new SystemAccess();
        systemAccess1.setId(1L);
        SystemAccess systemAccess2 = new SystemAccess();
        systemAccess2.setId(systemAccess1.getId());
        assertThat(systemAccess1).isEqualTo(systemAccess2);
        systemAccess2.setId(2L);
        assertThat(systemAccess1).isNotEqualTo(systemAccess2);
        systemAccess1.setId(null);
        assertThat(systemAccess1).isNotEqualTo(systemAccess2);
    }
}
