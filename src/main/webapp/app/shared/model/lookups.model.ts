import { ILookupTypes } from 'app/shared/model/lookup-types.model';

export interface ILookups {
  id?: number;
  lkcCode?: string;
  lkcSubCode?: string;
  lkcSort?: string;
  lkcEnabledInd?: boolean;
  lkcDesc?: string;
  lookUpType?: ILookupTypes;
}

export class Lookups implements ILookups {
  constructor(
    public id?: number,
    public lkcCode?: string,
    public lkcSubCode?: string,
    public lkcSort?: string,
    public lkcEnabledInd?: boolean,
    public lkcDesc?: string,
    public lookUpType?: ILookupTypes
  ) {
    this.lkcEnabledInd = this.lkcEnabledInd || false;
  }
}
