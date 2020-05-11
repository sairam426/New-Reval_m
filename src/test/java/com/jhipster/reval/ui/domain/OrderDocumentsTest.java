package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class OrderDocumentsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDocuments.class);
        OrderDocuments orderDocuments1 = new OrderDocuments();
        orderDocuments1.setId(1L);
        OrderDocuments orderDocuments2 = new OrderDocuments();
        orderDocuments2.setId(orderDocuments1.getId());
        assertThat(orderDocuments1).isEqualTo(orderDocuments2);
        orderDocuments2.setId(2L);
        assertThat(orderDocuments1).isNotEqualTo(orderDocuments2);
        orderDocuments1.setId(null);
        assertThat(orderDocuments1).isNotEqualTo(orderDocuments2);
    }
}
