package com.jhipster.reval.ui.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionDetails.class)
public abstract class TransactionDetails_ {

	public static volatile SingularAttribute<TransactionDetails, String> txdPrmCode;
	public static volatile SingularAttribute<TransactionDetails, String> txdValue;
	public static volatile SingularAttribute<TransactionDetails, Long> id;
	public static volatile SingularAttribute<TransactionDetails, Transactions> transaction;

	public static final String TXD_PRM_CODE = "txdPrmCode";
	public static final String TXD_VALUE = "txdValue";
	public static final String ID = "id";
	public static final String TRANSACTION = "transaction";

}

