import { element, by, ElementFinder } from 'protractor';

export class OrganizationsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-organizations div table .btn-danger'));
  title = element.all(by.css('jhi-organizations div h2#page-heading span')).first();
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

export class OrganizationsUpdatePage {
  pageTitle = element(by.id('jhi-organizations-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  orgNbrInput = element(by.id('field_orgNbr'));
  orgNameInput = element(by.id('field_orgName'));
  orgShortNameInput = element(by.id('field_orgShortName'));
  orgAddress1Input = element(by.id('field_orgAddress1'));
  orgAddress2Input = element(by.id('field_orgAddress2'));
  orgCityInput = element(by.id('field_orgCity'));
  orgStateCdInput = element(by.id('field_orgStateCd'));
  orgZipInput = element(by.id('field_orgZip'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrgNbrInput(orgNbr: string): Promise<void> {
    await this.orgNbrInput.sendKeys(orgNbr);
  }

  async getOrgNbrInput(): Promise<string> {
    return await this.orgNbrInput.getAttribute('value');
  }

  async setOrgNameInput(orgName: string): Promise<void> {
    await this.orgNameInput.sendKeys(orgName);
  }

  async getOrgNameInput(): Promise<string> {
    return await this.orgNameInput.getAttribute('value');
  }

  async setOrgShortNameInput(orgShortName: string): Promise<void> {
    await this.orgShortNameInput.sendKeys(orgShortName);
  }

  async getOrgShortNameInput(): Promise<string> {
    return await this.orgShortNameInput.getAttribute('value');
  }

  async setOrgAddress1Input(orgAddress1: string): Promise<void> {
    await this.orgAddress1Input.sendKeys(orgAddress1);
  }

  async getOrgAddress1Input(): Promise<string> {
    return await this.orgAddress1Input.getAttribute('value');
  }

  async setOrgAddress2Input(orgAddress2: string): Promise<void> {
    await this.orgAddress2Input.sendKeys(orgAddress2);
  }

  async getOrgAddress2Input(): Promise<string> {
    return await this.orgAddress2Input.getAttribute('value');
  }

  async setOrgCityInput(orgCity: string): Promise<void> {
    await this.orgCityInput.sendKeys(orgCity);
  }

  async getOrgCityInput(): Promise<string> {
    return await this.orgCityInput.getAttribute('value');
  }

  async setOrgStateCdInput(orgStateCd: string): Promise<void> {
    await this.orgStateCdInput.sendKeys(orgStateCd);
  }

  async getOrgStateCdInput(): Promise<string> {
    return await this.orgStateCdInput.getAttribute('value');
  }

  async setOrgZipInput(orgZip: string): Promise<void> {
    await this.orgZipInput.sendKeys(orgZip);
  }

  async getOrgZipInput(): Promise<string> {
    return await this.orgZipInput.getAttribute('value');
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

export class OrganizationsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-organizations-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-organizations'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
