import { element, by, ElementFinder } from 'protractor';

export class LookupTypesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-lookup-types div table .btn-danger'));
  title = element.all(by.css('jhi-lookup-types div h2#page-heading span')).first();
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

export class LookupTypesUpdatePage {
  pageTitle = element(by.id('jhi-lookup-types-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  lktTypeInput = element(by.id('field_lktType'));
  lktDescInput = element(by.id('field_lktDesc'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLktTypeInput(lktType: string): Promise<void> {
    await this.lktTypeInput.sendKeys(lktType);
  }

  async getLktTypeInput(): Promise<string> {
    return await this.lktTypeInput.getAttribute('value');
  }

  async setLktDescInput(lktDesc: string): Promise<void> {
    await this.lktDescInput.sendKeys(lktDesc);
  }

  async getLktDescInput(): Promise<string> {
    return await this.lktDescInput.getAttribute('value');
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

export class LookupTypesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-lookupTypes-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-lookupTypes'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
