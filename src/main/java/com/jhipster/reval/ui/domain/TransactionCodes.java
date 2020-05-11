package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A TransactionCodes.
 */
@Entity
@Table(name = "transaction_codes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TransactionCodes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tcd_code")
    private String tcdCode;

    @Column(name = "tcd_entity_group_cd")
    private String tcdEntityGroupCd;

    @Column(name = "tcd_desc")
    private String tcdDesc;

    @Column(name = "tcd_enabled_ind")
    private Boolean tcdEnabledInd;

    @OneToMany(mappedBy = "transactionCode")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TransactionCodeParams> txnCodeParams = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTcdCode() {
        return tcdCode;
    }

    public TransactionCodes tcdCode(String tcdCode) {
        this.tcdCode = tcdCode;
        return this;
    }

    public void setTcdCode(String tcdCode) {
        this.tcdCode = tcdCode;
    }

    public String getTcdEntityGroupCd() {
        return tcdEntityGroupCd;
    }

    public TransactionCodes tcdEntityGroupCd(String tcdEntityGroupCd) {
        this.tcdEntityGroupCd = tcdEntityGroupCd;
        return this;
    }

    public void setTcdEntityGroupCd(String tcdEntityGroupCd) {
        this.tcdEntityGroupCd = tcdEntityGroupCd;
    }

    public String getTcdDesc() {
        return tcdDesc;
    }

    public TransactionCodes tcdDesc(String tcdDesc) {
        this.tcdDesc = tcdDesc;
        return this;
    }

    public void setTcdDesc(String tcdDesc) {
        this.tcdDesc = tcdDesc;
    }

    public Boolean isTcdEnabledInd() {
        return tcdEnabledInd;
    }

    public TransactionCodes tcdEnabledInd(Boolean tcdEnabledInd) {
        this.tcdEnabledInd = tcdEnabledInd;
        return this;
    }

    public void setTcdEnabledInd(Boolean tcdEnabledInd) {
        this.tcdEnabledInd = tcdEnabledInd;
    }

    public Set<TransactionCodeParams> getTxnCodeParams() {
        return txnCodeParams;
    }

    public TransactionCodes txnCodeParams(Set<TransactionCodeParams> transactionCodeParams) {
        this.txnCodeParams = transactionCodeParams;
        return this;
    }

    public TransactionCodes addTxnCodeParams(TransactionCodeParams transactionCodeParams) {
        this.txnCodeParams.add(transactionCodeParams);
        transactionCodeParams.setTransactionCode(this);
        return this;
    }

    public TransactionCodes removeTxnCodeParams(TransactionCodeParams transactionCodeParams) {
        this.txnCodeParams.remove(transactionCodeParams);
        transactionCodeParams.setTransactionCode(null);
        return this;
    }

    public void setTxnCodeParams(Set<TransactionCodeParams> transactionCodeParams) {
        this.txnCodeParams = transactionCodeParams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionCodes)) {
            return false;
        }
        return id != null && id.equals(((TransactionCodes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransactionCodes{" +
            "id=" + getId() +
            ", tcdCode='" + getTcdCode() + "'" +
            ", tcdEntityGroupCd='" + getTcdEntityGroupCd() + "'" +
            ", tcdDesc='" + getTcdDesc() + "'" +
            ", tcdEnabledInd='" + isTcdEnabledInd() + "'" +
            "}";
    }
}
