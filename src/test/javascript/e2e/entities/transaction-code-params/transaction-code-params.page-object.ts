import { element, by, ElementFinder } from 'protractor';

export class TransactionCodeParamsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-transaction-code-params div table .btn-danger'));
  title = element.all(by.css('jhi-transaction-code-params div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class TransactionCodeParamsUpdatePage {
  pageTitle = element(by.id('jhi-transaction-code-params-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  tcpTcdCodeInput = element(by.id('field_tcpTcdCode'));
  tcpParamNameInput = element(by.id('field_tcpParamName'));
  tcpParamDescInput = element(by.id('field_tcpParamDesc'));
  tcpEnabledIndInput = element(by.id('field_tcpEnabledInd'));
  tcpParamDataTypeCdInput = element(by.id('field_tcpParamDataTypeCd'));
  tcpParamLengthInput = element(by.id('field_tcpParamLength'));
  tcpDefaultValueInput = element(by.id('field_tcpDefaultValue'));
  tcpParamLovValidationIndInput = element(by.id('field_tcpParamLovValidationInd'));
  tcpParamLktTypeInput = element(by.id('field_tcpParamLktType'));

  transactionCodeSelect = element(by.id('field_transactionCode'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTcpTcdCodeInput(tcpTcdCode: string): Promise<void> {
    await this.tcpTcdCodeInput.sendKeys(tcpTcdCode);
  }

  async getTcpTcdCodeInput(): Promise<string> {
    return await this.tcpTcdCodeInput.getAttribute('value');
  }

  async setTcpParamNameInput(tcpParamName: string): Promise<void> {
    await this.tcpParamNameInput.sendKeys(tcpParamName);
  }

  async getTcpParamNameInput(): Promise<string> {
    return await this.tcpParamNameInput.getAttribute('value');
  }

  async setTcpParamDescInput(tcpParamDesc: string): Promise<void> {
    await this.tcpParamDescInput.sendKeys(tcpParamDesc);
  }

  async getTcpParamDescInput(): Promise<string> {
    return await this.tcpParamDescInput.getAttribute('value');
  }

  getTcpEnabledIndInput(): ElementFinder {
    return this.tcpEnabledIndInput;
  }

  async setTcpParamDataTypeCdInput(tcpParamDataTypeCd: string): Promise<void> {
    await this.tcpParamDataTypeCdInput.sendKeys(tcpParamDataTypeCd);
  }

  async getTcpParamDataTypeCdInput(): Promise<string> {
    return await this.tcpParamDataTypeCdInput.getAttribute('value');
  }

  async setTcpParamLengthInput(tcpParamLength: string): Promise<void> {
    await this.tcpParamLengthInput.sendKeys(tcpParamLength);
  }

  async getTcpParamLengthInput(): Promise<string> {
    return await this.tcpParamLengthInput.getAttribute('value');
  }

  async setTcpDefaultValueInput(tcpDefaultValue: string): Promise<void> {
    await this.tcpDefaultValueInput.sendKeys(tcpDefaultValue);
  }

  async getTcpDefaultValueInput(): Promise<string> {
    return await this.tcpDefaultValueInput.getAttribute('value');
  }

  getTcpParamLovValidationIndInput(): ElementFinder {
    return this.tcpParamLovValidationIndInput;
  }

  async setTcpParamLktTypeInput(tcpParamLktType: string): Promise<void> {
    await this.tcpParamLktTypeInput.sendKeys(tcpParamLktType);
  }

  async getTcpParamLktTypeInput(): Promise<string> {
    return await this.tcpParamLktTypeInput.getAttribute('value');
  }

  async transactionCodeSelectLastOption(): Promise<void> {
    await this.transactionCodeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async transactionCodeSelectOption(option: string): Promise<void> {
    await this.transactionCodeSelect.sendKeys(option);
  }

  getTransactionCodeSelect(): ElementFinder {
    return this.transactionCodeSelect;
  }

  async getTransactionCodeSelectedOption(): Promise<string> {
    return await this.transactionCodeSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class TransactionCodeParamsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-transactionCodeParams-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-transactionCodeParams'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
