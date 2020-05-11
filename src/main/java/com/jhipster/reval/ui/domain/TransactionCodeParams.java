package com.jhipster.reval.ui.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TransactionCodeParams.
 */
@Entity
@Table(name = "transaction_code_params")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TransactionCodeParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "tcp_tcd_code", length = 30, nullable = false)
    private String tcpTcdCode;

    @NotNull
    @Size(max = 30)
    @Column(name = "tcp_param_name", length = 30, nullable = false)
    private String tcpParamName;

    @NotNull
    @Size(max = 80)
    @Column(name = "tcp_param_desc", length = 80, nullable = false)
    private String tcpParamDesc;

    @NotNull
    @Column(name = "tcp_enabled_ind", nullable = false)
    private Boolean tcpEnabledInd;

    @NotNull
    @Size(max = 30)
    @Column(name = "tcp_param_data_type_cd", length = 30, nullable = false)
    private String tcpParamDataTypeCd;

    @NotNull
    @Size(max = 30)
    @Column(name = "tcp_param_length", length = 30, nullable = false)
    private String tcpParamLength;

    @NotNull
    @Size(max = 240)
    @Column(name = "tcp_default_value", length = 240, nullable = false)
    private String tcpDefaultValue;

    @NotNull
    @Column(name = "tcp_param_lov_validation_ind", nullable = false)
    private Boolean tcpParamLovValidationInd;

    @NotNull
    @Size(max = 30)
    @Column(name = "tcp_param_lkt_type", length = 30, nullable = false)
    private String tcpParamLktType;

    @ManyToOne
    @JsonIgnoreProperties("txnCodeParams")
    private TransactionCodes transactionCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTcpTcdCode() {
        return tcpTcdCode;
    }

    public TransactionCodeParams tcpTcdCode(String tcpTcdCode) {
        this.tcpTcdCode = tcpTcdCode;
        return this;
    }

    public void setTcpTcdCode(String tcpTcdCode) {
        this.tcpTcdCode = tcpTcdCode;
    }

    public String getTcpParamName() {
        return tcpParamName;
    }

    public TransactionCodeParams tcpParamName(String tcpParamName) {
        this.tcpParamName = tcpParamName;
        return this;
    }

    public void setTcpParamName(String tcpParamName) {
        this.tcpParamName = tcpParamName;
    }

    public String getTcpParamDesc() {
        return tcpParamDesc;
    }

    public TransactionCodeParams tcpParamDesc(String tcpParamDesc) {
        this.tcpParamDesc = tcpParamDesc;
        return this;
    }

    public void setTcpParamDesc(String tcpParamDesc) {
        this.tcpParamDesc = tcpParamDesc;
    }

    public Boolean isTcpEnabledInd() {
        return tcpEnabledInd;
    }

    public TransactionCodeParams tcpEnabledInd(Boolean tcpEnabledInd) {
        this.tcpEnabledInd = tcpEnabledInd;
        return this;
    }

    public void setTcpEnabledInd(Boolean tcpEnabledInd) {
        this.tcpEnabledInd = tcpEnabledInd;
    }

    public String getTcpParamDataTypeCd() {
        return tcpParamDataTypeCd;
    }

    public TransactionCodeParams tcpParamDataTypeCd(String tcpParamDataTypeCd) {
        this.tcpParamDataTypeCd = tcpParamDataTypeCd;
        return this;
    }

    public void setTcpParamDataTypeCd(String tcpParamDataTypeCd) {
        this.tcpParamDataTypeCd = tcpParamDataTypeCd;
    }

    public String getTcpParamLength() {
        return tcpParamLength;
    }

    public TransactionCodeParams tcpParamLength(String tcpParamLength) {
        this.tcpParamLength = tcpParamLength;
        return this;
    }

    public void setTcpParamLength(String tcpParamLength) {
        this.tcpParamLength = tcpParamLength;
    }

    public String getTcpDefaultValue() {
        return tcpDefaultValue;
    }

    public TransactionCodeParams tcpDefaultValue(String tcpDefaultValue) {
        this.tcpDefaultValue = tcpDefaultValue;
        return this;
    }

    public void setTcpDefaultValue(String tcpDefaultValue) {
        this.tcpDefaultValue = tcpDefaultValue;
    }

    public Boolean isTcpParamLovValidationInd() {
        return tcpParamLovValidationInd;
    }

    public TransactionCodeParams tcpParamLovValidationInd(Boolean tcpParamLovValidationInd) {
        this.tcpParamLovValidationInd = tcpParamLovValidationInd;
        return this;
    }

    public void setTcpParamLovValidationInd(Boolean tcpParamLovValidationInd) {
        this.tcpParamLovValidationInd = tcpParamLovValidationInd;
    }

    public String getTcpParamLktType() {
        return tcpParamLktType;
    }

    public TransactionCodeParams tcpParamLktType(String tcpParamLktType) {
        this.tcpParamLktType = tcpParamLktType;
        return this;
    }

    public void setTcpParamLktType(String tcpParamLktType) {
        this.tcpParamLktType = tcpParamLktType;
    }

    public TransactionCodes getTransactionCode() {
        return transactionCode;
    }

    public TransactionCodeParams transactionCode(TransactionCodes transactionCodes) {
        this.transactionCode = transactionCodes;
        return this;
    }

    public void setTransactionCode(TransactionCodes transactionCodes) {
        this.transactionCode = transactionCodes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionCodeParams)) {
            return false;
        }
        return id != null && id.equals(((TransactionCodeParams) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransactionCodeParams{" +
            "id=" + getId() +
            ", tcpTcdCode='" + getTcpTcdCode() + "'" +
            ", tcpParamName='" + getTcpParamName() + "'" +
            ", tcpParamDesc='" + getTcpParamDesc() + "'" +
            ", tcpEnabledInd='" + isTcpEnabledInd() + "'" +
            ", tcpParamDataTypeCd='" + getTcpParamDataTypeCd() + "'" +
            ", tcpParamLength='" + getTcpParamLength() + "'" +
            ", tcpDefaultValue='" + getTcpDefaultValue() + "'" +
            ", tcpParamLovValidationInd='" + isTcpParamLovValidationInd() + "'" +
            ", tcpParamLktType='" + getTcpParamLktType() + "'" +
            "}";
    }
}
