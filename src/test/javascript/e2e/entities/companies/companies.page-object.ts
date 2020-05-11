import { element, by, ElementFinder } from 'protractor';

export class CompaniesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-companies div table .btn-danger'));
  title = element.all(by.css('jhi-companies div h2#page-heading span')).first();
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

export class CompaniesUpdatePage {
  pageTitle = element(by.id('jhi-companies-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  cmpNbrInput = element(by.id('field_cmpNbr'));
  cmpNameInput = element(by.id('field_cmpName'));
  cmpServiceTypeCdInput = element(by.id('field_cmpServiceTypeCd'));
  cmpShortNameInput = element(by.id('field_cmpShortName'));
  cmpStatusCdInput = element(by.id('field_cmpStatusCd'));
  cmpTypeCdInput = element(by.id('field_cmpTypeCd'));
  cmpAddress1Input = element(by.id('field_cmpAddress1'));
  cmpAddress2Input = element(by.id('field_cmpAddress2'));
  cmpCityInput = element(by.id('field_cmpCity'));
  cmpStateCdInput = element(by.id('field_cmpStateCd'));
  cmpZipInput = element(by.id('field_cmpZip'));
  cmpEnabledIndInput = element(by.id('field_cmpEnabledInd'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCmpNbrInput(cmpNbr: string): Promise<void> {
    await this.cmpNbrInput.sendKeys(cmpNbr);
  }

  async getCmpNbrInput(): Promise<string> {
    return await this.cmpNbrInput.getAttribute('value');
  }

  async setCmpNameInput(cmpName: string): Promise<void> {
    await this.cmpNameInput.sendKeys(cmpName);
  }

  async getCmpNameInput(): Promise<string> {
    return await this.cmpNameInput.getAttribute('value');
  }

  async setCmpServiceTypeCdInput(cmpServiceTypeCd: string): Promise<void> {
    await this.cmpServiceTypeCdInput.sendKeys(cmpServiceTypeCd);
  }

  async getCmpServiceTypeCdInput(): Promise<string> {
    return await this.cmpServiceTypeCdInput.getAttribute('value');
  }

  async setCmpShortNameInput(cmpShortName: string): Promise<void> {
    await this.cmpShortNameInput.sendKeys(cmpShortName);
  }

  async getCmpShortNameInput(): Promise<string> {
    return await this.cmpShortNameInput.getAttribute('value');
  }

  async setCmpStatusCdInput(cmpStatusCd: string): Promise<void> {
    await this.cmpStatusCdInput.sendKeys(cmpStatusCd);
  }

  async getCmpStatusCdInput(): Promise<string> {
    return await this.cmpStatusCdInput.getAttribute('value');
  }

  async setCmpTypeCdInput(cmpTypeCd: string): Promise<void> {
    await this.cmpTypeCdInput.sendKeys(cmpTypeCd);
  }

  async getCmpTypeCdInput(): Promise<string> {
    return await this.cmpTypeCdInput.getAttribute('value');
  }

  async setCmpAddress1Input(cmpAddress1: string): Promise<void> {
    await this.cmpAddress1Input.sendKeys(cmpAddress1);
  }

  async getCmpAddress1Input(): Promise<string> {
    return await this.cmpAddress1Input.getAttribute('value');
  }

  async setCmpAddress2Input(cmpAddress2: string): Promise<void> {
    await this.cmpAddress2Input.sendKeys(cmpAddress2);
  }

  async getCmpAddress2Input(): Promise<string> {
    return await this.cmpAddress2Input.getAttribute('value');
  }

  async setCmpCityInput(cmpCity: string): Promise<void> {
    await this.cmpCityInput.sendKeys(cmpCity);
  }

  async getCmpCityInput(): Promise<string> {
    return await this.cmpCityInput.getAttribute('value');
  }

  async setCmpStateCdInput(cmpStateCd: string): Promise<void> {
    await this.cmpStateCdInput.sendKeys(cmpStateCd);
  }

  async getCmpStateCdInput(): Promise<string> {
    return await this.cmpStateCdInput.getAttribute('value');
  }

  async setCmpZipInput(cmpZip: string): Promise<void> {
    await this.cmpZipInput.sendKeys(cmpZip);
  }

  async getCmpZipInput(): Promise<string> {
    return await this.cmpZipInput.getAttribute('value');
  }

  getCmpEnabledIndInput(): ElementFinder {
    return this.cmpEnabledIndInput;
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

export class CompaniesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-companies-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-companies'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
