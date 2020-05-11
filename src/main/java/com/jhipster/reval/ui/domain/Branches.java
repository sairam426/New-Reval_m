package com.jhipster.reval.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Branches.
 */
@Entity
@Table(name = "branches")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Branches implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_nbr", length = 30, nullable = false)
    private String brnNbr;

    @NotNull
    @Size(max = 80)
    @Column(name = "brn_name", length = 80, nullable = false)
    private String brnName;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_code", length = 30, nullable = false)
    private String brnCode;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_short_name", length = 30, nullable = false)
    private String brnShortName;

    @NotNull
    @Size(max = 80)
    @Column(name = "brn_address_1", length = 80, nullable = false)
    private String brnAddress1;

    @NotNull
    @Size(max = 80)
    @Column(name = "brn_address_2", length = 80, nullable = false)
    private String brnAddress2;

    @NotNull
    @Size(max = 80)
    @Column(name = "brn_city", length = 80, nullable = false)
    private String brnCity;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_state_cd", length = 30, nullable = false)
    private String brnStateCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_zip", length = 30, nullable = false)
    private String brnZip;

    @NotNull
    @Column(name = "brn_enabled_ind", nullable = false)
    private Boolean brnEnabledInd;

    @NotNull
    @Size(max = 30)
    @Column(name = "brn_regioin_cd", length = 30, nullable = false)
    private String brnRegioinCd;

    @Size(max = 30)
    @Column(name = "brn_service_type_cd", length = 30)
    private String brnServiceTypeCd;

    @ManyToOne
    @JsonIgnoreProperties("branches")
    private Companies company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrnNbr() {
        return brnNbr;
    }

    public Branches brnNbr(String brnNbr) {
        this.brnNbr = brnNbr;
        return this;
    }

    public void setBrnNbr(String brnNbr) {
        this.brnNbr = brnNbr;
    }

    public String getBrnName() {
        return brnName;
    }

    public Branches brnName(String brnName) {
        this.brnName = brnName;
        return this;
    }

    public void setBrnName(String brnName) {
        this.brnName = brnName;
    }

    public String getBrnCode() {
        return brnCode;
    }

    public Branches brnCode(String brnCode) {
        this.brnCode = brnCode;
        return this;
    }

    public void setBrnCode(String brnCode) {
        this.brnCode = brnCode;
    }

    public String getBrnShortName() {
        return brnShortName;
    }

    public Branches brnShortName(String brnShortName) {
        this.brnShortName = brnShortName;
        return this;
    }

    public void setBrnShortName(String brnShortName) {
        this.brnShortName = brnShortName;
    }

    public String getBrnAddress1() {
        return brnAddress1;
    }

    public Branches brnAddress1(String brnAddress1) {
        this.brnAddress1 = brnAddress1;
        return this;
    }

    public void setBrnAddress1(String brnAddress1) {
        this.brnAddress1 = brnAddress1;
    }

    public String getBrnAddress2() {
        return brnAddress2;
    }

    public Branches brnAddress2(String brnAddress2) {
        this.brnAddress2 = brnAddress2;
        return this;
    }

    public void setBrnAddress2(String brnAddress2) {
        this.brnAddress2 = brnAddress2;
    }

    public String getBrnCity() {
        return brnCity;
    }

    public Branches brnCity(String brnCity) {
        this.brnCity = brnCity;
        return this;
    }

    public void setBrnCity(String brnCity) {
        this.brnCity = brnCity;
    }

    public String getBrnStateCd() {
        return brnStateCd;
    }

    public Branches brnStateCd(String brnStateCd) {
        this.brnStateCd = brnStateCd;
        return this;
    }

    public void setBrnStateCd(String brnStateCd) {
        this.brnStateCd = brnStateCd;
    }

    public String getBrnZip() {
        return brnZip;
    }

    public Branches brnZip(String brnZip) {
        this.brnZip = brnZip;
        return this;
    }

    public void setBrnZip(String brnZip) {
        this.brnZip = brnZip;
    }

    public Boolean isBrnEnabledInd() {
        return brnEnabledInd;
    }

    public Branches brnEnabledInd(Boolean brnEnabledInd) {
        this.brnEnabledInd = brnEnabledInd;
        return this;
    }

    public void setBrnEnabledInd(Boolean brnEnabledInd) {
        this.brnEnabledInd = brnEnabledInd;
    }

    public String getBrnRegioinCd() {
        return brnRegioinCd;
    }

    public Branches brnRegioinCd(String brnRegioinCd) {
        this.brnRegioinCd = brnRegioinCd;
        return this;
    }

    public void setBrnRegioinCd(String brnRegioinCd) {
        this.brnRegioinCd = brnRegioinCd;
    }

    public String getBrnServiceTypeCd() {
        return brnServiceTypeCd;
    }

    public Branches brnServiceTypeCd(String brnServiceTypeCd) {
        this.brnServiceTypeCd = brnServiceTypeCd;
        return this;
    }

    public void setBrnServiceTypeCd(String brnServiceTypeCd) {
        this.brnServiceTypeCd = brnServiceTypeCd;
    }

    public Companies getCompany() {
        return company;
    }

    public Branches company(Companies companies) {
        this.company = companies;
        return this;
    }

    public void setCompany(Companies companies) {
        this.company = companies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branches)) {
            return false;
        }
        return id != null && id.equals(((Branches) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Branches{" +
            "id=" + getId() +
            ", brnNbr='" + getBrnNbr() + "'" +
            ", brnName='" + getBrnName() + "'" +
            ", brnCode='" + getBrnCode() + "'" +
            ", brnShortName='" + getBrnShortName() + "'" +
            ", brnAddress1='" + getBrnAddress1() + "'" +
            ", brnAddress2='" + getBrnAddress2() + "'" +
            ", brnCity='" + getBrnCity() + "'" +
            ", brnStateCd='" + getBrnStateCd() + "'" +
            ", brnZip='" + getBrnZip() + "'" +
            ", brnEnabledInd='" + isBrnEnabledInd() + "'" +
            ", brnRegioinCd='" + getBrnRegioinCd() + "'" +
            ", brnServiceTypeCd='" + getBrnServiceTypeCd() + "'" +
            "}";
    }
}
