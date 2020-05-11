export interface IOrganizations {
  id?: number;
  orgNbr?: string;
  orgName?: string;
  orgShortName?: string;
  orgAddress1?: string;
  orgAddress2?: string;
  orgCity?: string;
  orgStateCd?: string;
  orgZip?: string;
}

export class Organizations implements IOrganizations {
  constructor(
    public id?: number,
    public orgNbr?: string,
    public orgName?: string,
    public orgShortName?: string,
    public orgAddress1?: string,
    public orgAddress2?: string,
    public orgCity?: string,
    public orgStateCd?: string,
    public orgZip?: string
  ) {}
}
