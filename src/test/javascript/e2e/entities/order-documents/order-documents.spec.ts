import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderDocumentsComponentsPage, OrderDocumentsDeleteDialog, OrderDocumentsUpdatePage } from './order-documents.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('OrderDocuments e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderDocumentsComponentsPage: OrderDocumentsComponentsPage;
  let orderDocumentsUpdatePage: OrderDocumentsUpdatePage;
  let orderDocumentsDeleteDialog: OrderDocumentsDeleteDialog;
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderDocuments', async () => {
    await navBarPage.goToEntity('order-documents');
    orderDocumentsComponentsPage = new OrderDocumentsComponentsPage();
    await browser.wait(ec.visibilityOf(orderDocumentsComponentsPage.title), 5000);
    expect(await orderDocumentsComponentsPage.getTitle()).to.eq('revalApp.orderDocuments.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(orderDocumentsComponentsPage.entities), ec.visibilityOf(orderDocumentsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OrderDocuments page', async () => {
    await orderDocumentsComponentsPage.clickOnCreateButton();
    orderDocumentsUpdatePage = new OrderDocumentsUpdatePage();
    expect(await orderDocumentsUpdatePage.getPageTitle()).to.eq('revalApp.orderDocuments.home.createOrEditLabel');
    await orderDocumentsUpdatePage.cancel();
  });

  it('should create and save OrderDocuments', async () => {
    const nbButtonsBeforeCreate = await orderDocumentsComponentsPage.countDeleteButtons();

    await orderDocumentsComponentsPage.clickOnCreateButton();

    await promise.all([
      orderDocumentsUpdatePage.setOdoDocumentMimeTypeCdInput('odoDocumentMimeTypeCd'),
      orderDocumentsUpdatePage.setOdoDocumentTypeCdInput('odoDocumentTypeCd'),
      orderDocumentsUpdatePage.setOdoExternalStorageLinkInput('odoExternalStorageLink'),
      orderDocumentsUpdatePage.setOdoExternalStorageFileNameInput('odoExternalStorageFileName'),
      orderDocumentsUpdatePage.setOdoDocumentDueToCdInput('odoDocumentDueToCd'),
      orderDocumentsUpdatePage.setOdoDocumentDueFromCdInput('odoDocumentDueFromCd'),
      orderDocumentsUpdatePage.setOdoDocumentDueDtInput('2000-12-31'),
      orderDocumentsUpdatePage.setOdoDocumentRcvdDtInput('2000-12-31'),
      orderDocumentsUpdatePage.setOdoDocumentStatusCdInput('odoDocumentStatusCd'),
      orderDocumentsUpdatePage.setOdoDocumentInput(absolutePath),
      orderDocumentsUpdatePage.orderSelectLastOption()
    ]);

    expect(await orderDocumentsUpdatePage.getOdoDocumentMimeTypeCdInput()).to.eq(
      'odoDocumentMimeTypeCd',
      'Expected OdoDocumentMimeTypeCd value to be equals to odoDocumentMimeTypeCd'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentTypeCdInput()).to.eq(
      'odoDocumentTypeCd',
      'Expected OdoDocumentTypeCd value to be equals to odoDocumentTypeCd'
    );
    expect(await orderDocumentsUpdatePage.getOdoExternalStorageLinkInput()).to.eq(
      'odoExternalStorageLink',
      'Expected OdoExternalStorageLink value to be equals to odoExternalStorageLink'
    );
    expect(await orderDocumentsUpdatePage.getOdoExternalStorageFileNameInput()).to.eq(
      'odoExternalStorageFileName',
      'Expected OdoExternalStorageFileName value to be equals to odoExternalStorageFileName'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentDueToCdInput()).to.eq(
      'odoDocumentDueToCd',
      'Expected OdoDocumentDueToCd value to be equals to odoDocumentDueToCd'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentDueFromCdInput()).to.eq(
      'odoDocumentDueFromCd',
      'Expected OdoDocumentDueFromCd value to be equals to odoDocumentDueFromCd'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentDueDtInput()).to.eq(
      '2000-12-31',
      'Expected odoDocumentDueDt value to be equals to 2000-12-31'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentRcvdDtInput()).to.eq(
      '2000-12-31',
      'Expected odoDocumentRcvdDt value to be equals to 2000-12-31'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentStatusCdInput()).to.eq(
      'odoDocumentStatusCd',
      'Expected OdoDocumentStatusCd value to be equals to odoDocumentStatusCd'
    );
    expect(await orderDocumentsUpdatePage.getOdoDocumentInput()).to.endsWith(
      fileNameToUpload,
      'Expected OdoDocument value to be end with ' + fileNameToUpload
    );

    await orderDocumentsUpdatePage.save();
    expect(await orderDocumentsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderDocumentsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OrderDocuments', async () => {
    const nbButtonsBeforeDelete = await orderDocumentsComponentsPage.countDeleteButtons();
    await orderDocumentsComponentsPage.clickOnLastDeleteButton();

    orderDocumentsDeleteDialog = new OrderDocumentsDeleteDialog();
    expect(await orderDocumentsDeleteDialog.getDialogTitle()).to.eq('revalApp.orderDocuments.delete.question');
    await orderDocumentsDeleteDialog.clickOnConfirmButton();

    expect(await orderDocumentsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
