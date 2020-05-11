export interface ILookupTypes {
  id?: number;
  lktType?: string;
  lktDesc?: string;
}

export class LookupTypes implements ILookupTypes {
  constructor(public id?: number, public lktType?: string, public lktDesc?: string) {}
}
