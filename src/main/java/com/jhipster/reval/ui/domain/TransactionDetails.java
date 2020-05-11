package com.jhipster.reval.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TransactionDetails.
 */
@Entity
@Table(name = "transaction_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TransactionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "txd_prm_code", length = 30, nullable = false)
    private String txdPrmCode;

    @Size(max = 240)
    @Column(name = "txd_value", length = 240)
    private String txdValue;

    @ManyToOne
    @JsonIgnoreProperties("txnDetails")
    private Transactions transaction;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxdPrmCode() {
        return txdPrmCode;
    }

    public TransactionDetails txdPrmCode(String txdPrmCode) {
        this.txdPrmCode = txdPrmCode;
        return this;
    }

    public void setTxdPrmCode(String txdPrmCode) {
        this.txdPrmCode = txdPrmCode;
    }

    public String getTxdValue() {
        return txdValue;
    }

    public TransactionDetails txdValue(String txdValue) {
        this.txdValue = txdValue;
        return this;
    }

    public void setTxdValue(String txdValue) {
        this.txdValue = txdValue;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public TransactionDetails transaction(Transactions transactions) {
        this.transaction = transactions;
        return this;
    }

    public void setTransaction(Transactions transactions) {
        this.transaction = transactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDetails)) {
            return false;
        }
        return id != null && id.equals(((TransactionDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
            "id=" + getId() +
            ", txdPrmCode='" + getTxdPrmCode() + "'" +
            ", txdValue='" + getTxdValue() + "'" +
            "}";
    }
}
