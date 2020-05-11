import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrganizationsComponentsPage, OrganizationsDeleteDialog, OrganizationsUpdatePage } from './organizations.page-object';

const expect = chai.expect;

describe('Organizations e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let organizationsComponentsPage: OrganizationsComponentsPage;
  let organizationsUpdatePage: OrganizationsUpdatePage;
  let organizationsDeleteDialog: OrganizationsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Organizations', async () => {
    await navBarPage.goToEntity('organizations');
    organizationsComponentsPage = new OrganizationsComponentsPage();
    await browser.wait(ec.visibilityOf(organizationsComponentsPage.title), 5000);
    expect(await organizationsComponentsPage.getTitle()).to.eq('revalApp.organizations.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(organizationsComponentsPage.entities), ec.visibilityOf(organizationsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Organizations page', async () => {
    await organizationsComponentsPage.clickOnCreateButton();
    organizationsUpdatePage = new OrganizationsUpdatePage();
    expect(await organizationsUpdatePage.getPageTitle()).to.eq('revalApp.organizations.home.createOrEditLabel');
    await organizationsUpdatePage.cancel();
  });

  it('should create and save Organizations', async () => {
    const nbButtonsBeforeCreate = await organizationsComponentsPage.countDeleteButtons();

    await organizationsComponentsPage.clickOnCreateButton();

    await promise.all([
      organizationsUpdatePage.setOrgNbrInput('orgNbr'),
      organizationsUpdatePage.setOrgNameInput('orgName'),
      organizationsUpdatePage.setOrgShortNameInput('orgShortName'),
      organizationsUpdatePage.setOrgAddress1Input('orgAddress1'),
      organizationsUpdatePage.setOrgAddress2Input('orgAddress2'),
      organizationsUpdatePage.setOrgCityInput('orgCity'),
      organizationsUpdatePage.setOrgStateCdInput('orgStateCd'),
      organizationsUpdatePage.setOrgZipInput('orgZip')
    ]);

    expect(await organizationsUpdatePage.getOrgNbrInput()).to.eq('orgNbr', 'Expected OrgNbr value to be equals to orgNbr');
    expect(await organizationsUpdatePage.getOrgNameInput()).to.eq('orgName', 'Expected OrgName value to be equals to orgName');
    expect(await organizationsUpdatePage.getOrgShortNameInput()).to.eq(
      'orgShortName',
      'Expected OrgShortName value to be equals to orgShortName'
    );
    expect(await organizationsUpdatePage.getOrgAddress1Input()).to.eq(
      'orgAddress1',
      'Expected OrgAddress1 value to be equals to orgAddress1'
    );
    expect(await organizationsUpdatePage.getOrgAddress2Input()).to.eq(
      'orgAddress2',
      'Expected OrgAddress2 value to be equals to orgAddress2'
    );
    expect(await organizationsUpdatePage.getOrgCityInput()).to.eq('orgCity', 'Expected OrgCity value to be equals to orgCity');
    expect(await organizationsUpdatePage.getOrgStateCdInput()).to.eq('orgStateCd', 'Expected OrgStateCd value to be equals to orgStateCd');
    expect(await organizationsUpdatePage.getOrgZipInput()).to.eq('orgZip', 'Expected OrgZip value to be equals to orgZip');

    await organizationsUpdatePage.save();
    expect(await organizationsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await organizationsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Organizations', async () => {
    const nbButtonsBeforeDelete = await organizationsComponentsPage.countDeleteButtons();
    await organizationsComponentsPage.clickOnLastDeleteButton();

    organizationsDeleteDialog = new OrganizationsDeleteDialog();
    expect(await organizationsDeleteDialog.getDialogTitle()).to.eq('revalApp.organizations.delete.question');
    await organizationsDeleteDialog.clickOnConfirmButton();

    expect(await organizationsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
