export interface IProviders {
  id?: number;
  proNbr?: string;
  proName?: string;
  proShortName?: string;
  proStatusCd?: string;
  proTypeCd?: string;
  proGroupCd?: string;
  proLicenseNbr?: string;
  proLicenseStatusCd?: string;
  proAddress1?: string;
  proAddress2?: string;
  proCity?: string;
  proStateCd?: string;
  proZip?: number;
}

export class Providers implements IProviders {
  constructor(
    public id?: number,
    public proNbr?: string,
    public proName?: string,
    public proShortName?: string,
    public proStatusCd?: string,
    public proTypeCd?: string,
    public proGroupCd?: string,
    public proLicenseNbr?: string,
    public proLicenseStatusCd?: string,
    public proAddress1?: string,
    public proAddress2?: string,
    public proCity?: string,
    public proStateCd?: string,
    public proZip?: number
  ) {}
}
