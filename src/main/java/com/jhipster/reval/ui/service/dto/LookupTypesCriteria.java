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
 * Criteria class for the {@link com.jhipster.reval.ui.domain.LookupTypes} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.LookupTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lookup-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LookupTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter lktType;

    private StringFilter lktDesc;

    public LookupTypesCriteria() {
    }

    public LookupTypesCriteria(LookupTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.lktType = other.lktType == null ? null : other.lktType.copy();
        this.lktDesc = other.lktDesc == null ? null : other.lktDesc.copy();
    }

    @Override
    public LookupTypesCriteria copy() {
        return new LookupTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLktType() {
        return lktType;
    }

    public void setLktType(StringFilter lktType) {
        this.lktType = lktType;
    }

    public StringFilter getLktDesc() {
        return lktDesc;
    }

    public void setLktDesc(StringFilter lktDesc) {
        this.lktDesc = lktDesc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LookupTypesCriteria that = (LookupTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(lktType, that.lktType) &&
            Objects.equals(lktDesc, that.lktDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        lktType,
        lktDesc
        );
    }

    @Override
    public String toString() {
        return "LookupTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (lktType != null ? "lktType=" + lktType + ", " : "") +
                (lktDesc != null ? "lktDesc=" + lktDesc + ", " : "") +
            "}";
    }

}
