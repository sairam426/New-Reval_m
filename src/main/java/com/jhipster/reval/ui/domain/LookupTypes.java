package com.jhipster.reval.ui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LookupTypes.
 */
@Entity
@Table(name = "lookup_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LookupTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "lkt_type", length = 30, nullable = false)
    private String lktType;

    @NotNull
    @Size(max = 80)
    @Column(name = "lkt_desc", length = 80, nullable = false)
    private String lktDesc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLktType() {
        return lktType;
    }

    public LookupTypes lktType(String lktType) {
        this.lktType = lktType;
        return this;
    }

    public void setLktType(String lktType) {
        this.lktType = lktType;
    }

    public String getLktDesc() {
        return lktDesc;
    }

    public LookupTypes lktDesc(String lktDesc) {
        this.lktDesc = lktDesc;
        return this;
    }

    public void setLktDesc(String lktDesc) {
        this.lktDesc = lktDesc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LookupTypes)) {
            return false;
        }
        return id != null && id.equals(((LookupTypes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LookupTypes{" +
            "id=" + getId() +
            ", lktType='" + getLktType() + "'" +
            ", lktDesc='" + getLktDesc() + "'" +
            "}";
    }
}
