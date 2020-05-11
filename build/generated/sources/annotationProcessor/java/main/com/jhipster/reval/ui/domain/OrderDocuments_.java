package com.jhipster.reval.ui.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderDocuments.class)
public abstract class OrderDocuments_ {

	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentDueToCd;
	public static volatile SingularAttribute<OrderDocuments, LocalDate> odoDocumentRcvdDt;
	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentStatusCd;
	public static volatile SingularAttribute<OrderDocuments, byte[]> odoDocument;
	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentContentType;
	public static volatile SingularAttribute<OrderDocuments, String> odoExternalStorageFileName;
	public static volatile SingularAttribute<OrderDocuments, LocalDate> odoDocumentDueDt;
	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentMimeTypeCd;
	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentTypeCd;
	public static volatile SingularAttribute<OrderDocuments, String> odoExternalStorageLink;
	public static volatile SingularAttribute<OrderDocuments, Long> id;
	public static volatile SingularAttribute<OrderDocuments, String> odoDocumentDueFromCd;
	public static volatile SingularAttribute<OrderDocuments, Orders> order;

	public static final String ODO_DOCUMENT_DUE_TO_CD = "odoDocumentDueToCd";
	public static final String ODO_DOCUMENT_RCVD_DT = "odoDocumentRcvdDt";
	public static final String ODO_DOCUMENT_STATUS_CD = "odoDocumentStatusCd";
	public static final String ODO_DOCUMENT = "odoDocument";
	public static final String ODO_DOCUMENT_CONTENT_TYPE = "odoDocumentContentType";
	public static final String ODO_EXTERNAL_STORAGE_FILE_NAME = "odoExternalStorageFileName";
	public static final String ODO_DOCUMENT_DUE_DT = "odoDocumentDueDt";
	public static final String ODO_DOCUMENT_MIME_TYPE_CD = "odoDocumentMimeTypeCd";
	public static final String ODO_DOCUMENT_TYPE_CD = "odoDocumentTypeCd";
	public static final String ODO_EXTERNAL_STORAGE_LINK = "odoExternalStorageLink";
	public static final String ID = "id";
	public static final String ODO_DOCUMENT_DUE_FROM_CD = "odoDocumentDueFromCd";
	public static final String ORDER = "order";

}

