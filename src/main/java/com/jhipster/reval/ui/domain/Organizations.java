package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Organizations.
 */
@Entity
@Table(name = "organizations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organizations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "org_nbr", length = 30, nullable = false)
    private String orgNbr;

    @NotNull
    @Size(max = 30)
    @Column(name = "org_name", length = 30, nullable = false)
    private String orgName;

    @NotNull
    @Size(max = 30)
    @Column(name = "org_short_name", length = 30, nullable = false)
    private String orgShortName;

    @Column(name = "org_address_1")
    private String orgAddress1;

    @Column(name = "org_address_2")
    private String orgAddress2;

    @Column(name = "org_city")
    private String orgCity;

    @Column(name = "org_state_cd")
    private String orgStateCd;

    @Column(name = "org_zip")
    private String orgZip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgNbr() {
        return orgNbr;
    }

    public Organizations orgNbr(String orgNbr) {
        this.orgNbr = orgNbr;
        return this;
    }

    public void setOrgNbr(String orgNbr) {
        this.orgNbr = orgNbr;
    }

    public String getOrgName() {
        return orgName;
    }

    public Organizations orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public Organizations orgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
        return this;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgAddress1() {
        return orgAddress1;
    }

    public Organizations orgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
        return this;
    }

    public void setOrgAddress1(String orgAddress1) {
        this.orgAddress1 = orgAddress1;
    }

    public String getOrgAddress2() {
        return orgAddress2;
    }

    public Organizations orgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
        return this;
    }

    public void setOrgAddress2(String orgAddress2) {
        this.orgAddress2 = orgAddress2;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public Organizations orgCity(String orgCity) {
        this.orgCity = orgCity;
        return this;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgStateCd() {
        return orgStateCd;
    }

    public Organizations orgStateCd(String orgStateCd) {
        this.orgStateCd = orgStateCd;
        return this;
    }

    public void setOrgStateCd(String orgStateCd) {
        this.orgStateCd = orgStateCd;
    }

    public String getOrgZip() {
        return orgZip;
    }

    public Organizations orgZip(String orgZip) {
        this.orgZip = orgZip;
        return this;
    }

    public void setOrgZip(String orgZip) {
        this.orgZip = orgZip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organizations)) {
            return false;
        }
        return id != null && id.equals(((Organizations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organizations{" +
            "id=" + getId() +
            ", orgNbr='" + getOrgNbr() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgShortName='" + getOrgShortName() + "'" +
            ", orgAddress1='" + getOrgAddress1() + "'" +
            ", orgAddress2='" + getOrgAddress2() + "'" +
            ", orgCity='" + getOrgCity() + "'" +
            ", orgStateCd='" + getOrgStateCd() + "'" +
            ", orgZip='" + getOrgZip() + "'" +
            "}";
    }
}
