package com.jhipster.reval.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Lookups.
 */
@Entity
@Table(name = "lookups")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Lookups implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "lkc_code", length = 30, nullable = false)
    private String lkcCode;

    @NotNull
    @Size(max = 30)
    @Column(name = "lkc_sub_code", length = 30, nullable = false)
    private String lkcSubCode;

    @NotNull
    @Size(max = 30)
    @Column(name = "lkc_sort", length = 30, nullable = false)
    private String lkcSort;

    @NotNull
    @Column(name = "lkc_enabled_ind", nullable = false)
    private Boolean lkcEnabledInd;

    @Size(max = 80)
    @Column(name = "lkc_desc", length = 80)
    private String lkcDesc;

    @ManyToOne
    @JsonIgnoreProperties("lookups")
    private LookupTypes lookUpType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLkcCode() {
        return lkcCode;
    }

    public Lookups lkcCode(String lkcCode) {
        this.lkcCode = lkcCode;
        return this;
    }

    public void setLkcCode(String lkcCode) {
        this.lkcCode = lkcCode;
    }

    public String getLkcSubCode() {
        return lkcSubCode;
    }

    public Lookups lkcSubCode(String lkcSubCode) {
        this.lkcSubCode = lkcSubCode;
        return this;
    }

    public void setLkcSubCode(String lkcSubCode) {
        this.lkcSubCode = lkcSubCode;
    }

    public String getLkcSort() {
        return lkcSort;
    }

    public Lookups lkcSort(String lkcSort) {
        this.lkcSort = lkcSort;
        return this;
    }

    public void setLkcSort(String lkcSort) {
        this.lkcSort = lkcSort;
    }

    public Boolean isLkcEnabledInd() {
        return lkcEnabledInd;
    }

    public Lookups lkcEnabledInd(Boolean lkcEnabledInd) {
        this.lkcEnabledInd = lkcEnabledInd;
        return this;
    }

    public void setLkcEnabledInd(Boolean lkcEnabledInd) {
        this.lkcEnabledInd = lkcEnabledInd;
    }

    public String getLkcDesc() {
        return lkcDesc;
    }

    public Lookups lkcDesc(String lkcDesc) {
        this.lkcDesc = lkcDesc;
        return this;
    }

    public void setLkcDesc(String lkcDesc) {
        this.lkcDesc = lkcDesc;
    }

    public LookupTypes getLookUpType() {
        return lookUpType;
    }

    public Lookups lookUpType(LookupTypes lookupTypes) {
        this.lookUpType = lookupTypes;
        return this;
    }

    public void setLookUpType(LookupTypes lookupTypes) {
        this.lookUpType = lookupTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lookups)) {
            return false;
        }
        return id != null && id.equals(((Lookups) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lookups{" +
            "id=" + getId() +
            ", lkcCode='" + getLkcCode() + "'" +
            ", lkcSubCode='" + getLkcSubCode() + "'" +
            ", lkcSort='" + getLkcSort() + "'" +
            ", lkcEnabledInd='" + isLkcEnabledInd() + "'" +
            ", lkcDesc='" + getLkcDesc() + "'" +
            "}";
    }
}
