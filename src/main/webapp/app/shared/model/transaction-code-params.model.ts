import { ITransactionCodes } from 'app/shared/model/transaction-codes.model';

export interface ITransactionCodeParams {
  id?: number;
  tcpTcdCode?: string;
  tcpParamName?: string;
  tcpParamDesc?: string;
  tcpEnabledInd?: boolean;
  tcpParamDataTypeCd?: string;
  tcpParamLength?: string;
  tcpDefaultValue?: string;
  tcpParamLovValidationInd?: boolean;
  tcpParamLktType?: string;
  transactionCode?: ITransactionCodes;
}

export class TransactionCodeParams implements ITransactionCodeParams {
  constructor(
    public id?: number,
    public tcpTcdCode?: string,
    public tcpParamName?: string,
    public tcpParamDesc?: string,
    public tcpEnabledInd?: boolean,
    public tcpParamDataTypeCd?: string,
    public tcpParamLength?: string,
    public tcpDefaultValue?: string,
    public tcpParamLovValidationInd?: boolean,
    public tcpParamLktType?: string,
    public transactionCode?: ITransactionCodes
  ) {
    this.tcpEnabledInd = this.tcpEnabledInd || false;
    this.tcpParamLovValidationInd = this.tcpParamLovValidationInd || false;
  }
}
