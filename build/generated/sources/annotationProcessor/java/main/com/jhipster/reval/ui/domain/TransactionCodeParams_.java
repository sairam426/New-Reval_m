package com.jhipster.reval.ui.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionCodeParams.class)
public abstract class TransactionCodeParams_ {

	public static volatile SingularAttribute<TransactionCodeParams, String> tcpParamName;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpDefaultValue;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpParamLength;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpParamDataTypeCd;
	public static volatile SingularAttribute<TransactionCodeParams, Long> id;
	public static volatile SingularAttribute<TransactionCodeParams, Boolean> tcpParamLovValidationInd;
	public static volatile SingularAttribute<TransactionCodeParams, TransactionCodes> transactionCode;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpParamDesc;
	public static volatile SingularAttribute<TransactionCodeParams, Boolean> tcpEnabledInd;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpTcdCode;
	public static volatile SingularAttribute<TransactionCodeParams, String> tcpParamLktType;

	public static final String TCP_PARAM_NAME = "tcpParamName";
	public static final String TCP_DEFAULT_VALUE = "tcpDefaultValue";
	public static final String TCP_PARAM_LENGTH = "tcpParamLength";
	public static final String TCP_PARAM_DATA_TYPE_CD = "tcpParamDataTypeCd";
	public static final String ID = "id";
	public static final String TCP_PARAM_LOV_VALIDATION_IND = "tcpParamLovValidationInd";
	public static final String TRANSACTION_CODE = "transactionCode";
	public static final String TCP_PARAM_DESC = "tcpParamDesc";
	public static final String TCP_ENABLED_IND = "tcpEnabledInd";
	public static final String TCP_TCD_CODE = "tcpTcdCode";
	public static final String TCP_PARAM_LKT_TYPE = "tcpParamLktType";

}

