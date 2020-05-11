import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  TransactionCodeParamsComponentsPage,
  TransactionCodeParamsDeleteDialog,
  TransactionCodeParamsUpdatePage
} from './transaction-code-params.page-object';

const expect = chai.expect;

describe('TransactionCodeParams e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let transactionCodeParamsComponentsPage: TransactionCodeParamsComponentsPage;
  let transactionCodeParamsUpdatePage: TransactionCodeParamsUpdatePage;
  let transactionCodeParamsDeleteDialog: TransactionCodeParamsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TransactionCodeParams', async () => {
    await navBarPage.goToEntity('transaction-code-params');
    transactionCodeParamsComponentsPage = new TransactionCodeParamsComponentsPage();
    await browser.wait(ec.visibilityOf(transactionCodeParamsComponentsPage.title), 5000);
    expect(await transactionCodeParamsComponentsPage.getTitle()).to.eq('revalApp.transactionCodeParams.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(transactionCodeParamsComponentsPage.entities), ec.visibilityOf(transactionCodeParamsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TransactionCodeParams page', async () => {
    await transactionCodeParamsComponentsPage.clickOnCreateButton();
    transactionCodeParamsUpdatePage = new TransactionCodeParamsUpdatePage();
    expect(await transactionCodeParamsUpdatePage.getPageTitle()).to.eq('revalApp.transactionCodeParams.home.createOrEditLabel');
    await transactionCodeParamsUpdatePage.cancel();
  });

  it('should create and save TransactionCodeParams', async () => {
    const nbButtonsBeforeCreate = await transactionCodeParamsComponentsPage.countDeleteButtons();

    await transactionCodeParamsComponentsPage.clickOnCreateButton();

    await promise.all([
      transactionCodeParamsUpdatePage.setTcpTcdCodeInput('tcpTcdCode'),
      transactionCodeParamsUpdatePage.setTcpParamNameInput('tcpParamName'),
      transactionCodeParamsUpdatePage.setTcpParamDescInput('tcpParamDesc'),
      transactionCodeParamsUpdatePage.setTcpParamDataTypeCdInput('tcpParamDataTypeCd'),
      transactionCodeParamsUpdatePage.setTcpParamLengthInput('tcpParamLength'),
      transactionCodeParamsUpdatePage.setTcpDefaultValueInput('tcpDefaultValue'),
      transactionCodeParamsUpdatePage.setTcpParamLktTypeInput('tcpParamLktType'),
      transactionCodeParamsUpdatePage.transactionCodeSelectLastOption()
    ]);

    expect(await transactionCodeParamsUpdatePage.getTcpTcdCodeInput()).to.eq(
      'tcpTcdCode',
      'Expected TcpTcdCode value to be equals to tcpTcdCode'
    );
    expect(await transactionCodeParamsUpdatePage.getTcpParamNameInput()).to.eq(
      'tcpParamName',
      'Expected TcpParamName value to be equals to tcpParamName'
    );
    expect(await transactionCodeParamsUpdatePage.getTcpParamDescInput()).to.eq(
      'tcpParamDesc',
      'Expected TcpParamDesc value to be equals to tcpParamDesc'
    );
    const selectedTcpEnabledInd = transactionCodeParamsUpdatePage.getTcpEnabledIndInput();
    if (await selectedTcpEnabledInd.isSelected()) {
      await transactionCodeParamsUpdatePage.getTcpEnabledIndInput().click();
      expect(await transactionCodeParamsUpdatePage.getTcpEnabledIndInput().isSelected(), 'Expected tcpEnabledInd not to be selected').to.be
        .false;
    } else {
      await transactionCodeParamsUpdatePage.getTcpEnabledIndInput().click();
      expect(await transactionCodeParamsUpdatePage.getTcpEnabledIndInput().isSelected(), 'Expected tcpEnabledInd to be selected').to.be
        .true;
    }
    expect(await transactionCodeParamsUpdatePage.getTcpParamDataTypeCdInput()).to.eq(
      'tcpParamDataTypeCd',
      'Expected TcpParamDataTypeCd value to be equals to tcpParamDataTypeCd'
    );
    expect(await transactionCodeParamsUpdatePage.getTcpParamLengthInput()).to.eq(
      'tcpParamLength',
      'Expected TcpParamLength value to be equals to tcpParamLength'
    );
    expect(await transactionCodeParamsUpdatePage.getTcpDefaultValueInput()).to.eq(
      'tcpDefaultValue',
      'Expected TcpDefaultValue value to be equals to tcpDefaultValue'
    );
    const selectedTcpParamLovValidationInd = transactionCodeParamsUpdatePage.getTcpParamLovValidationIndInput();
    if (await selectedTcpParamLovValidationInd.isSelected()) {
      await transactionCodeParamsUpdatePage.getTcpParamLovValidationIndInput().click();
      expect(
        await transactionCodeParamsUpdatePage.getTcpParamLovValidationIndInput().isSelected(),
        'Expected tcpParamLovValidationInd not to be selected'
      ).to.be.false;
    } else {
      await transactionCodeParamsUpdatePage.getTcpParamLovValidationIndInput().click();
      expect(
        await transactionCodeParamsUpdatePage.getTcpParamLovValidationIndInput().isSelected(),
        'Expected tcpParamLovValidationInd to be selected'
      ).to.be.true;
    }
    expect(await transactionCodeParamsUpdatePage.getTcpParamLktTypeInput()).to.eq(
      'tcpParamLktType',
      'Expected TcpParamLktType value to be equals to tcpParamLktType'
    );

    await transactionCodeParamsUpdatePage.save();
    expect(await transactionCodeParamsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await transactionCodeParamsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TransactionCodeParams', async () => {
    const nbButtonsBeforeDelete = await transactionCodeParamsComponentsPage.countDeleteButtons();
    await transactionCodeParamsComponentsPage.clickOnLastDeleteButton();

    transactionCodeParamsDeleteDialog = new TransactionCodeParamsDeleteDialog();
    expect(await transactionCodeParamsDeleteDialog.getDialogTitle()).to.eq('revalApp.transactionCodeParams.delete.question');
    await transactionCodeParamsDeleteDialog.clickOnConfirmButton();

    expect(await transactionCodeParamsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
