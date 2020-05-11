export interface ISystemAccess {
  id?: number;
  sacAccessKey?: string;
  sacAccessTypeCd?: string;
  sacAccessValue?: string;
  sacAllowedInd?: boolean;
}

export class SystemAccess implements ISystemAccess {
  constructor(
    public id?: number,
    public sacAccessKey?: string,
    public sacAccessTypeCd?: string,
    public sacAccessValue?: string,
    public sacAllowedInd?: boolean
  ) {
    this.sacAllowedInd = this.sacAllowedInd || false;
  }
}
