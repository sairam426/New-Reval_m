package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class TransactionCodesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionCodes.class);
        TransactionCodes transactionCodes1 = new TransactionCodes();
        transactionCodes1.setId(1L);
        TransactionCodes transactionCodes2 = new TransactionCodes();
        transactionCodes2.setId(transactionCodes1.getId());
        assertThat(transactionCodes1).isEqualTo(transactionCodes2);
        transactionCodes2.setId(2L);
        assertThat(transactionCodes1).isNotEqualTo(transactionCodes2);
        transactionCodes1.setId(null);
        assertThat(transactionCodes1).isNotEqualTo(transactionCodes2);
    }
}
