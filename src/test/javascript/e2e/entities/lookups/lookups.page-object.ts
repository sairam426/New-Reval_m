import { element, by, ElementFinder } from 'protractor';

export class LookupsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-lookups div table .btn-danger'));
  title = element.all(by.css('jhi-lookups div h2#page-heading span')).first();
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

export class LookupsUpdatePage {
  pageTitle = element(by.id('jhi-lookups-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  lkcCodeInput = element(by.id('field_lkcCode'));
  lkcSubCodeInput = element(by.id('field_lkcSubCode'));
  lkcSortInput = element(by.id('field_lkcSort'));
  lkcEnabledIndInput = element(by.id('field_lkcEnabledInd'));
  lkcDescInput = element(by.id('field_lkcDesc'));

  lookUpTypeSelect = element(by.id('field_lookUpType'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLkcCodeInput(lkcCode: string): Promise<void> {
    await this.lkcCodeInput.sendKeys(lkcCode);
  }

  async getLkcCodeInput(): Promise<string> {
    return await this.lkcCodeInput.getAttribute('value');
  }

  async setLkcSubCodeInput(lkcSubCode: string): Promise<void> {
    await this.lkcSubCodeInput.sendKeys(lkcSubCode);
  }

  async getLkcSubCodeInput(): Promise<string> {
    return await this.lkcSubCodeInput.getAttribute('value');
  }

  async setLkcSortInput(lkcSort: string): Promise<void> {
    await this.lkcSortInput.sendKeys(lkcSort);
  }

  async getLkcSortInput(): Promise<string> {
    return await this.lkcSortInput.getAttribute('value');
  }

  getLkcEnabledIndInput(): ElementFinder {
    return this.lkcEnabledIndInput;
  }

  async setLkcDescInput(lkcDesc: string): Promise<void> {
    await this.lkcDescInput.sendKeys(lkcDesc);
  }

  async getLkcDescInput(): Promise<string> {
    return await this.lkcDescInput.getAttribute('value');
  }

  async lookUpTypeSelectLastOption(): Promise<void> {
    await this.lookUpTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async lookUpTypeSelectOption(option: string): Promise<void> {
    await this.lookUpTypeSelect.sendKeys(option);
  }

  getLookUpTypeSelect(): ElementFinder {
    return this.lookUpTypeSelect;
  }

  async getLookUpTypeSelectedOption(): Promise<string> {
    return await this.lookUpTypeSelect.element(by.css('option:checked')).getText();
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

export class LookupsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-lookups-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-lookups'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
