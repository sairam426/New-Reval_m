package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Transactions.
 */
@Entity
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "txn_entity_id", nullable = false)
    private Long txnEntityId;

    @NotNull
    @Column(name = "txn_entity_nbr", nullable = false)
    private String txnEntityNbr;

    @NotNull
    @Column(name = "txn_tcd_code", nullable = false)
    private String txnTcdCode;

    @NotNull
    @Column(name = "txn_post_dt", nullable = false)
    private LocalDate txnPostDt;

    @NotNull
    @Size(max = 30)
    @Column(name = "txn_status_cd", length = 30, nullable = false)
    private String txnStatusCd;

    @OneToMany(mappedBy = "transaction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TransactionDetails> txnDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTxnEntityId() {
        return txnEntityId;
    }

    public Transactions txnEntityId(Long txnEntityId) {
        this.txnEntityId = txnEntityId;
        return this;
    }

    public void setTxnEntityId(Long txnEntityId) {
        this.txnEntityId = txnEntityId;
    }

    public String getTxnEntityNbr() {
        return txnEntityNbr;
    }

    public Transactions txnEntityNbr(String txnEntityNbr) {
        this.txnEntityNbr = txnEntityNbr;
        return this;
    }

    public void setTxnEntityNbr(String txnEntityNbr) {
        this.txnEntityNbr = txnEntityNbr;
    }

    public String getTxnTcdCode() {
        return txnTcdCode;
    }

    public Transactions txnTcdCode(String txnTcdCode) {
        this.txnTcdCode = txnTcdCode;
        return this;
    }

    public void setTxnTcdCode(String txnTcdCode) {
        this.txnTcdCode = txnTcdCode;
    }

    public LocalDate getTxnPostDt() {
        return txnPostDt;
    }

    public Transactions txnPostDt(LocalDate txnPostDt) {
        this.txnPostDt = txnPostDt;
        return this;
    }

    public void setTxnPostDt(LocalDate txnPostDt) {
        this.txnPostDt = txnPostDt;
    }

    public String getTxnStatusCd() {
        return txnStatusCd;
    }

    public Transactions txnStatusCd(String txnStatusCd) {
        this.txnStatusCd = txnStatusCd;
        return this;
    }

    public void setTxnStatusCd(String txnStatusCd) {
        this.txnStatusCd = txnStatusCd;
    }

    public Set<TransactionDetails> getTxnDetails() {
        return txnDetails;
    }

    public Transactions txnDetails(Set<TransactionDetails> transactionDetails) {
        this.txnDetails = transactionDetails;
        return this;
    }

    public Transactions addTxnDetails(TransactionDetails transactionDetails) {
        this.txnDetails.add(transactionDetails);
        transactionDetails.setTransaction(this);
        return this;
    }

    public Transactions removeTxnDetails(TransactionDetails transactionDetails) {
        this.txnDetails.remove(transactionDetails);
        transactionDetails.setTransaction(null);
        return this;
    }

    public void setTxnDetails(Set<TransactionDetails> transactionDetails) {
        this.txnDetails = transactionDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transactions)) {
            return false;
        }
        return id != null && id.equals(((Transactions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + getId() +
            ", txnEntityId=" + getTxnEntityId() +
            ", txnEntityNbr='" + getTxnEntityNbr() + "'" +
            ", txnTcdCode='" + getTxnTcdCode() + "'" +
            ", txnPostDt='" + getTxnPostDt() + "'" +
            ", txnStatusCd='" + getTxnStatusCd() + "'" +
            "}";
    }
}
