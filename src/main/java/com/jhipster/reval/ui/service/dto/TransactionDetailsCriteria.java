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

/**
 * Criteria class for the {@link com.jhipster.reval.ui.domain.TransactionDetails} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.TransactionDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transaction-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TransactionDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter txdPrmCode;

    private StringFilter txdValue;

    private LongFilter transactionId;

    public TransactionDetailsCriteria() {
    }

    public TransactionDetailsCriteria(TransactionDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.txdPrmCode = other.txdPrmCode == null ? null : other.txdPrmCode.copy();
        this.txdValue = other.txdValue == null ? null : other.txdValue.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
    }

    @Override
    public TransactionDetailsCriteria copy() {
        return new TransactionDetailsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTxdPrmCode() {
        return txdPrmCode;
    }

    public void setTxdPrmCode(StringFilter txdPrmCode) {
        this.txdPrmCode = txdPrmCode;
    }

    public StringFilter getTxdValue() {
        return txdValue;
    }

    public void setTxdValue(StringFilter txdValue) {
        this.txdValue = txdValue;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TransactionDetailsCriteria that = (TransactionDetailsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(txdPrmCode, that.txdPrmCode) &&
            Objects.equals(txdValue, that.txdValue) &&
            Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        txdPrmCode,
        txdValue,
        transactionId
        );
    }

    @Override
    public String toString() {
        return "TransactionDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (txdPrmCode != null ? "txdPrmCode=" + txdPrmCode + ", " : "") +
                (txdValue != null ? "txdValue=" + txdValue + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
            "}";
    }

}
