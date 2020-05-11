import { ICompanies } from 'app/shared/model/companies.model';

export interface IBranches {
  id?: number;
  brnNbr?: string;
  brnName?: string;
  brnCode?: string;
  brnShortName?: string;
  brnAddress1?: string;
  brnAddress2?: string;
  brnCity?: string;
  brnStateCd?: string;
  brnZip?: string;
  brnEnabledInd?: boolean;
  brnRegioinCd?: string;
  brnServiceTypeCd?: string;
  company?: ICompanies;
}

export class Branches implements IBranches {
  constructor(
    public id?: number,
    public brnNbr?: string,
    public brnName?: string,
    public brnCode?: string,
    public brnShortName?: string,
    public brnAddress1?: string,
    public brnAddress2?: string,
    public brnCity?: string,
    public brnStateCd?: string,
    public brnZip?: string,
    public brnEnabledInd?: boolean,
    public brnRegioinCd?: string,
    public brnServiceTypeCd?: string,
    public company?: ICompanies
  ) {
    this.brnEnabledInd = this.brnEnabledInd || false;
  }
}
