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
 * Criteria class for the {@link com.jhipster.reval.ui.domain.Lookups} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.LookupsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lookups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LookupsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter lkcCode;

    private StringFilter lkcSubCode;

    private StringFilter lkcSort;

    private BooleanFilter lkcEnabledInd;

    private StringFilter lkcDesc;

    private LongFilter lookUpTypeId;

    public LookupsCriteria() {
    }

    public LookupsCriteria(LookupsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.lkcCode = other.lkcCode == null ? null : other.lkcCode.copy();
        this.lkcSubCode = other.lkcSubCode == null ? null : other.lkcSubCode.copy();
        this.lkcSort = other.lkcSort == null ? null : other.lkcSort.copy();
        this.lkcEnabledInd = other.lkcEnabledInd == null ? null : other.lkcEnabledInd.copy();
        this.lkcDesc = other.lkcDesc == null ? null : other.lkcDesc.copy();
        this.lookUpTypeId = other.lookUpTypeId == null ? null : other.lookUpTypeId.copy();
    }

    @Override
    public LookupsCriteria copy() {
        return new LookupsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLkcCode() {
        return lkcCode;
    }

    public void setLkcCode(StringFilter lkcCode) {
        this.lkcCode = lkcCode;
    }

    public StringFilter getLkcSubCode() {
        return lkcSubCode;
    }

    public void setLkcSubCode(StringFilter lkcSubCode) {
        this.lkcSubCode = lkcSubCode;
    }

    public StringFilter getLkcSort() {
        return lkcSort;
    }

    public void setLkcSort(StringFilter lkcSort) {
        this.lkcSort = lkcSort;
    }

    public BooleanFilter getLkcEnabledInd() {
        return lkcEnabledInd;
    }

    public void setLkcEnabledInd(BooleanFilter lkcEnabledInd) {
        this.lkcEnabledInd = lkcEnabledInd;
    }

    public StringFilter getLkcDesc() {
        return lkcDesc;
    }

    public void setLkcDesc(StringFilter lkcDesc) {
        this.lkcDesc = lkcDesc;
    }

    public LongFilter getLookUpTypeId() {
        return lookUpTypeId;
    }

    public void setLookUpTypeId(LongFilter lookUpTypeId) {
        this.lookUpTypeId = lookUpTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LookupsCriteria that = (LookupsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(lkcCode, that.lkcCode) &&
            Objects.equals(lkcSubCode, that.lkcSubCode) &&
            Objects.equals(lkcSort, that.lkcSort) &&
            Objects.equals(lkcEnabledInd, that.lkcEnabledInd) &&
            Objects.equals(lkcDesc, that.lkcDesc) &&
            Objects.equals(lookUpTypeId, that.lookUpTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        lkcCode,
        lkcSubCode,
        lkcSort,
        lkcEnabledInd,
        lkcDesc,
        lookUpTypeId
        );
    }

    @Override
    public String toString() {
        return "LookupsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (lkcCode != null ? "lkcCode=" + lkcCode + ", " : "") +
                (lkcSubCode != null ? "lkcSubCode=" + lkcSubCode + ", " : "") +
                (lkcSort != null ? "lkcSort=" + lkcSort + ", " : "") +
                (lkcEnabledInd != null ? "lkcEnabledInd=" + lkcEnabledInd + ", " : "") +
                (lkcDesc != null ? "lkcDesc=" + lkcDesc + ", " : "") +
                (lookUpTypeId != null ? "lookUpTypeId=" + lookUpTypeId + ", " : "") +
            "}";
    }

}
