package com.jhipster.reval.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A OrderComments.
 */
@Entity
@Table(name = "order_comments")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "ocm_comment_type_cd", length = 30, nullable = false)
    private String ocmCommentTypeCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "ocm_comment_sub_type_cd", length = 30, nullable = false)
    private String ocmCommentSubTypeCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "ocm_comment_by", length = 30, nullable = false)
    private String ocmCommentBy;

    @NotNull
    @Column(name = "ocm_comment_date", nullable = false)
    private LocalDate ocmCommentDate;

    @NotNull
    @Column(name = "ocm_comment_imp_ind", nullable = false)
    private Boolean ocmCommentImpInd;

    @NotNull
    @Size(max = 240)
    @Column(name = "ocm_comment", length = 240, nullable = false)
    private String ocmComment;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private Orders order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOcmCommentTypeCd() {
        return ocmCommentTypeCd;
    }

    public OrderComments ocmCommentTypeCd(String ocmCommentTypeCd) {
        this.ocmCommentTypeCd = ocmCommentTypeCd;
        return this;
    }

    public void setOcmCommentTypeCd(String ocmCommentTypeCd) {
        this.ocmCommentTypeCd = ocmCommentTypeCd;
    }

    public String getOcmCommentSubTypeCd() {
        return ocmCommentSubTypeCd;
    }

    public OrderComments ocmCommentSubTypeCd(String ocmCommentSubTypeCd) {
        this.ocmCommentSubTypeCd = ocmCommentSubTypeCd;
        return this;
    }

    public void setOcmCommentSubTypeCd(String ocmCommentSubTypeCd) {
        this.ocmCommentSubTypeCd = ocmCommentSubTypeCd;
    }

    public String getOcmCommentBy() {
        return ocmCommentBy;
    }

    public OrderComments ocmCommentBy(String ocmCommentBy) {
        this.ocmCommentBy = ocmCommentBy;
        return this;
    }

    public void setOcmCommentBy(String ocmCommentBy) {
        this.ocmCommentBy = ocmCommentBy;
    }

    public LocalDate getOcmCommentDate() {
        return ocmCommentDate;
    }

    public OrderComments ocmCommentDate(LocalDate ocmCommentDate) {
        this.ocmCommentDate = ocmCommentDate;
        return this;
    }

    public void setOcmCommentDate(LocalDate ocmCommentDate) {
        this.ocmCommentDate = ocmCommentDate;
    }

    public Boolean isOcmCommentImpInd() {
        return ocmCommentImpInd;
    }

    public OrderComments ocmCommentImpInd(Boolean ocmCommentImpInd) {
        this.ocmCommentImpInd = ocmCommentImpInd;
        return this;
    }

    public void setOcmCommentImpInd(Boolean ocmCommentImpInd) {
        this.ocmCommentImpInd = ocmCommentImpInd;
    }

    public String getOcmComment() {
        return ocmComment;
    }

    public OrderComments ocmComment(String ocmComment) {
        this.ocmComment = ocmComment;
        return this;
    }

    public void setOcmComment(String ocmComment) {
        this.ocmComment = ocmComment;
    }

    public Orders getOrder() {
        return order;
    }

    public OrderComments order(Orders orders) {
        this.order = orders;
        return this;
    }

    public void setOrder(Orders orders) {
        this.order = orders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderComments)) {
            return false;
        }
        return id != null && id.equals(((OrderComments) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderComments{" +
            "id=" + getId() +
            ", ocmCommentTypeCd='" + getOcmCommentTypeCd() + "'" +
            ", ocmCommentSubTypeCd='" + getOcmCommentSubTypeCd() + "'" +
            ", ocmCommentBy='" + getOcmCommentBy() + "'" +
            ", ocmCommentDate='" + getOcmCommentDate() + "'" +
            ", ocmCommentImpInd='" + isOcmCommentImpInd() + "'" +
            ", ocmComment='" + getOcmComment() + "'" +
            "}";
    }
}
