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
 * A OrderDocuments.
 */
@Entity
@Table(name = "order_documents")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "odo_document_mime_type_cd", length = 30, nullable = false)
    private String odoDocumentMimeTypeCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "odo_document_type_cd", length = 30, nullable = false)
    private String odoDocumentTypeCd;

    @Size(max = 240)
    @Column(name = "odo_external_storage_link", length = 240)
    private String odoExternalStorageLink;

    @Size(max = 240)
    @Column(name = "odo_external_storage_file_name", length = 240)
    private String odoExternalStorageFileName;

    @Column(name = "odo_document_due_to_cd")
    private String odoDocumentDueToCd;

    @Column(name = "odo_document_due_from_cd")
    private String odoDocumentDueFromCd;

    @Column(name = "odo_document_due_dt")
    private LocalDate odoDocumentDueDt;

    @Column(name = "odo_document_rcvd_dt")
    private LocalDate odoDocumentRcvdDt;

    @NotNull
    @Size(max = 30)
    @Column(name = "odo_document_status_cd", length = 30, nullable = false)
    private String odoDocumentStatusCd;

    @Lob
    @Column(name = "odo_document")
    private byte[] odoDocument;

    @Column(name = "odo_document_content_type")
    private String odoDocumentContentType;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Orders order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOdoDocumentMimeTypeCd() {
        return odoDocumentMimeTypeCd;
    }

    public OrderDocuments odoDocumentMimeTypeCd(String odoDocumentMimeTypeCd) {
        this.odoDocumentMimeTypeCd = odoDocumentMimeTypeCd;
        return this;
    }

    public void setOdoDocumentMimeTypeCd(String odoDocumentMimeTypeCd) {
        this.odoDocumentMimeTypeCd = odoDocumentMimeTypeCd;
    }

    public String getOdoDocumentTypeCd() {
        return odoDocumentTypeCd;
    }

    public OrderDocuments odoDocumentTypeCd(String odoDocumentTypeCd) {
        this.odoDocumentTypeCd = odoDocumentTypeCd;
        return this;
    }

    public void setOdoDocumentTypeCd(String odoDocumentTypeCd) {
        this.odoDocumentTypeCd = odoDocumentTypeCd;
    }

    public String getOdoExternalStorageLink() {
        return odoExternalStorageLink;
    }

    public OrderDocuments odoExternalStorageLink(String odoExternalStorageLink) {
        this.odoExternalStorageLink = odoExternalStorageLink;
        return this;
    }

    public void setOdoExternalStorageLink(String odoExternalStorageLink) {
        this.odoExternalStorageLink = odoExternalStorageLink;
    }

    public String getOdoExternalStorageFileName() {
        return odoExternalStorageFileName;
    }

    public OrderDocuments odoExternalStorageFileName(String odoExternalStorageFileName) {
        this.odoExternalStorageFileName = odoExternalStorageFileName;
        return this;
    }

    public void setOdoExternalStorageFileName(String odoExternalStorageFileName) {
        this.odoExternalStorageFileName = odoExternalStorageFileName;
    }

    public String getOdoDocumentDueToCd() {
        return odoDocumentDueToCd;
    }

    public OrderDocuments odoDocumentDueToCd(String odoDocumentDueToCd) {
        this.odoDocumentDueToCd = odoDocumentDueToCd;
        return this;
    }

    public void setOdoDocumentDueToCd(String odoDocumentDueToCd) {
        this.odoDocumentDueToCd = odoDocumentDueToCd;
    }

    public String getOdoDocumentDueFromCd() {
        return odoDocumentDueFromCd;
    }

    public OrderDocuments odoDocumentDueFromCd(String odoDocumentDueFromCd) {
        this.odoDocumentDueFromCd = odoDocumentDueFromCd;
        return this;
    }

    public void setOdoDocumentDueFromCd(String odoDocumentDueFromCd) {
        this.odoDocumentDueFromCd = odoDocumentDueFromCd;
    }

    public LocalDate getOdoDocumentDueDt() {
        return odoDocumentDueDt;
    }

    public OrderDocuments odoDocumentDueDt(LocalDate odoDocumentDueDt) {
        this.odoDocumentDueDt = odoDocumentDueDt;
        return this;
    }

    public void setOdoDocumentDueDt(LocalDate odoDocumentDueDt) {
        this.odoDocumentDueDt = odoDocumentDueDt;
    }

    public LocalDate getOdoDocumentRcvdDt() {
        return odoDocumentRcvdDt;
    }

    public OrderDocuments odoDocumentRcvdDt(LocalDate odoDocumentRcvdDt) {
        this.odoDocumentRcvdDt = odoDocumentRcvdDt;
        return this;
    }

    public void setOdoDocumentRcvdDt(LocalDate odoDocumentRcvdDt) {
        this.odoDocumentRcvdDt = odoDocumentRcvdDt;
    }

    public String getOdoDocumentStatusCd() {
        return odoDocumentStatusCd;
    }

    public OrderDocuments odoDocumentStatusCd(String odoDocumentStatusCd) {
        this.odoDocumentStatusCd = odoDocumentStatusCd;
        return this;
    }

    public void setOdoDocumentStatusCd(String odoDocumentStatusCd) {
        this.odoDocumentStatusCd = odoDocumentStatusCd;
    }

    public byte[] getOdoDocument() {
        return odoDocument;
    }

    public OrderDocuments odoDocument(byte[] odoDocument) {
        this.odoDocument = odoDocument;
        return this;
    }

    public void setOdoDocument(byte[] odoDocument) {
        this.odoDocument = odoDocument;
    }

    public String getOdoDocumentContentType() {
        return odoDocumentContentType;
    }

    public OrderDocuments odoDocumentContentType(String odoDocumentContentType) {
        this.odoDocumentContentType = odoDocumentContentType;
        return this;
    }

    public void setOdoDocumentContentType(String odoDocumentContentType) {
        this.odoDocumentContentType = odoDocumentContentType;
    }

    public Orders getOrder() {
        return order;
    }

    public OrderDocuments order(Orders orders) {
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
        if (!(o instanceof OrderDocuments)) {
            return false;
        }
        return id != null && id.equals(((OrderDocuments) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderDocuments{" +
            "id=" + getId() +
            ", odoDocumentMimeTypeCd='" + getOdoDocumentMimeTypeCd() + "'" +
            ", odoDocumentTypeCd='" + getOdoDocumentTypeCd() + "'" +
            ", odoExternalStorageLink='" + getOdoExternalStorageLink() + "'" +
            ", odoExternalStorageFileName='" + getOdoExternalStorageFileName() + "'" +
            ", odoDocumentDueToCd='" + getOdoDocumentDueToCd() + "'" +
            ", odoDocumentDueFromCd='" + getOdoDocumentDueFromCd() + "'" +
            ", odoDocumentDueDt='" + getOdoDocumentDueDt() + "'" +
            ", odoDocumentRcvdDt='" + getOdoDocumentRcvdDt() + "'" +
            ", odoDocumentStatusCd='" + getOdoDocumentStatusCd() + "'" +
            ", odoDocument='" + getOdoDocument() + "'" +
            ", odoDocumentContentType='" + getOdoDocumentContentType() + "'" +
            "}";
    }
}
