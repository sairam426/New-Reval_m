package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class BranchesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Branches.class);
        Branches branches1 = new Branches();
        branches1.setId(1L);
        Branches branches2 = new Branches();
        branches2.setId(branches1.getId());
        assertThat(branches1).isEqualTo(branches2);
        branches2.setId(2L);
        assertThat(branches1).isNotEqualTo(branches2);
        branches1.setId(null);
        assertThat(branches1).isNotEqualTo(branches2);
    }
}
