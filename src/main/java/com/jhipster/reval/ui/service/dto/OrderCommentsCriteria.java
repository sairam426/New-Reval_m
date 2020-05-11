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
 * Criteria class for the {@link com.jhipster.reval.ui.domain.OrderComments} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.OrderCommentsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /order-comments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderCommentsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ocmCommentTypeCd;

    private StringFilter ocmCommentSubTypeCd;

    private StringFilter ocmCommentBy;

    private LocalDateFilter ocmCommentDate;

    private BooleanFilter ocmCommentImpInd;

    private StringFilter ocmComment;

    private LongFilter orderId;

    public OrderCommentsCriteria() {
    }

    public OrderCommentsCriteria(OrderCommentsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ocmCommentTypeCd = other.ocmCommentTypeCd == null ? null : other.ocmCommentTypeCd.copy();
        this.ocmCommentSubTypeCd = other.ocmCommentSubTypeCd == null ? null : other.ocmCommentSubTypeCd.copy();
        this.ocmCommentBy = other.ocmCommentBy == null ? null : other.ocmCommentBy.copy();
        this.ocmCommentDate = other.ocmCommentDate == null ? null : other.ocmCommentDate.copy();
        this.ocmCommentImpInd = other.ocmCommentImpInd == null ? null : other.ocmCommentImpInd.copy();
        this.ocmComment = other.ocmComment == null ? null : other.ocmComment.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
    }

    @Override
    public OrderCommentsCriteria copy() {
        return new OrderCommentsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOcmCommentTypeCd() {
        return ocmCommentTypeCd;
    }

    public void setOcmCommentTypeCd(StringFilter ocmCommentTypeCd) {
        this.ocmCommentTypeCd = ocmCommentTypeCd;
    }

    public StringFilter getOcmCommentSubTypeCd() {
        return ocmCommentSubTypeCd;
    }

    public void setOcmCommentSubTypeCd(StringFilter ocmCommentSubTypeCd) {
        this.ocmCommentSubTypeCd = ocmCommentSubTypeCd;
    }

    public StringFilter getOcmCommentBy() {
        return ocmCommentBy;
    }

    public void setOcmCommentBy(StringFilter ocmCommentBy) {
        this.ocmCommentBy = ocmCommentBy;
    }

    public LocalDateFilter getOcmCommentDate() {
        return ocmCommentDate;
    }

    public void setOcmCommentDate(LocalDateFilter ocmCommentDate) {
        this.ocmCommentDate = ocmCommentDate;
    }

    public BooleanFilter getOcmCommentImpInd() {
        return ocmCommentImpInd;
    }

    public void setOcmCommentImpInd(BooleanFilter ocmCommentImpInd) {
        this.ocmCommentImpInd = ocmCommentImpInd;
    }

    public StringFilter getOcmComment() {
        return ocmComment;
    }

    public void setOcmComment(StringFilter ocmComment) {
        this.ocmComment = ocmComment;
    }

    public LongFilter getOrderId() {
        return orderId;
    }

    public void setOrderId(LongFilter orderId) {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderCommentsCriteria that = (OrderCommentsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ocmCommentTypeCd, that.ocmCommentTypeCd) &&
            Objects.equals(ocmCommentSubTypeCd, that.ocmCommentSubTypeCd) &&
            Objects.equals(ocmCommentBy, that.ocmCommentBy) &&
            Objects.equals(ocmCommentDate, that.ocmCommentDate) &&
            Objects.equals(ocmCommentImpInd, that.ocmCommentImpInd) &&
            Objects.equals(ocmComment, that.ocmComment) &&
            Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ocmCommentTypeCd,
        ocmCommentSubTypeCd,
        ocmCommentBy,
        ocmCommentDate,
        ocmCommentImpInd,
        ocmComment,
        orderId
        );
    }

    @Override
    public String toString() {
        return "OrderCommentsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ocmCommentTypeCd != null ? "ocmCommentTypeCd=" + ocmCommentTypeCd + ", " : "") +
                (ocmCommentSubTypeCd != null ? "ocmCommentSubTypeCd=" + ocmCommentSubTypeCd + ", " : "") +
                (ocmCommentBy != null ? "ocmCommentBy=" + ocmCommentBy + ", " : "") +
                (ocmCommentDate != null ? "ocmCommentDate=" + ocmCommentDate + ", " : "") +
                (ocmCommentImpInd != null ? "ocmCommentImpInd=" + ocmCommentImpInd + ", " : "") +
                (ocmComment != null ? "ocmComment=" + ocmComment + ", " : "") +
                (orderId != null ? "orderId=" + orderId + ", " : "") +
            "}";
    }

}
