package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Companies.
 */
@Entity
@Table(name = "companies")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_nbr", length = 30, nullable = false)
    private String cmpNbr;

    @NotNull
    @Size(max = 80)
    @Column(name = "cmp_name", length = 80, nullable = false)
    private String cmpName;

    @Size(max = 30)
    @Column(name = "cmp_service_type_cd", length = 30)
    private String cmpServiceTypeCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_short_name", length = 30, nullable = false)
    private String cmpShortName;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_status_cd", length = 30, nullable = false)
    private String cmpStatusCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_type_cd", length = 30, nullable = false)
    private String cmpTypeCd;

    @NotNull
    @Size(max = 80)
    @Column(name = "cmp_address_1", length = 80, nullable = false)
    private String cmpAddress1;

    @NotNull
    @Size(max = 80)
    @Column(name = "cmp_address_2", length = 80, nullable = false)
    private String cmpAddress2;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_city", length = 30, nullable = false)
    private String cmpCity;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_state_cd", length = 30, nullable = false)
    private String cmpStateCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "cmp_zip", length = 30, nullable = false)
    private String cmpZip;

    @NotNull
    @Column(name = "cmp_enabled_ind", nullable = false)
    private Boolean cmpEnabledInd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmpNbr() {
        return cmpNbr;
    }

    public Companies cmpNbr(String cmpNbr) {
        this.cmpNbr = cmpNbr;
        return this;
    }

    public void setCmpNbr(String cmpNbr) {
        this.cmpNbr = cmpNbr;
    }

    public String getCmpName() {
        return cmpName;
    }

    public Companies cmpName(String cmpName) {
        this.cmpName = cmpName;
        return this;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    public String getCmpServiceTypeCd() {
        return cmpServiceTypeCd;
    }

    public Companies cmpServiceTypeCd(String cmpServiceTypeCd) {
        this.cmpServiceTypeCd = cmpServiceTypeCd;
        return this;
    }

    public void setCmpServiceTypeCd(String cmpServiceTypeCd) {
        this.cmpServiceTypeCd = cmpServiceTypeCd;
    }

    public String getCmpShortName() {
        return cmpShortName;
    }

    public Companies cmpShortName(String cmpShortName) {
        this.cmpShortName = cmpShortName;
        return this;
    }

    public void setCmpShortName(String cmpShortName) {
        this.cmpShortName = cmpShortName;
    }

    public String getCmpStatusCd() {
        return cmpStatusCd;
    }

    public Companies cmpStatusCd(String cmpStatusCd) {
        this.cmpStatusCd = cmpStatusCd;
        return this;
    }

    public void setCmpStatusCd(String cmpStatusCd) {
        this.cmpStatusCd = cmpStatusCd;
    }

    public String getCmpTypeCd() {
        return cmpTypeCd;
    }

    public Companies cmpTypeCd(String cmpTypeCd) {
        this.cmpTypeCd = cmpTypeCd;
        return this;
    }

    public void setCmpTypeCd(String cmpTypeCd) {
        this.cmpTypeCd = cmpTypeCd;
    }

    public String getCmpAddress1() {
        return cmpAddress1;
    }

    public Companies cmpAddress1(String cmpAddress1) {
        this.cmpAddress1 = cmpAddress1;
        return this;
    }

    public void setCmpAddress1(String cmpAddress1) {
        this.cmpAddress1 = cmpAddress1;
    }

    public String getCmpAddress2() {
        return cmpAddress2;
    }

    public Companies cmpAddress2(String cmpAddress2) {
        this.cmpAddress2 = cmpAddress2;
        return this;
    }

    public void setCmpAddress2(String cmpAddress2) {
        this.cmpAddress2 = cmpAddress2;
    }

    public String getCmpCity() {
        return cmpCity;
    }

    public Companies cmpCity(String cmpCity) {
        this.cmpCity = cmpCity;
        return this;
    }

    public void setCmpCity(String cmpCity) {
        this.cmpCity = cmpCity;
    }

    public String getCmpStateCd() {
        return cmpStateCd;
    }

    public Companies cmpStateCd(String cmpStateCd) {
        this.cmpStateCd = cmpStateCd;
        return this;
    }

    public void setCmpStateCd(String cmpStateCd) {
        this.cmpStateCd = cmpStateCd;
    }

    public String getCmpZip() {
        return cmpZip;
    }

    public Companies cmpZip(String cmpZip) {
        this.cmpZip = cmpZip;
        return this;
    }

    public void setCmpZip(String cmpZip) {
        this.cmpZip = cmpZip;
    }

    public Boolean isCmpEnabledInd() {
        return cmpEnabledInd;
    }

    public Companies cmpEnabledInd(Boolean cmpEnabledInd) {
        this.cmpEnabledInd = cmpEnabledInd;
        return this;
    }

    public void setCmpEnabledInd(Boolean cmpEnabledInd) {
        this.cmpEnabledInd = cmpEnabledInd;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Companies)) {
            return false;
        }
        return id != null && id.equals(((Companies) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Companies{" +
            "id=" + getId() +
            ", cmpNbr='" + getCmpNbr() + "'" +
            ", cmpName='" + getCmpName() + "'" +
            ", cmpServiceTypeCd='" + getCmpServiceTypeCd() + "'" +
            ", cmpShortName='" + getCmpShortName() + "'" +
            ", cmpStatusCd='" + getCmpStatusCd() + "'" +
            ", cmpTypeCd='" + getCmpTypeCd() + "'" +
            ", cmpAddress1='" + getCmpAddress1() + "'" +
            ", cmpAddress2='" + getCmpAddress2() + "'" +
            ", cmpCity='" + getCmpCity() + "'" +
            ", cmpStateCd='" + getCmpStateCd() + "'" +
            ", cmpZip='" + getCmpZip() + "'" +
            ", cmpEnabledInd='" + isCmpEnabledInd() + "'" +
            "}";
    }
}
