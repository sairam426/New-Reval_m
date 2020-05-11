package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SystemAccess.
 */
@Entity
@Table(name = "system_access")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SystemAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 240)
    @Column(name = "sac_access_key", length = 240, nullable = false)
    private String sacAccessKey;

    @NotNull
    @Size(max = 30)
    @Column(name = "sac_access_type_cd", length = 30, nullable = false)
    private String sacAccessTypeCd;

    @NotNull
    @Size(max = 80)
    @Column(name = "sac_access_value", length = 80, nullable = false)
    private String sacAccessValue;

    @NotNull
    @Column(name = "sac_allowed_ind", nullable = false)
    private Boolean sacAllowedInd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSacAccessKey() {
        return sacAccessKey;
    }

    public SystemAccess sacAccessKey(String sacAccessKey) {
        this.sacAccessKey = sacAccessKey;
        return this;
    }

    public void setSacAccessKey(String sacAccessKey) {
        this.sacAccessKey = sacAccessKey;
    }

    public String getSacAccessTypeCd() {
        return sacAccessTypeCd;
    }

    public SystemAccess sacAccessTypeCd(String sacAccessTypeCd) {
        this.sacAccessTypeCd = sacAccessTypeCd;
        return this;
    }

    public void setSacAccessTypeCd(String sacAccessTypeCd) {
        this.sacAccessTypeCd = sacAccessTypeCd;
    }

    public String getSacAccessValue() {
        return sacAccessValue;
    }

    public SystemAccess sacAccessValue(String sacAccessValue) {
        this.sacAccessValue = sacAccessValue;
        return this;
    }

    public void setSacAccessValue(String sacAccessValue) {
        this.sacAccessValue = sacAccessValue;
    }

    public Boolean isSacAllowedInd() {
        return sacAllowedInd;
    }

    public SystemAccess sacAllowedInd(Boolean sacAllowedInd) {
        this.sacAllowedInd = sacAllowedInd;
        return this;
    }

    public void setSacAllowedInd(Boolean sacAllowedInd) {
        this.sacAllowedInd = sacAllowedInd;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemAccess)) {
            return false;
        }
        return id != null && id.equals(((SystemAccess) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SystemAccess{" +
            "id=" + getId() +
            ", sacAccessKey='" + getSacAccessKey() + "'" +
            ", sacAccessTypeCd='" + getSacAccessTypeCd() + "'" +
            ", sacAccessValue='" + getSacAccessValue() + "'" +
            ", sacAllowedInd='" + isSacAllowedInd() + "'" +
            "}";
    }
}
