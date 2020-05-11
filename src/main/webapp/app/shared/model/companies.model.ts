export interface ICompanies {
  id?: number;
  cmpNbr?: string;
  cmpName?: string;
  cmpServiceTypeCd?: string;
  cmpShortName?: string;
  cmpStatusCd?: string;
  cmpTypeCd?: string;
  cmpAddress1?: string;
  cmpAddress2?: string;
  cmpCity?: string;
  cmpStateCd?: string;
  cmpZip?: string;
  cmpEnabledInd?: boolean;
}

export class Companies implements ICompanies {
  constructor(
    public id?: number,
    public cmpNbr?: string,
    public cmpName?: string,
    public cmpServiceTypeCd?: string,
    public cmpShortName?: string,
    public cmpStatusCd?: string,
    public cmpTypeCd?: string,
    public cmpAddress1?: string,
    public cmpAddress2?: string,
    public cmpCity?: string,
    public cmpStateCd?: string,
    public cmpZip?: string,
    public cmpEnabledInd?: boolean
  ) {
    this.cmpEnabledInd = this.cmpEnabledInd || false;
  }
}
