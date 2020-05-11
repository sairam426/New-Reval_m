package com.jhipster.reval.ui.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.jhipster.reval.ui.domain.Transactions} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.TransactionsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TransactionsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter txnEntityId;

    private StringFilter txnEntityNbr;

    private StringFilter txnTcdCode;

    private LocalDateFilter txnPostDt;

    private StringFilter txnStatusCd;

    private LongFilter txnDetailsId;

    public TransactionsCriteria() {
    }

    public TransactionsCriteria(TransactionsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.txnEntityId = other.txnEntityId == null ? null : other.txnEntityId.copy();
        this.txnEntityNbr = other.txnEntityNbr == null ? null : other.txnEntityNbr.copy();
        this.txnTcdCode = other.txnTcdCode == null ? null : other.txnTcdCode.copy();
        this.txnPostDt = other.txnPostDt == null ? null : other.txnPostDt.copy();
        this.txnStatusCd = other.txnStatusCd == null ? null : other.txnStatusCd.copy();
        this.txnDetailsId = other.txnDetailsId == null ? null : other.txnDetailsId.copy();
    }

    @Override
    public TransactionsCriteria copy() {
        return new TransactionsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getTxnEntityId() {
        return txnEntityId;
    }

    public void setTxnEntityId(LongFilter txnEntityId) {
        this.txnEntityId = txnEntityId;
    }

    public StringFilter getTxnEntityNbr() {
        return txnEntityNbr;
    }

    public void setTxnEntityNbr(StringFilter txnEntityNbr) {
        this.txnEntityNbr = txnEntityNbr;
    }

    public StringFilter getTxnTcdCode() {
        return txnTcdCode;
    }

    public void setTxnTcdCode(StringFilter txnTcdCode) {
        this.txnTcdCode = txnTcdCode;
    }

    public LocalDateFilter getTxnPostDt() {
        return txnPostDt;
    }

    public void setTxnPostDt(LocalDateFilter txnPostDt) {
        this.txnPostDt = txnPostDt;
    }

    public StringFilter getTxnStatusCd() {
        return txnStatusCd;
    }

    public void setTxnStatusCd(StringFilter txnStatusCd) {
        this.txnStatusCd = txnStatusCd;
    }

    public LongFilter getTxnDetailsId() {
        return txnDetailsId;
    }

    public void setTxnDetailsId(LongFilter txnDetailsId) {
        this.txnDetailsId = txnDetailsId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TransactionsCriteria that = (TransactionsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(txnEntityId, that.txnEntityId) &&
            Objects.equals(txnEntityNbr, that.txnEntityNbr) &&
            Objects.equals(txnTcdCode, that.txnTcdCode) &&
            Objects.equals(txnPostDt, that.txnPostDt) &&
            Objects.equals(txnStatusCd, that.txnStatusCd) &&
            Objects.equals(txnDetailsId, that.txnDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        txnEntityId,
        txnEntityNbr,
        txnTcdCode,
        txnPostDt,
        txnStatusCd,
        txnDetailsId
        );
    }

    @Override
    public String toString() {
        return "TransactionsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (txnEntityId != null ? "txnEntityId=" + txnEntityId + ", " : "") +
                (txnEntityNbr != null ? "txnEntityNbr=" + txnEntityNbr + ", " : "") +
                (txnTcdCode != null ? "txnTcdCode=" + txnTcdCode + ", " : "") +
                (txnPostDt != null ? "txnPostDt=" + txnPostDt + ", " : "") +
                (txnStatusCd != null ? "txnStatusCd=" + txnStatusCd + ", " : "") +
                (txnDetailsId != null ? "txnDetailsId=" + txnDetailsId + ", " : "") +
            "}";
    }

}
