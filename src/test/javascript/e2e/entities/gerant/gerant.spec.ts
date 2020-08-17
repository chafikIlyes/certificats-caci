import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GerantComponentsPage, GerantDeleteDialog, GerantUpdatePage } from './gerant.page-object';

const expect = chai.expect;

describe('Gerant e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let gerantComponentsPage: GerantComponentsPage;
  let gerantUpdatePage: GerantUpdatePage;
  let gerantDeleteDialog: GerantDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Gerants', async () => {
    await navBarPage.goToEntity('gerant');
    gerantComponentsPage = new GerantComponentsPage();
    await browser.wait(ec.visibilityOf(gerantComponentsPage.title), 5000);
    expect(await gerantComponentsPage.getTitle()).to.eq('certificatcaciApp.gerant.home.title');
    await browser.wait(ec.or(ec.visibilityOf(gerantComponentsPage.entities), ec.visibilityOf(gerantComponentsPage.noResult)), 1000);
  });

  it('should load create Gerant page', async () => {
    await gerantComponentsPage.clickOnCreateButton();
    gerantUpdatePage = new GerantUpdatePage();
    expect(await gerantUpdatePage.getPageTitle()).to.eq('certificatcaciApp.gerant.home.createOrEditLabel');
    await gerantUpdatePage.cancel();
  });

  it('should create and save Gerants', async () => {
    const nbButtonsBeforeCreate = await gerantComponentsPage.countDeleteButtons();

    await gerantComponentsPage.clickOnCreateButton();

    await promise.all([
      gerantUpdatePage.setNomInput('nom'),
      gerantUpdatePage.setPrenomInput('prenom'),
      gerantUpdatePage.setEmailInput('email'),
      gerantUpdatePage.setTelInput('tel'),
      gerantUpdatePage.setFaxInput('fax'),
      gerantUpdatePage.setMobileInput('mobile'),
    ]);

    expect(await gerantUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await gerantUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await gerantUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await gerantUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await gerantUpdatePage.getFaxInput()).to.eq('fax', 'Expected Fax value to be equals to fax');
    expect(await gerantUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');

    await gerantUpdatePage.save();
    expect(await gerantUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await gerantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Gerant', async () => {
    const nbButtonsBeforeDelete = await gerantComponentsPage.countDeleteButtons();
    await gerantComponentsPage.clickOnLastDeleteButton();

    gerantDeleteDialog = new GerantDeleteDialog();
    expect(await gerantDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.gerant.delete.question');
    await gerantDeleteDialog.clickOnConfirmButton();

    expect(await gerantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
