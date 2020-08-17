import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProduitComponentsPage, ProduitDeleteDialog, ProduitUpdatePage } from './produit.page-object';

const expect = chai.expect;

describe('Produit e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let produitComponentsPage: ProduitComponentsPage;
  let produitUpdatePage: ProduitUpdatePage;
  let produitDeleteDialog: ProduitDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Produits', async () => {
    await navBarPage.goToEntity('produit');
    produitComponentsPage = new ProduitComponentsPage();
    await browser.wait(ec.visibilityOf(produitComponentsPage.title), 5000);
    expect(await produitComponentsPage.getTitle()).to.eq('certificatcaciApp.produit.home.title');
    await browser.wait(ec.or(ec.visibilityOf(produitComponentsPage.entities), ec.visibilityOf(produitComponentsPage.noResult)), 1000);
  });

  it('should load create Produit page', async () => {
    await produitComponentsPage.clickOnCreateButton();
    produitUpdatePage = new ProduitUpdatePage();
    expect(await produitUpdatePage.getPageTitle()).to.eq('certificatcaciApp.produit.home.createOrEditLabel');
    await produitUpdatePage.cancel();
  });

  it('should create and save Produits', async () => {
    const nbButtonsBeforeCreate = await produitComponentsPage.countDeleteButtons();

    await produitComponentsPage.clickOnCreateButton();

    await promise.all([
      produitUpdatePage.setNomProduitInput('nomProduit'),
      produitUpdatePage.setDescriptionInput('description'),
      produitUpdatePage.setMarqueInput('marque'),
      produitUpdatePage.setHsCodeInput('hsCode'),
      produitUpdatePage.setQteInput('qte'),
      produitUpdatePage.setUniteMesureInput('uniteMesure'),
      produitUpdatePage.setNbrColiInput('nbrColi'),
      produitUpdatePage.setPoidNetInput('poidNet'),
      produitUpdatePage.setPoidReelInput('poidReel'),
      produitUpdatePage.setDateFactureInput('2000-12-31'),
      produitUpdatePage.setNumeroFactureInput('numeroFacture'),
      produitUpdatePage.entrepriseSelectLastOption(),
      produitUpdatePage.certificatOrigineSelectLastOption(),
    ]);

    expect(await produitUpdatePage.getNomProduitInput()).to.eq('nomProduit', 'Expected NomProduit value to be equals to nomProduit');
    expect(await produitUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await produitUpdatePage.getMarqueInput()).to.eq('marque', 'Expected Marque value to be equals to marque');
    expect(await produitUpdatePage.getHsCodeInput()).to.eq('hsCode', 'Expected HsCode value to be equals to hsCode');
    expect(await produitUpdatePage.getQteInput()).to.eq('qte', 'Expected Qte value to be equals to qte');
    expect(await produitUpdatePage.getUniteMesureInput()).to.eq('uniteMesure', 'Expected UniteMesure value to be equals to uniteMesure');
    expect(await produitUpdatePage.getNbrColiInput()).to.eq('nbrColi', 'Expected NbrColi value to be equals to nbrColi');
    expect(await produitUpdatePage.getPoidNetInput()).to.eq('poidNet', 'Expected PoidNet value to be equals to poidNet');
    expect(await produitUpdatePage.getPoidReelInput()).to.eq('poidReel', 'Expected PoidReel value to be equals to poidReel');
    expect(await produitUpdatePage.getDateFactureInput()).to.eq('2000-12-31', 'Expected dateFacture value to be equals to 2000-12-31');
    expect(await produitUpdatePage.getNumeroFactureInput()).to.eq(
      'numeroFacture',
      'Expected NumeroFacture value to be equals to numeroFacture'
    );

    await produitUpdatePage.save();
    expect(await produitUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Produit', async () => {
    const nbButtonsBeforeDelete = await produitComponentsPage.countDeleteButtons();
    await produitComponentsPage.clickOnLastDeleteButton();

    produitDeleteDialog = new ProduitDeleteDialog();
    expect(await produitDeleteDialog.getDialogTitle()).to.eq('certificatcaciApp.produit.delete.question');
    await produitDeleteDialog.clickOnConfirmButton();

    expect(await produitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
