import { element, by, ElementFinder } from 'protractor';

export class BranchesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-branches div table .btn-danger'));
  title = element.all(by.css('jhi-branches div h2#page-heading span')).first();
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

export class BranchesUpdatePage {
  pageTitle = element(by.id('jhi-branches-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  brnNbrInput = element(by.id('field_brnNbr'));
  brnNameInput = element(by.id('field_brnName'));
  brnCodeInput = element(by.id('field_brnCode'));
  brnShortNameInput = element(by.id('field_brnShortName'));
  brnAddress1Input = element(by.id('field_brnAddress1'));
  brnAddress2Input = element(by.id('field_brnAddress2'));
  brnCityInput = element(by.id('field_brnCity'));
  brnStateCdInput = element(by.id('field_brnStateCd'));
  brnZipInput = element(by.id('field_brnZip'));
  brnEnabledIndInput = element(by.id('field_brnEnabledInd'));
  brnRegioinCdInput = element(by.id('field_brnRegioinCd'));
  brnServiceTypeCdInput = element(by.id('field_brnServiceTypeCd'));

  companySelect = element(by.id('field_company'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setBrnNbrInput(brnNbr: string): Promise<void> {
    await this.brnNbrInput.sendKeys(brnNbr);
  }

  async getBrnNbrInput(): Promise<string> {
    return await this.brnNbrInput.getAttribute('value');
  }

  async setBrnNameInput(brnName: string): Promise<void> {
    await this.brnNameInput.sendKeys(brnName);
  }

  async getBrnNameInput(): Promise<string> {
    return await this.brnNameInput.getAttribute('value');
  }

  async setBrnCodeInput(brnCode: string): Promise<void> {
    await this.brnCodeInput.sendKeys(brnCode);
  }

  async getBrnCodeInput(): Promise<string> {
    return await this.brnCodeInput.getAttribute('value');
  }

  async setBrnShortNameInput(brnShortName: string): Promise<void> {
    await this.brnShortNameInput.sendKeys(brnShortName);
  }

  async getBrnShortNameInput(): Promise<string> {
    return await this.brnShortNameInput.getAttribute('value');
  }

  async setBrnAddress1Input(brnAddress1: string): Promise<void> {
    await this.brnAddress1Input.sendKeys(brnAddress1);
  }

  async getBrnAddress1Input(): Promise<string> {
    return await this.brnAddress1Input.getAttribute('value');
  }

  async setBrnAddress2Input(brnAddress2: string): Promise<void> {
    await this.brnAddress2Input.sendKeys(brnAddress2);
  }

  async getBrnAddress2Input(): Promise<string> {
    return await this.brnAddress2Input.getAttribute('value');
  }

  async setBrnCityInput(brnCity: string): Promise<void> {
    await this.brnCityInput.sendKeys(brnCity);
  }

  async getBrnCityInput(): Promise<string> {
    return await this.brnCityInput.getAttribute('value');
  }

  async setBrnStateCdInput(brnStateCd: string): Promise<void> {
    await this.brnStateCdInput.sendKeys(brnStateCd);
  }

  async getBrnStateCdInput(): Promise<string> {
    return await this.brnStateCdInput.getAttribute('value');
  }

  async setBrnZipInput(brnZip: string): Promise<void> {
    await this.brnZipInput.sendKeys(brnZip);
  }

  async getBrnZipInput(): Promise<string> {
    return await this.brnZipInput.getAttribute('value');
  }

  getBrnEnabledIndInput(): ElementFinder {
    return this.brnEnabledIndInput;
  }

  async setBrnRegioinCdInput(brnRegioinCd: string): Promise<void> {
    await this.brnRegioinCdInput.sendKeys(brnRegioinCd);
  }

  async getBrnRegioinCdInput(): Promise<string> {
    return await this.brnRegioinCdInput.getAttribute('value');
  }

  async setBrnServiceTypeCdInput(brnServiceTypeCd: string): Promise<void> {
    await this.brnServiceTypeCdInput.sendKeys(brnServiceTypeCd);
  }

  async getBrnServiceTypeCdInput(): Promise<string> {
    return await this.brnServiceTypeCdInput.getAttribute('value');
  }

  async companySelectLastOption(): Promise<void> {
    await this.companySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async companySelectOption(option: string): Promise<void> {
    await this.companySelect.sendKeys(option);
  }

  getCompanySelect(): ElementFinder {
    return this.companySelect;
  }

  async getCompanySelectedOption(): Promise<string> {
    return await this.companySelect.element(by.css('option:checked')).getText();
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

export class BranchesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-branches-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-branches'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
