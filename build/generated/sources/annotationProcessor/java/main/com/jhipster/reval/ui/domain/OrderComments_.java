package com.jhipster.reval.ui.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderComments.class)
public abstract class OrderComments_ {

	public static volatile SingularAttribute<OrderComments, Boolean> ocmCommentImpInd;
	public static volatile SingularAttribute<OrderComments, String> ocmCommentTypeCd;
	public static volatile SingularAttribute<OrderComments, LocalDate> ocmCommentDate;
	public static volatile SingularAttribute<OrderComments, Long> id;
	public static volatile SingularAttribute<OrderComments, String> ocmComment;
	public static volatile SingularAttribute<OrderComments, String> ocmCommentSubTypeCd;
	public static volatile SingularAttribute<OrderComments, String> ocmCommentBy;
	public static volatile SingularAttribute<OrderComments, Orders> order;

	public static final String OCM_COMMENT_IMP_IND = "ocmCommentImpInd";
	public static final String OCM_COMMENT_TYPE_CD = "ocmCommentTypeCd";
	public static final String OCM_COMMENT_DATE = "ocmCommentDate";
	public static final String ID = "id";
	public static final String OCM_COMMENT = "ocmComment";
	public static final String OCM_COMMENT_SUB_TYPE_CD = "ocmCommentSubTypeCd";
	public static final String OCM_COMMENT_BY = "ocmCommentBy";
	public static final String ORDER = "order";

}

