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
 * Criteria class for the {@link com.jhipster.reval.ui.domain.SystemAccess} entity. This class is used
 * in {@link com.jhipster.reval.ui.web.rest.SystemAccessResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /system-accesses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SystemAccessCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sacAccessKey;

    private StringFilter sacAccessTypeCd;

    private StringFilter sacAccessValue;

    private BooleanFilter sacAllowedInd;

    public SystemAccessCriteria() {
    }

    public SystemAccessCriteria(SystemAccessCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sacAccessKey = other.sacAccessKey == null ? null : other.sacAccessKey.copy();
        this.sacAccessTypeCd = other.sacAccessTypeCd == null ? null : other.sacAccessTypeCd.copy();
        this.sacAccessValue = other.sacAccessValue == null ? null : other.sacAccessValue.copy();
        this.sacAllowedInd = other.sacAllowedInd == null ? null : other.sacAllowedInd.copy();
    }

    @Override
    public SystemAccessCriteria copy() {
        return new SystemAccessCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSacAccessKey() {
        return sacAccessKey;
    }

    public void setSacAccessKey(StringFilter sacAccessKey) {
        this.sacAccessKey = sacAccessKey;
    }

    public StringFilter getSacAccessTypeCd() {
        return sacAccessTypeCd;
    }

    public void setSacAccessTypeCd(StringFilter sacAccessTypeCd) {
        this.sacAccessTypeCd = sacAccessTypeCd;
    }

    public StringFilter getSacAccessValue() {
        return sacAccessValue;
    }

    public void setSacAccessValue(StringFilter sacAccessValue) {
        this.sacAccessValue = sacAccessValue;
    }

    public BooleanFilter getSacAllowedInd() {
        return sacAllowedInd;
    }

    public void setSacAllowedInd(BooleanFilter sacAllowedInd) {
        this.sacAllowedInd = sacAllowedInd;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SystemAccessCriteria that = (SystemAccessCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sacAccessKey, that.sacAccessKey) &&
            Objects.equals(sacAccessTypeCd, that.sacAccessTypeCd) &&
            Objects.equals(sacAccessValue, that.sacAccessValue) &&
            Objects.equals(sacAllowedInd, that.sacAllowedInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sacAccessKey,
        sacAccessTypeCd,
        sacAccessValue,
        sacAllowedInd
        );
    }

    @Override
    public String toString() {
        return "SystemAccessCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sacAccessKey != null ? "sacAccessKey=" + sacAccessKey + ", " : "") +
                (sacAccessTypeCd != null ? "sacAccessTypeCd=" + sacAccessTypeCd + ", " : "") +
                (sacAccessValue != null ? "sacAccessValue=" + sacAccessValue + ", " : "") +
                (sacAllowedInd != null ? "sacAllowedInd=" + sacAllowedInd + ", " : "") +
            "}";
    }

}
