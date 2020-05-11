import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemAccessComponentsPage, SystemAccessDeleteDialog, SystemAccessUpdatePage } from './system-access.page-object';

const expect = chai.expect;

describe('SystemAccess e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let systemAccessComponentsPage: SystemAccessComponentsPage;
  let systemAccessUpdatePage: SystemAccessUpdatePage;
  let systemAccessDeleteDialog: SystemAccessDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SystemAccesses', async () => {
    await navBarPage.goToEntity('system-access');
    systemAccessComponentsPage = new SystemAccessComponentsPage();
    await browser.wait(ec.visibilityOf(systemAccessComponentsPage.title), 5000);
    expect(await systemAccessComponentsPage.getTitle()).to.eq('revalApp.systemAccess.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(systemAccessComponentsPage.entities), ec.visibilityOf(systemAccessComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SystemAccess page', async () => {
    await systemAccessComponentsPage.clickOnCreateButton();
    systemAccessUpdatePage = new SystemAccessUpdatePage();
    expect(await systemAccessUpdatePage.getPageTitle()).to.eq('revalApp.systemAccess.home.createOrEditLabel');
    await systemAccessUpdatePage.cancel();
  });

  it('should create and save SystemAccesses', async () => {
    const nbButtonsBeforeCreate = await systemAccessComponentsPage.countDeleteButtons();

    await systemAccessComponentsPage.clickOnCreateButton();

    await promise.all([
      systemAccessUpdatePage.setSacAccessKeyInput('sacAccessKey'),
      systemAccessUpdatePage.setSacAccessTypeCdInput('sacAccessTypeCd'),
      systemAccessUpdatePage.setSacAccessValueInput('sacAccessValue')
    ]);

    expect(await systemAccessUpdatePage.getSacAccessKeyInput()).to.eq(
      'sacAccessKey',
      'Expected SacAccessKey value to be equals to sacAccessKey'
    );
    expect(await systemAccessUpdatePage.getSacAccessTypeCdInput()).to.eq(
      'sacAccessTypeCd',
      'Expected SacAccessTypeCd value to be equals to sacAccessTypeCd'
    );
    expect(await systemAccessUpdatePage.getSacAccessValueInput()).to.eq(
      'sacAccessValue',
      'Expected SacAccessValue value to be equals to sacAccessValue'
    );
    const selectedSacAllowedInd = systemAccessUpdatePage.getSacAllowedIndInput();
    if (await selectedSacAllowedInd.isSelected()) {
      await systemAccessUpdatePage.getSacAllowedIndInput().click();
      expect(await systemAccessUpdatePage.getSacAllowedIndInput().isSelected(), 'Expected sacAllowedInd not to be selected').to.be.false;
    } else {
      await systemAccessUpdatePage.getSacAllowedIndInput().click();
      expect(await systemAccessUpdatePage.getSacAllowedIndInput().isSelected(), 'Expected sacAllowedInd to be selected').to.be.true;
    }

    await systemAccessUpdatePage.save();
    expect(await systemAccessUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await systemAccessComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SystemAccess', async () => {
    const nbButtonsBeforeDelete = await systemAccessComponentsPage.countDeleteButtons();
    await systemAccessComponentsPage.clickOnLastDeleteButton();

    systemAccessDeleteDialog = new SystemAccessDeleteDialog();
    expect(await systemAccessDeleteDialog.getDialogTitle()).to.eq('revalApp.systemAccess.delete.question');
    await systemAccessDeleteDialog.clickOnConfirmButton();

    expect(await systemAccessComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
