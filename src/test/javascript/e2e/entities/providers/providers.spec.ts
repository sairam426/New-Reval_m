import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProvidersComponentsPage, ProvidersDeleteDialog, ProvidersUpdatePage } from './providers.page-object';

const expect = chai.expect;

describe('Providers e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let providersComponentsPage: ProvidersComponentsPage;
  let providersUpdatePage: ProvidersUpdatePage;
  let providersDeleteDialog: ProvidersDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Providers', async () => {
    await navBarPage.goToEntity('providers');
    providersComponentsPage = new ProvidersComponentsPage();
    await browser.wait(ec.visibilityOf(providersComponentsPage.title), 5000);
    expect(await providersComponentsPage.getTitle()).to.eq('revalApp.providers.home.title');
    await browser.wait(ec.or(ec.visibilityOf(providersComponentsPage.entities), ec.visibilityOf(providersComponentsPage.noResult)), 1000);
  });

  it('should load create Providers page', async () => {
    await providersComponentsPage.clickOnCreateButton();
    providersUpdatePage = new ProvidersUpdatePage();
    expect(await providersUpdatePage.getPageTitle()).to.eq('revalApp.providers.home.createOrEditLabel');
    await providersUpdatePage.cancel();
  });

  it('should create and save Providers', async () => {
    const nbButtonsBeforeCreate = await providersComponentsPage.countDeleteButtons();

    await providersComponentsPage.clickOnCreateButton();

    await promise.all([
      providersUpdatePage.setProNbrInput('proNbr'),
      providersUpdatePage.setProNameInput('proName'),
      providersUpdatePage.setProShortNameInput('proShortName'),
      providersUpdatePage.setProStatusCdInput('proStatusCd'),
      providersUpdatePage.setProTypeCdInput('proTypeCd'),
      providersUpdatePage.setProGroupCdInput('proGroupCd'),
      providersUpdatePage.setProLicenseNbrInput('proLicenseNbr'),
      providersUpdatePage.setProLicenseStatusCdInput('proLicenseStatusCd'),
      providersUpdatePage.setProAddress1Input('proAddress1'),
      providersUpdatePage.setProAddress2Input('proAddress2'),
      providersUpdatePage.setProCityInput('proCity'),
      providersUpdatePage.setProStateCdInput('proStateCd'),
      providersUpdatePage.setProZipInput('5')
    ]);

    expect(await providersUpdatePage.getProNbrInput()).to.eq('proNbr', 'Expected ProNbr value to be equals to proNbr');
    expect(await providersUpdatePage.getProNameInput()).to.eq('proName', 'Expected ProName value to be equals to proName');
    expect(await providersUpdatePage.getProShortNameInput()).to.eq(
      'proShortName',
      'Expected ProShortName value to be equals to proShortName'
    );
    expect(await providersUpdatePage.getProStatusCdInput()).to.eq('proStatusCd', 'Expected ProStatusCd value to be equals to proStatusCd');
    expect(await providersUpdatePage.getProTypeCdInput()).to.eq('proTypeCd', 'Expected ProTypeCd value to be equals to proTypeCd');
    expect(await providersUpdatePage.getProGroupCdInput()).to.eq('proGroupCd', 'Expected ProGroupCd value to be equals to proGroupCd');
    expect(await providersUpdatePage.getProLicenseNbrInput()).to.eq(
      'proLicenseNbr',
      'Expected ProLicenseNbr value to be equals to proLicenseNbr'
    );
    expect(await providersUpdatePage.getProLicenseStatusCdInput()).to.eq(
      'proLicenseStatusCd',
      'Expected ProLicenseStatusCd value to be equals to proLicenseStatusCd'
    );
    expect(await providersUpdatePage.getProAddress1Input()).to.eq('proAddress1', 'Expected ProAddress1 value to be equals to proAddress1');
    expect(await providersUpdatePage.getProAddress2Input()).to.eq('proAddress2', 'Expected ProAddress2 value to be equals to proAddress2');
    expect(await providersUpdatePage.getProCityInput()).to.eq('proCity', 'Expected ProCity value to be equals to proCity');
    expect(await providersUpdatePage.getProStateCdInput()).to.eq('proStateCd', 'Expected ProStateCd value to be equals to proStateCd');
    expect(await providersUpdatePage.getProZipInput()).to.eq('5', 'Expected proZip value to be equals to 5');

    await providersUpdatePage.save();
    expect(await providersUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await providersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Providers', async () => {
    const nbButtonsBeforeDelete = await providersComponentsPage.countDeleteButtons();
    await providersComponentsPage.clickOnLastDeleteButton();

    providersDeleteDialog = new ProvidersDeleteDialog();
    expect(await providersDeleteDialog.getDialogTitle()).to.eq('revalApp.providers.delete.question');
    await providersDeleteDialog.clickOnConfirmButton();

    expect(await providersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
