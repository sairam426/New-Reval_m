import { ITransactionCodeParams } from 'app/shared/model/transaction-code-params.model';

export interface ITransactionCodes {
  id?: number;
  tcdCode?: string;
  tcdEntityGroupCd?: string;
  tcdDesc?: string;
  tcdEnabledInd?: boolean;
  txnCodeParams?: ITransactionCodeParams[];
}

export class TransactionCodes implements ITransactionCodes {
  constructor(
    public id?: number,
    public tcdCode?: string,
    public tcdEntityGroupCd?: string,
    public tcdDesc?: string,
    public tcdEnabledInd?: boolean,
    public txnCodeParams?: ITransactionCodeParams[]
  ) {
    this.tcdEnabledInd = this.tcdEnabledInd || false;
  }
}
