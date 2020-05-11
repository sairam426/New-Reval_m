import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BranchesComponentsPage, BranchesDeleteDialog, BranchesUpdatePage } from './branches.page-object';

const expect = chai.expect;

describe('Branches e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let branchesComponentsPage: BranchesComponentsPage;
  let branchesUpdatePage: BranchesUpdatePage;
  let branchesDeleteDialog: BranchesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Branches', async () => {
    await navBarPage.goToEntity('branches');
    branchesComponentsPage = new BranchesComponentsPage();
    await browser.wait(ec.visibilityOf(branchesComponentsPage.title), 5000);
    expect(await branchesComponentsPage.getTitle()).to.eq('revalApp.branches.home.title');
    await browser.wait(ec.or(ec.visibilityOf(branchesComponentsPage.entities), ec.visibilityOf(branchesComponentsPage.noResult)), 1000);
  });

  it('should load create Branches page', async () => {
    await branchesComponentsPage.clickOnCreateButton();
    branchesUpdatePage = new BranchesUpdatePage();
    expect(await branchesUpdatePage.getPageTitle()).to.eq('revalApp.branches.home.createOrEditLabel');
    await branchesUpdatePage.cancel();
  });

  it('should create and save Branches', async () => {
    const nbButtonsBeforeCreate = await branchesComponentsPage.countDeleteButtons();

    await branchesComponentsPage.clickOnCreateButton();

    await promise.all([
      branchesUpdatePage.setBrnNbrInput('brnNbr'),
      branchesUpdatePage.setBrnNameInput('brnName'),
      branchesUpdatePage.setBrnCodeInput('brnCode'),
      branchesUpdatePage.setBrnShortNameInput('brnShortName'),
      branchesUpdatePage.setBrnAddress1Input('brnAddress1'),
      branchesUpdatePage.setBrnAddress2Input('brnAddress2'),
      branchesUpdatePage.setBrnCityInput('brnCity'),
      branchesUpdatePage.setBrnStateCdInput('brnStateCd'),
      branchesUpdatePage.setBrnZipInput('brnZip'),
      branchesUpdatePage.setBrnRegioinCdInput('brnRegioinCd'),
      branchesUpdatePage.setBrnServiceTypeCdInput('brnServiceTypeCd'),
      branchesUpdatePage.companySelectLastOption()
    ]);

    expect(await branchesUpdatePage.getBrnNbrInput()).to.eq('brnNbr', 'Expected BrnNbr value to be equals to brnNbr');
    expect(await branchesUpdatePage.getBrnNameInput()).to.eq('brnName', 'Expected BrnName value to be equals to brnName');
    expect(await branchesUpdatePage.getBrnCodeInput()).to.eq('brnCode', 'Expected BrnCode value to be equals to brnCode');
    expect(await branchesUpdatePage.getBrnShortNameInput()).to.eq(
      'brnShortName',
      'Expected BrnShortName value to be equals to brnShortName'
    );
    expect(await branchesUpdatePage.getBrnAddress1Input()).to.eq('brnAddress1', 'Expected BrnAddress1 value to be equals to brnAddress1');
    expect(await branchesUpdatePage.getBrnAddress2Input()).to.eq('brnAddress2', 'Expected BrnAddress2 value to be equals to brnAddress2');
    expect(await branchesUpdatePage.getBrnCityInput()).to.eq('brnCity', 'Expected BrnCity value to be equals to brnCity');
    expect(await branchesUpdatePage.getBrnStateCdInput()).to.eq('brnStateCd', 'Expected BrnStateCd value to be equals to brnStateCd');
    expect(await branchesUpdatePage.getBrnZipInput()).to.eq('brnZip', 'Expected BrnZip value to be equals to brnZip');
    const selectedBrnEnabledInd = branchesUpdatePage.getBrnEnabledIndInput();
    if (await selectedBrnEnabledInd.isSelected()) {
      await branchesUpdatePage.getBrnEnabledIndInput().click();
      expect(await branchesUpdatePage.getBrnEnabledIndInput().isSelected(), 'Expected brnEnabledInd not to be selected').to.be.false;
    } else {
      await branchesUpdatePage.getBrnEnabledIndInput().click();
      expect(await branchesUpdatePage.getBrnEnabledIndInput().isSelected(), 'Expected brnEnabledInd to be selected').to.be.true;
    }
    expect(await branchesUpdatePage.getBrnRegioinCdInput()).to.eq(
      'brnRegioinCd',
      'Expected BrnRegioinCd value to be equals to brnRegioinCd'
    );
    expect(await branchesUpdatePage.getBrnServiceTypeCdInput()).to.eq(
      'brnServiceTypeCd',
      'Expected BrnServiceTypeCd value to be equals to brnServiceTypeCd'
    );

    await branchesUpdatePage.save();
    expect(await branchesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await branchesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Branches', async () => {
    const nbButtonsBeforeDelete = await branchesComponentsPage.countDeleteButtons();
    await branchesComponentsPage.clickOnLastDeleteButton();

    branchesDeleteDialog = new BranchesDeleteDialog();
    expect(await branchesDeleteDialog.getDialogTitle()).to.eq('revalApp.branches.delete.question');
    await branchesDeleteDialog.clickOnConfirmButton();

    expect(await branchesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
