package com.jhipster.reval.ui.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.reval.ui.web.rest.TestUtil;

public class OrderCommentsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderComments.class);
        OrderComments orderComments1 = new OrderComments();
        orderComments1.setId(1L);
        OrderComments orderComments2 = new OrderComments();
        orderComments2.setId(orderComments1.getId());
        assertThat(orderComments1).isEqualTo(orderComments2);
        orderComments2.setId(2L);
        assertThat(orderComments1).isNotEqualTo(orderComments2);
        orderComments1.setId(null);
        assertThat(orderComments1).isNotEqualTo(orderComments2);
    }
}
