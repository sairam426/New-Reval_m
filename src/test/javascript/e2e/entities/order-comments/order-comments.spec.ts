import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderCommentsComponentsPage, OrderCommentsDeleteDialog, OrderCommentsUpdatePage } from './order-comments.page-object';

const expect = chai.expect;

describe('OrderComments e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderCommentsComponentsPage: OrderCommentsComponentsPage;
  let orderCommentsUpdatePage: OrderCommentsUpdatePage;
  let orderCommentsDeleteDialog: OrderCommentsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderComments', async () => {
    await navBarPage.goToEntity('order-comments');
    orderCommentsComponentsPage = new OrderCommentsComponentsPage();
    await browser.wait(ec.visibilityOf(orderCommentsComponentsPage.title), 5000);
    expect(await orderCommentsComponentsPage.getTitle()).to.eq('revalApp.orderComments.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(orderCommentsComponentsPage.entities), ec.visibilityOf(orderCommentsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OrderComments page', async () => {
    await orderCommentsComponentsPage.clickOnCreateButton();
    orderCommentsUpdatePage = new OrderCommentsUpdatePage();
    expect(await orderCommentsUpdatePage.getPageTitle()).to.eq('revalApp.orderComments.home.createOrEditLabel');
    await orderCommentsUpdatePage.cancel();
  });

  it('should create and save OrderComments', async () => {
    const nbButtonsBeforeCreate = await orderCommentsComponentsPage.countDeleteButtons();

    await orderCommentsComponentsPage.clickOnCreateButton();

    await promise.all([
      orderCommentsUpdatePage.setOcmCommentTypeCdInput('ocmCommentTypeCd'),
      orderCommentsUpdatePage.setOcmCommentSubTypeCdInput('ocmCommentSubTypeCd'),
      orderCommentsUpdatePage.setOcmCommentByInput('ocmCommentBy'),
      orderCommentsUpdatePage.setOcmCommentDateInput('2000-12-31'),
      orderCommentsUpdatePage.setOcmCommentInput('ocmComment'),
      orderCommentsUpdatePage.orderSelectLastOption()
    ]);

    expect(await orderCommentsUpdatePage.getOcmCommentTypeCdInput()).to.eq(
      'ocmCommentTypeCd',
      'Expected OcmCommentTypeCd value to be equals to ocmCommentTypeCd'
    );
    expect(await orderCommentsUpdatePage.getOcmCommentSubTypeCdInput()).to.eq(
      'ocmCommentSubTypeCd',
      'Expected OcmCommentSubTypeCd value to be equals to ocmCommentSubTypeCd'
    );
    expect(await orderCommentsUpdatePage.getOcmCommentByInput()).to.eq(
      'ocmCommentBy',
      'Expected OcmCommentBy value to be equals to ocmCommentBy'
    );
    expect(await orderCommentsUpdatePage.getOcmCommentDateInput()).to.eq(
      '2000-12-31',
      'Expected ocmCommentDate value to be equals to 2000-12-31'
    );
    const selectedOcmCommentImpInd = orderCommentsUpdatePage.getOcmCommentImpIndInput();
    if (await selectedOcmCommentImpInd.isSelected()) {
      await orderCommentsUpdatePage.getOcmCommentImpIndInput().click();
      expect(await orderCommentsUpdatePage.getOcmCommentImpIndInput().isSelected(), 'Expected ocmCommentImpInd not to be selected').to.be
        .false;
    } else {
      await orderCommentsUpdatePage.getOcmCommentImpIndInput().click();
      expect(await orderCommentsUpdatePage.getOcmCommentImpIndInput().isSelected(), 'Expected ocmCommentImpInd to be selected').to.be.true;
    }
    expect(await orderCommentsUpdatePage.getOcmCommentInput()).to.eq('ocmComment', 'Expected OcmComment value to be equals to ocmComment');

    await orderCommentsUpdatePage.save();
    expect(await orderCommentsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderCommentsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last OrderComments', async () => {
    const nbButtonsBeforeDelete = await orderCommentsComponentsPage.countDeleteButtons();
    await orderCommentsComponentsPage.clickOnLastDeleteButton();

    orderCommentsDeleteDialog = new OrderCommentsDeleteDialog();
    expect(await orderCommentsDeleteDialog.getDialogTitle()).to.eq('revalApp.orderComments.delete.question');
    await orderCommentsDeleteDialog.clickOnConfirmButton();

    expect(await orderCommentsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
