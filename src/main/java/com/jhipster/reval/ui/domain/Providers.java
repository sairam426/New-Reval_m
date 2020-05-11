package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Providers.
 */
@Entity
@Table(name = "providers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Providers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "pro_nbr", length = 30, nullable = false)
    private String proNbr;

    @NotNull
    @Size(max = 30)
    @Column(name = "pro_name", length = 30, nullable = false)
    private String proName;

    @NotNull
    @Size(max = 80)
    @Column(name = "pro_short_name", length = 80, nullable = false)
    private String proShortName;

    @NotNull
    @Size(max = 30)
    @Column(name = "pro_status_cd", length = 30, nullable = false)
    private String proStatusCd;

    @Column(name = "pro_type_cd")
    private String proTypeCd;

    @Column(name = "pro_group_cd")
    private String proGroupCd;

    @Column(name = "pro_license_nbr")
    private String proLicenseNbr;

    @Column(name = "pro_license_status_cd")
    private String proLicenseStatusCd;

    @Column(name = "pro_address_1")
    private String proAddress1;

    @Column(name = "pro_address_2")
    private String proAddress2;

    @Column(name = "pro_city")
    private String proCity;

    @Column(name = "pro_state_cd")
    private String proStateCd;

    @Column(name = "pro_zip")
    private Long proZip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProNbr() {
        return proNbr;
    }

    public Providers proNbr(String proNbr) {
        this.proNbr = proNbr;
        return this;
    }

    public void setProNbr(String proNbr) {
        this.proNbr = proNbr;
    }

    public String getProName() {
        return proName;
    }

    public Providers proName(String proName) {
        this.proName = proName;
        return this;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProShortName() {
        return proShortName;
    }

    public Providers proShortName(String proShortName) {
        this.proShortName = proShortName;
        return this;
    }

    public void setProShortName(String proShortName) {
        this.proShortName = proShortName;
    }

    public String getProStatusCd() {
        return proStatusCd;
    }

    public Providers proStatusCd(String proStatusCd) {
        this.proStatusCd = proStatusCd;
        return this;
    }

    public void setProStatusCd(String proStatusCd) {
        this.proStatusCd = proStatusCd;
    }

    public String getProTypeCd() {
        return proTypeCd;
    }

    public Providers proTypeCd(String proTypeCd) {
        this.proTypeCd = proTypeCd;
        return this;
    }

    public void setProTypeCd(String proTypeCd) {
        this.proTypeCd = proTypeCd;
    }

    public String getProGroupCd() {
        return proGroupCd;
    }

    public Providers proGroupCd(String proGroupCd) {
        this.proGroupCd = proGroupCd;
        return this;
    }

    public void setProGroupCd(String proGroupCd) {
        this.proGroupCd = proGroupCd;
    }

    public String getProLicenseNbr() {
        return proLicenseNbr;
    }

    public Providers proLicenseNbr(String proLicenseNbr) {
        this.proLicenseNbr = proLicenseNbr;
        return this;
    }

    public void setProLicenseNbr(String proLicenseNbr) {
        this.proLicenseNbr = proLicenseNbr;
    }

    public String getProLicenseStatusCd() {
        return proLicenseStatusCd;
    }

    public Providers proLicenseStatusCd(String proLicenseStatusCd) {
        this.proLicenseStatusCd = proLicenseStatusCd;
        return this;
    }

    public void setProLicenseStatusCd(String proLicenseStatusCd) {
        this.proLicenseStatusCd = proLicenseStatusCd;
    }

    public String getProAddress1() {
        return proAddress1;
    }

    public Providers proAddress1(String proAddress1) {
        this.proAddress1 = proAddress1;
        return this;
    }

    public void setProAddress1(String proAddress1) {
        this.proAddress1 = proAddress1;
    }

    public String getProAddress2() {
        return proAddress2;
    }

    public Providers proAddress2(String proAddress2) {
        this.proAddress2 = proAddress2;
        return this;
    }

    public void setProAddress2(String proAddress2) {
        this.proAddress2 = proAddress2;
    }

    public String getProCity() {
        return proCity;
    }

    public Providers proCity(String proCity) {
        this.proCity = proCity;
        return this;
    }

    public void setProCity(String proCity) {
        this.proCity = proCity;
    }

    public String getProStateCd() {
        return proStateCd;
    }

    public Providers proStateCd(String proStateCd) {
        this.proStateCd = proStateCd;
        return this;
    }

    public void setProStateCd(String proStateCd) {
        this.proStateCd = proStateCd;
    }

    public Long getProZip() {
        return proZip;
    }

    public Providers proZip(Long proZip) {
        this.proZip = proZip;
        return this;
    }

    public void setProZip(Long proZip) {
        this.proZip = proZip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Providers)) {
            return false;
        }
        return id != null && id.equals(((Providers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Providers{" +
            "id=" + getId() +
            ", proNbr='" + getProNbr() + "'" +
            ", proName='" + getProName() + "'" +
            ", proShortName='" + getProShortName() + "'" +
            ", proStatusCd='" + getProStatusCd() + "'" +
            ", proTypeCd='" + getProTypeCd() + "'" +
            ", proGroupCd='" + getProGroupCd() + "'" +
            ", proLicenseNbr='" + getProLicenseNbr() + "'" +
            ", proLicenseStatusCd='" + getProLicenseStatusCd() + "'" +
            ", proAddress1='" + getProAddress1() + "'" +
            ", proAddress2='" + getProAddress2() + "'" +
            ", proCity='" + getProCity() + "'" +
            ", proStateCd='" + getProStateCd() + "'" +
            ", proZip=" + getProZip() +
            "}";
    }
}
