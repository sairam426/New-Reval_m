package com.jhipster.reval.ui.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transactions.class)
public abstract class Transactions_ {

	public static volatile SingularAttribute<Transactions, String> txnTcdCode;
	public static volatile SingularAttribute<Transactions, String> txnStatusCd;
	public static volatile SetAttribute<Transactions, TransactionDetails> txnDetails;
	public static volatile SingularAttribute<Transactions, LocalDate> txnPostDt;
	public static volatile SingularAttribute<Transactions, Long> id;
	public static volatile SingularAttribute<Transactions, Long> txnEntityId;
	public static volatile SingularAttribute<Transactions, String> txnEntityNbr;

	public static final String TXN_TCD_CODE = "txnTcdCode";
	public static final String TXN_STATUS_CD = "txnStatusCd";
	public static final String TXN_DETAILS = "txnDetails";
	public static final String TXN_POST_DT = "txnPostDt";
	public static final String ID = "id";
	public static final String TXN_ENTITY_ID = "txnEntityId";
	public static final String TXN_ENTITY_NBR = "txnEntityNbr";

}

