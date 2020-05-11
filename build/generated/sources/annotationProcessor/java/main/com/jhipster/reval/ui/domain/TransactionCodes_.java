package com.jhipster.reval.ui.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionCodes.class)
public abstract class TransactionCodes_ {

	public static volatile SingularAttribute<TransactionCodes, String> tcdCode;
	public static volatile SingularAttribute<TransactionCodes, String> tcdDesc;
	public static volatile SetAttribute<TransactionCodes, TransactionCodeParams> txnCodeParams;
	public static volatile SingularAttribute<TransactionCodes, String> tcdEntityGroupCd;
	public static volatile SingularAttribute<TransactionCodes, Long> id;
	public static volatile SingularAttribute<TransactionCodes, Boolean> tcdEnabledInd;

	public static final String TCD_CODE = "tcdCode";
	public static final String TCD_DESC = "tcdDesc";
	public static final String TXN_CODE_PARAMS = "txnCodeParams";
	public static final String TCD_ENTITY_GROUP_CD = "tcdEntityGroupCd";
	public static final String ID = "id";
	public static final String TCD_ENABLED_IND = "tcdEnabledInd";

}

