package com.jhipster.reval.ui.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.jhipster.reval.ui.domain.Orders;
import com.jhipster.reval.ui.domain.*; // for static metamodels
import com.jhipster.reval.ui.repository.OrdersRepository;
import com.jhipster.reval.ui.service.dto.OrdersCriteria;

/**
 * Service for executing complex queries for {@link Orders} entities in the database.
 * The main input is a {@link OrdersCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Orders} or a {@link Page} of {@link Orders} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrdersQueryService extends QueryService<Orders> {

    private final Logger log = LoggerFactory.getLogger(OrdersQueryService.class);

    private final OrdersRepository ordersRepository;

    public OrdersQueryService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    /**
     * Return a {@link List} of {@link Orders} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Orders> findByCriteria(OrdersCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Orders> specification = createSpecification(criteria);
        return ordersRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Orders} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Orders> findByCriteria(OrdersCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Orders> specification = createSpecification(criteria);
        return ordersRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrdersCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Orders> specification = createSpecification(criteria);
        return ordersRepository.count(specification);
    }

    /**
     * Function to convert {@link OrdersCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Orders> createSpecification(OrdersCriteria criteria) {
        Specification<Orders> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Orders_.id));
            }
            if (criteria.getOrdNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdNumber(), Orders_.ordNumber));
            }
            if (criteria.getOrdStageCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdStageCd(), Orders_.ordStageCd));
            }
            if (criteria.getOrdOrgOrderStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdOrgOrderStatusCd(), Orders_.ordOrgOrderStatusCd));
            }
            if (criteria.getOrdCmpOrderStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdCmpOrderStatusCd(), Orders_.ordCmpOrderStatusCd));
            }
            if (criteria.getOrdProOrderStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdProOrderStatusCd(), Orders_.ordProOrderStatusCd));
            }
            if (criteria.getOrdCmpNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdCmpNbr(), Orders_.ordCmpNbr));
            }
            if (criteria.getOrdBrnNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdBrnNbr(), Orders_.ordBrnNbr));
            }
            if (criteria.getOrdProNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdProNbr(), Orders_.ordProNbr));
            }
            if (criteria.getOrdProductCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdProductCode(), Orders_.ordProductCode));
            }
            if (criteria.getOrdOriginalValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdOriginalValueAmt(), Orders_.ordOriginalValueAmt));
            }
            if (criteria.getOrdParentTrackingNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdParentTrackingNbr(), Orders_.ordParentTrackingNbr));
            }
            if (criteria.getOrdUserNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUserNbr(), Orders_.ordUserNbr));
            }
            if (criteria.getOrdOrderDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdOrderDt(), Orders_.ordOrderDt));
            }
            if (criteria.getOrdRushRequestDueDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdRushRequestDueDt(), Orders_.ordRushRequestDueDt));
            }
            if (criteria.getOrdOrgInstructions() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdOrgInstructions(), Orders_.ordOrgInstructions));
            }
            if (criteria.getOrdOrgOtherInstructions() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdOrgOtherInstructions(), Orders_.ordOrgOtherInstructions));
            }
            if (criteria.getOrdMultiOrderInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdMultiOrderInd(), Orders_.ordMultiOrderInd));
            }
            if (criteria.getOrdOrgDuedate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdOrgDuedate(), Orders_.ordOrgDuedate));
            }
            if (criteria.getOrdProDuedate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdProDuedate(), Orders_.ordProDuedate));
            }
            if (criteria.getOrdUcdpDataFnmaSubmitToUcdpInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdUcdpDataFnmaSubmitToUcdpInd(), Orders_.ordUcdpDataFnmaSubmitToUcdpInd));
            }
            if (criteria.getOrdUcdpDataFnmaServicerNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpDataFnmaServicerNbr(), Orders_.ordUcdpDataFnmaServicerNbr));
            }
            if (criteria.getOrdUcdpDataFhlmcSubmitToUcdpInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdUcdpDataFhlmcSubmitToUcdpInd(), Orders_.ordUcdpDataFhlmcSubmitToUcdpInd));
            }
            if (criteria.getOrdUcdpDataFhlmcIdentificationNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpDataFhlmcIdentificationNbr(), Orders_.ordUcdpDataFhlmcIdentificationNbr));
            }
            if (criteria.getOrdUcdpDataUcdpBussinessUnitNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpDataUcdpBussinessUnitNbr(), Orders_.ordUcdpDataUcdpBussinessUnitNbr));
            }
            if (criteria.getOrdUcdpSdFnamaDocumentId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpSdFnamaDocumentId(), Orders_.ordUcdpSdFnamaDocumentId));
            }
            if (criteria.getOrdUcdpSdFnmaSubmissionStatusId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpSdFnmaSubmissionStatusId(), Orders_.ordUcdpSdFnmaSubmissionStatusId));
            }
            if (criteria.getOrdUcdpSdFhlmcDocumentFileCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpSdFhlmcDocumentFileCd(), Orders_.ordUcdpSdFhlmcDocumentFileCd));
            }
            if (criteria.getOrdUcdpSdEadSubmissionStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdUcdpSdEadSubmissionStatusCd(), Orders_.ordUcdpSdEadSubmissionStatusCd));
            }
            if (criteria.getOrdUcdpFinalSubmissionDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdUcdpFinalSubmissionDt(), Orders_.ordUcdpFinalSubmissionDt));
            }
            if (criteria.getOrdLoanNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanNbr(), Orders_.ordLoanNbr));
            }
            if (criteria.getOrdloanOldLoanNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdloanOldLoanNbr(), Orders_.ordloanOldLoanNbr));
            }
            if (criteria.getOrdLoanProgram() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanProgram(), Orders_.ordLoanProgram));
            }
            if (criteria.getOrdLoanPurpose() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanPurpose(), Orders_.ordLoanPurpose));
            }
            if (criteria.getOrdLoanType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanType(), Orders_.ordLoanType));
            }
            if (criteria.getOrdLoanAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdLoanAmt(), Orders_.ordLoanAmt));
            }
            if (criteria.getOrdLoanQualifyingValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdLoanQualifyingValueAmt(), Orders_.ordLoanQualifyingValueAmt));
            }
            if (criteria.getOrdLoanSalesPriceAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdLoanSalesPriceAmt(), Orders_.ordLoanSalesPriceAmt));
            }
            if (criteria.getOrdLoanBorrowerLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanBorrowerLastName(), Orders_.ordLoanBorrowerLastName));
            }
            if (criteria.getOrdLoanBorrowerFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanBorrowerFirstName(), Orders_.ordLoanBorrowerFirstName));
            }
            if (criteria.getOrdLoanBorrowerMiddleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanBorrowerMiddleName(), Orders_.ordLoanBorrowerMiddleName));
            }
            if (criteria.getOrdLoanBorrowerIsCoBorrowerInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdLoanBorrowerIsCoBorrowerInd(), Orders_.ordLoanBorrowerIsCoBorrowerInd));
            }
            if (criteria.getOrdLoanFhaCaseNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanFhaCaseNbr(), Orders_.ordLoanFhaCaseNbr));
            }
            if (criteria.getOrdLoanDeedRestrictionInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdLoanDeedRestrictionInd(), Orders_.ordLoanDeedRestrictionInd));
            }
            if (criteria.getOrdLoanEstimatedCloseDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdLoanEstimatedCloseDt(), Orders_.ordLoanEstimatedCloseDt));
            }
            if (criteria.getOrdLoanHpmlInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdLoanHpmlInd(), Orders_.ordLoanHpmlInd));
            }
            if (criteria.getOrdLoanIsNewConstructionInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdLoanIsNewConstructionInd(), Orders_.ordLoanIsNewConstructionInd));
            }
            if (criteria.getOrdLoanCustomerSegmentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdLoanCustomerSegmentCode(), Orders_.ordLoanCustomerSegmentCode));
            }
            if (criteria.getOrdLoanNonSubjectPropertyInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdLoanNonSubjectPropertyInd(), Orders_.ordLoanNonSubjectPropertyInd));
            }
            if (criteria.getOrdLoanBorrowerRequestedCloseDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdLoanBorrowerRequestedCloseDt(), Orders_.ordLoanBorrowerRequestedCloseDt));
            }
            if (criteria.getOrdPropertyTypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyTypeCd(), Orders_.ordPropertyTypeCd));
            }
            if (criteria.getOrdPropertyAddress1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyAddress1(), Orders_.ordPropertyAddress1));
            }
            if (criteria.getOrdPropertyAddress2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyAddress2(), Orders_.ordPropertyAddress2));
            }
            if (criteria.getOrdPropertyCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyCity(), Orders_.ordPropertyCity));
            }
            if (criteria.getOrdPropertyStateCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyStateCd(), Orders_.ordPropertyStateCd));
            }
            if (criteria.getOrdPropertyZip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyZip(), Orders_.ordPropertyZip));
            }
            if (criteria.getOrdPropertyCounty() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyCounty(), Orders_.ordPropertyCounty));
            }
            if (criteria.getOrdPropertyLatitue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyLatitue(), Orders_.ordPropertyLatitue));
            }
            if (criteria.getOrdPropertyLongitude() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyLongitude(), Orders_.ordPropertyLongitude));
            }
            if (criteria.getOrdPropertyProjectName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdPropertyProjectName(), Orders_.ordPropertyProjectName));
            }
            if (criteria.getOrdContact1TypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact1TypeCd(), Orders_.ordContact1TypeCd));
            }
            if (criteria.getOrdContact1Name() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact1Name(), Orders_.ordContact1Name));
            }
            if (criteria.getOrdContact1WorkPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact1WorkPhone(), Orders_.ordContact1WorkPhone));
            }
            if (criteria.getOrdContact1HomePhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact1HomePhone(), Orders_.ordContact1HomePhone));
            }
            if (criteria.getOrdContact1CellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact1CellPhone(), Orders_.ordContact1CellPhone));
            }
            if (criteria.getOrdContact1OtherCellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact1OtherCellPhone(), Orders_.ordContact1OtherCellPhone));
            }
            if (criteria.getOrdContact1Email() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact1Email(), Orders_.ordContact1Email));
            }
            if (criteria.getOrdContact1OtherEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact1OtherEmail(), Orders_.ordContact1OtherEmail));
            }
            if (criteria.getOrdContact2TypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact2TypeCd(), Orders_.ordContact2TypeCd));
            }
            if (criteria.getOrdContact2Name() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact2Name(), Orders_.ordContact2Name));
            }
            if (criteria.getOrdContact2WorkPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact2WorkPhone(), Orders_.ordContact2WorkPhone));
            }
            if (criteria.getOrdContact2HomePhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact2HomePhone(), Orders_.ordContact2HomePhone));
            }
            if (criteria.getOrdContact2CellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact2CellPhone(), Orders_.ordContact2CellPhone));
            }
            if (criteria.getOrdContact2OtherCellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact2OtherCellPhone(), Orders_.ordContact2OtherCellPhone));
            }
            if (criteria.getOrdContact2Email() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact2Email(), Orders_.ordContact2Email));
            }
            if (criteria.getOrdContact2OtherEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact2OtherEmail(), Orders_.ordContact2OtherEmail));
            }
            if (criteria.getOrdContact3TypeCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact3TypeCd(), Orders_.ordContact3TypeCd));
            }
            if (criteria.getOrdContact3Name() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact3Name(), Orders_.ordContact3Name));
            }
            if (criteria.getOrdContact3WorkPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact3WorkPhone(), Orders_.ordContact3WorkPhone));
            }
            if (criteria.getOrdContact3HomePhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact3HomePhone(), Orders_.ordContact3HomePhone));
            }
            if (criteria.getOrdContact3CellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact3CellPhone(), Orders_.ordContact3CellPhone));
            }
            if (criteria.getOrdContact3OtherCellPhone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdContact3OtherCellPhone(), Orders_.ordContact3OtherCellPhone));
            }
            if (criteria.getOrdContact3Email() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact3Email(), Orders_.ordContact3Email));
            }
            if (criteria.getOrdContact3OtherEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdContact3OtherEmail(), Orders_.ordContact3OtherEmail));
            }
            if (criteria.getOrdRequestRrStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrStatusCd(), Orders_.ordRequestRrStatusCd));
            }
            if (criteria.getOrdRequestRrVendorAppraisalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrVendorAppraisalId(), Orders_.ordRequestRrVendorAppraisalId));
            }
            if (criteria.getOrdRequestRrvendorAppraisalDraftRcvdDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdRequestRrvendorAppraisalDraftRcvdDt(), Orders_.ordRequestRrvendorAppraisalDraftRcvdDt));
            }
            if (criteria.getOrdRequestRrXmlFormId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrXmlFormId(), Orders_.ordRequestRrXmlFormId));
            }
            if (criteria.getOrdRequestRrPdfFormId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrPdfFormId(), Orders_.ordRequestRrPdfFormId));
            }
            if (criteria.getOrdRequestRrApprFileDataSourcCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrApprFileDataSourcCd(), Orders_.ordRequestRrApprFileDataSourcCd));
            }
            if (criteria.getOrdRequestRrReportValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdRequestRrReportValueAmt(), Orders_.ordRequestRrReportValueAmt));
            }
            if (criteria.getOrdRequestRrAppraisalCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrAppraisalCompanyName(), Orders_.ordRequestRrAppraisalCompanyName));
            }
            if (criteria.getOrdRequestRrvendorName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrvendorName(), Orders_.ordRequestRrvendorName));
            }
            if (criteria.getOrdRequestRrvendorLicenseNbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrvendorLicenseNbr(), Orders_.ordRequestRrvendorLicenseNbr));
            }
            if (criteria.getOrdRequestRrvendorLicenseStateCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdRequestRrvendorLicenseStateCd(), Orders_.ordRequestRrvendorLicenseStateCd));
            }
            if (criteria.getOrdValuationCompletedProduct() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdValuationCompletedProduct(), Orders_.ordValuationCompletedProduct));
            }
            if (criteria.getOrdValuationDueToClientDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationDueToClientDt(), Orders_.ordValuationDueToClientDt));
            }
            if (criteria.getOrdValuationReportRecivedDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationReportRecivedDt(), Orders_.ordValuationReportRecivedDt));
            }
            if (criteria.getOrdValuationCompletionDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationCompletionDt(), Orders_.ordValuationCompletionDt));
            }
            if (criteria.getOrdValuationReportStatusCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdValuationReportStatusCd(), Orders_.ordValuationReportStatusCd));
            }
            if (criteria.getOrdValuationAppraisedValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationAppraisedValueAmt(), Orders_.ordValuationAppraisedValueAmt));
            }
            if (criteria.getOrdValuationBpoMarketValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationBpoMarketValueAmt(), Orders_.ordValuationBpoMarketValueAmt));
            }
            if (criteria.getOrdValuationBpoAsIsAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationBpoAsIsAmt(), Orders_.ordValuationBpoAsIsAmt));
            }
            if (criteria.getOrdValuationBpoAsRepairedValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationBpoAsRepairedValueAmt(), Orders_.ordValuationBpoAsRepairedValueAmt));
            }
            if (criteria.getOrdValuationRedFlagCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdValuationRedFlagCode(), Orders_.ordValuationRedFlagCode));
            }
            if (criteria.getOrdValuationRedFlagDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdValuationRedFlagDesc(), Orders_.ordValuationRedFlagDesc));
            }
            if (criteria.getOrdValuationAmcAppraisalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdValuationAmcAppraisalId(), Orders_.ordValuationAmcAppraisalId));
            }
            if (criteria.getOrdValuationFinalReviewId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdValuationFinalReviewId(), Orders_.ordValuationFinalReviewId));
            }
            if (criteria.getOrdReviewRecommendedValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdReviewRecommendedValueAmt(), Orders_.ordReviewRecommendedValueAmt));
            }
            if (criteria.getOrdReviewDt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdReviewDt(), Orders_.ordReviewDt));
            }
            if (criteria.getOrdReviewRecommendedActionCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdReviewRecommendedActionCd(), Orders_.ordReviewRecommendedActionCd));
            }
            if (criteria.getOrdReviewReviewerDecisionCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdReviewReviewerDecisionCd(), Orders_.ordReviewReviewerDecisionCd));
            }
            if (criteria.getOrdReviewReviewScore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdReviewReviewScore(), Orders_.ordReviewReviewScore));
            }
            if (criteria.getOrdReviewFormUsed() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdReviewFormUsed(), Orders_.ordReviewFormUsed));
            }
            if (criteria.getOrdReviewProviderOnWatchListInd() != null) {
                specification = specification.and(buildSpecification(criteria.getOrdReviewProviderOnWatchListInd(), Orders_.ordReviewProviderOnWatchListInd));
            }
            if (criteria.getOrdReviewCuredValueAmt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrdReviewCuredValueAmt(), Orders_.ordReviewCuredValueAmt));
            }
            if (criteria.getOrdReviewFinalRecommendedActionCd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrdReviewFinalRecommendedActionCd(), Orders_.ordReviewFinalRecommendedActionCd));
            }
            if (criteria.getCommentsId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommentsId(),
                    root -> root.join(Orders_.comments, JoinType.LEFT).get(OrderComments_.id)));
            }
            if (criteria.getDocumentsId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentsId(),
                    root -> root.join(Orders_.documents, JoinType.LEFT).get(OrderDocuments_.id)));
            }
        }
        return specification;
    }
}
