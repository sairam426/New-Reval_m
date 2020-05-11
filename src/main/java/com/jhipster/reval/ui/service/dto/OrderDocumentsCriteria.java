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
 * Criteria class for the {@link com.jhipster.reval.ui.domain.OrderDocuments} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.OrderDocumentsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /order-documents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrderDocumentsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter odoDocumentMimeTypeCd;

    private StringFilter odoDocumentTypeCd;

    private StringFilter odoExternalStorageLink;

    private StringFilter odoExternalStorageFileName;

    private StringFilter odoDocumentDueToCd;

    private StringFilter odoDocumentDueFromCd;

    private LocalDateFilter odoDocumentDueDt;

    private LocalDateFilter odoDocumentRcvdDt;

    private StringFilter odoDocumentStatusCd;

    private LongFilter orderId;

    public OrderDocumentsCriteria() {
    }

    public OrderDocumentsCriteria(OrderDocumentsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.odoDocumentMimeTypeCd = other.odoDocumentMimeTypeCd == null ? null : other.odoDocumentMimeTypeCd.copy();
        this.odoDocumentTypeCd = other.odoDocumentTypeCd == null ? null : other.odoDocumentTypeCd.copy();
        this.odoExternalStorageLink = other.odoExternalStorageLink == null ? null : other.odoExternalStorageLink.copy();
        this.odoExternalStorageFileName = other.odoExternalStorageFileName == null ? null : other.odoExternalStorageFileName.copy();
        this.odoDocumentDueToCd = other.odoDocumentDueToCd == null ? null : other.odoDocumentDueToCd.copy();
        this.odoDocumentDueFromCd = other.odoDocumentDueFromCd == null ? null : other.odoDocumentDueFromCd.copy();
        this.odoDocumentDueDt = other.odoDocumentDueDt == null ? null : other.odoDocumentDueDt.copy();
        this.odoDocumentRcvdDt = other.odoDocumentRcvdDt == null ? null : other.odoDocumentRcvdDt.copy();
        this.odoDocumentStatusCd = other.odoDocumentStatusCd == null ? null : other.odoDocumentStatusCd.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
    }

    @Override
    public OrderDocumentsCriteria copy() {
        return new OrderDocumentsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOdoDocumentMimeTypeCd() {
        return odoDocumentMimeTypeCd;
    }

    public void setOdoDocumentMimeTypeCd(StringFilter odoDocumentMimeTypeCd) {
        this.odoDocumentMimeTypeCd = odoDocumentMimeTypeCd;
    }

    public StringFilter getOdoDocumentTypeCd() {
        return odoDocumentTypeCd;
    }

    public void setOdoDocumentTypeCd(StringFilter odoDocumentTypeCd) {
        this.odoDocumentTypeCd = odoDocumentTypeCd;
    }

    public StringFilter getOdoExternalStorageLink() {
        return odoExternalStorageLink;
    }

    public void setOdoExternalStorageLink(StringFilter odoExternalStorageLink) {
        this.odoExternalStorageLink = odoExternalStorageLink;
    }

    public StringFilter getOdoExternalStorageFileName() {
        return odoExternalStorageFileName;
    }

    public void setOdoExternalStorageFileName(StringFilter odoExternalStorageFileName) {
        this.odoExternalStorageFileName = odoExternalStorageFileName;
    }

    public StringFilter getOdoDocumentDueToCd() {
        return odoDocumentDueToCd;
    }

    public void setOdoDocumentDueToCd(StringFilter odoDocumentDueToCd) {
        this.odoDocumentDueToCd = odoDocumentDueToCd;
    }

    public StringFilter getOdoDocumentDueFromCd() {
        return odoDocumentDueFromCd;
    }

    public void setOdoDocumentDueFromCd(StringFilter odoDocumentDueFromCd) {
        this.odoDocumentDueFromCd = odoDocumentDueFromCd;
    }

    public LocalDateFilter getOdoDocumentDueDt() {
        return odoDocumentDueDt;
    }

    public void setOdoDocumentDueDt(LocalDateFilter odoDocumentDueDt) {
        this.odoDocumentDueDt = odoDocumentDueDt;
    }

    public LocalDateFilter getOdoDocumentRcvdDt() {
        return odoDocumentRcvdDt;
    }

    public void setOdoDocumentRcvdDt(LocalDateFilter odoDocumentRcvdDt) {
        this.odoDocumentRcvdDt = odoDocumentRcvdDt;
    }

    public StringFilter getOdoDocumentStatusCd() {
        return odoDocumentStatusCd;
    }

    public void setOdoDocumentStatusCd(StringFilter odoDocumentStatusCd) {
        this.odoDocumentStatusCd = odoDocumentStatusCd;
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
        final OrderDocumentsCriteria that = (OrderDocumentsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(odoDocumentMimeTypeCd, that.odoDocumentMimeTypeCd) &&
            Objects.equals(odoDocumentTypeCd, that.odoDocumentTypeCd) &&
            Objects.equals(odoExternalStorageLink, that.odoExternalStorageLink) &&
            Objects.equals(odoExternalStorageFileName, that.odoExternalStorageFileName) &&
            Objects.equals(odoDocumentDueToCd, that.odoDocumentDueToCd) &&
            Objects.equals(odoDocumentDueFromCd, that.odoDocumentDueFromCd) &&
            Objects.equals(odoDocumentDueDt, that.odoDocumentDueDt) &&
            Objects.equals(odoDocumentRcvdDt, that.odoDocumentRcvdDt) &&
            Objects.equals(odoDocumentStatusCd, that.odoDocumentStatusCd) &&
            Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        odoDocumentMimeTypeCd,
        odoDocumentTypeCd,
        odoExternalStorageLink,
        odoExternalStorageFileName,
        odoDocumentDueToCd,
        odoDocumentDueFromCd,
        odoDocumentDueDt,
        odoDocumentRcvdDt,
        odoDocumentStatusCd,
        orderId
        );
    }

    @Override
    public String toString() {
        return "OrderDocumentsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (odoDocumentMimeTypeCd != null ? "odoDocumentMimeTypeCd=" + odoDocumentMimeTypeCd + ", " : "") +
                (odoDocumentTypeCd != null ? "odoDocumentTypeCd=" + odoDocumentTypeCd + ", " : "") +
                (odoExternalStorageLink != null ? "odoExternalStorageLink=" + odoExternalStorageLink + ", " : "") +
                (odoExternalStorageFileName != null ? "odoExternalStorageFileName=" + odoExternalStorageFileName + ", " : "") +
                (odoDocumentDueToCd != null ? "odoDocumentDueToCd=" + odoDocumentDueToCd + ", " : "") +
                (odoDocumentDueFromCd != null ? "odoDocumentDueFromCd=" + odoDocumentDueFromCd + ", " : "") +
                (odoDocumentDueDt != null ? "odoDocumentDueDt=" + odoDocumentDueDt + ", " : "") +
                (odoDocumentRcvdDt != null ? "odoDocumentRcvdDt=" + odoDocumentRcvdDt + ", " : "") +
                (odoDocumentStatusCd != null ? "odoDocumentStatusCd=" + odoDocumentStatusCd + ", " : "") +
                (orderId != null ? "orderId=" + orderId + ", " : "") +
            "}";
    }

}
