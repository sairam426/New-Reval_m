package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class TransactionCodeParamsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionCodeParams.class);
        TransactionCodeParams transactionCodeParams1 = new TransactionCodeParams();
        transactionCodeParams1.setId(1L);
        TransactionCodeParams transactionCodeParams2 = new TransactionCodeParams();
        transactionCodeParams2.setId(transactionCodeParams1.getId());
        assertThat(transactionCodeParams1).isEqualTo(transactionCodeParams2);
        transactionCodeParams2.setId(2L);
        assertThat(transactionCodeParams1).isNotEqualTo(transactionCodeParams2);
        transactionCodeParams1.setId(null);
        assertThat(transactionCodeParams1).isNotEqualTo(transactionCodeParams2);
    }
}
