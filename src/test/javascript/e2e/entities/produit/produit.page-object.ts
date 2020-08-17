import { element, by, ElementFinder } from 'protractor';

export class ProduitComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-produit div table .btn-danger'));
  title = element.all(by.css('jhi-produit div h2#page-heading span')).first();
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

export class ProduitUpdatePage {
  pageTitle = element(by.id('jhi-produit-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomProduitInput = element(by.id('field_nomProduit'));
  descriptionInput = element(by.id('field_description'));
  marqueInput = element(by.id('field_marque'));
  hsCodeInput = element(by.id('field_hsCode'));
  qteInput = element(by.id('field_qte'));
  uniteMesureInput = element(by.id('field_uniteMesure'));
  nbrColiInput = element(by.id('field_nbrColi'));
  poidNetInput = element(by.id('field_poidNet'));
  poidReelInput = element(by.id('field_poidReel'));
  dateFactureInput = element(by.id('field_dateFacture'));
  numeroFactureInput = element(by.id('field_numeroFacture'));

  entrepriseSelect = element(by.id('field_entreprise'));
  certificatOrigineSelect = element(by.id('field_certificatOrigine'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomProduitInput(nomProduit: string): Promise<void> {
    await this.nomProduitInput.sendKeys(nomProduit);
  }

  async getNomProduitInput(): Promise<string> {
    return await this.nomProduitInput.getAttribute('value');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setMarqueInput(marque: string): Promise<void> {
    await this.marqueInput.sendKeys(marque);
  }

  async getMarqueInput(): Promise<string> {
    return await this.marqueInput.getAttribute('value');
  }

  async setHsCodeInput(hsCode: string): Promise<void> {
    await this.hsCodeInput.sendKeys(hsCode);
  }

  async getHsCodeInput(): Promise<string> {
    return await this.hsCodeInput.getAttribute('value');
  }

  async setQteInput(qte: string): Promise<void> {
    await this.qteInput.sendKeys(qte);
  }

  async getQteInput(): Promise<string> {
    return await this.qteInput.getAttribute('value');
  }

  async setUniteMesureInput(uniteMesure: string): Promise<void> {
    await this.uniteMesureInput.sendKeys(uniteMesure);
  }

  async getUniteMesureInput(): Promise<string> {
    return await this.uniteMesureInput.getAttribute('value');
  }

  async setNbrColiInput(nbrColi: string): Promise<void> {
    await this.nbrColiInput.sendKeys(nbrColi);
  }

  async getNbrColiInput(): Promise<string> {
    return await this.nbrColiInput.getAttribute('value');
  }

  async setPoidNetInput(poidNet: string): Promise<void> {
    await this.poidNetInput.sendKeys(poidNet);
  }

  async getPoidNetInput(): Promise<string> {
    return await this.poidNetInput.getAttribute('value');
  }

  async setPoidReelInput(poidReel: string): Promise<void> {
    await this.poidReelInput.sendKeys(poidReel);
  }

  async getPoidReelInput(): Promise<string> {
    return await this.poidReelInput.getAttribute('value');
  }

  async setDateFactureInput(dateFacture: string): Promise<void> {
    await this.dateFactureInput.sendKeys(dateFacture);
  }

  async getDateFactureInput(): Promise<string> {
    return await this.dateFactureInput.getAttribute('value');
  }

  async setNumeroFactureInput(numeroFacture: string): Promise<void> {
    await this.numeroFactureInput.sendKeys(numeroFacture);
  }

  async getNumeroFactureInput(): Promise<string> {
    return await this.numeroFactureInput.getAttribute('value');
  }

  async entrepriseSelectLastOption(): Promise<void> {
    await this.entrepriseSelect.all(by.tagName('option')).last().click();
  }

  async entrepriseSelectOption(option: string): Promise<void> {
    await this.entrepriseSelect.sendKeys(option);
  }

  getEntrepriseSelect(): ElementFinder {
    return this.entrepriseSelect;
  }

  async getEntrepriseSelectedOption(): Promise<string> {
    return await this.entrepriseSelect.element(by.css('option:checked')).getText();
  }

  async certificatOrigineSelectLastOption(): Promise<void> {
    await this.certificatOrigineSelect.all(by.tagName('option')).last().click();
  }

  async certificatOrigineSelectOption(option: string): Promise<void> {
    await this.certificatOrigineSelect.sendKeys(option);
  }

  getCertificatOrigineSelect(): ElementFinder {
    return this.certificatOrigineSelect;
  }

  async getCertificatOrigineSelectedOption(): Promise<string> {
    return await this.certificatOrigineSelect.element(by.css('option:checked')).getText();
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

export class ProduitDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-produit-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-produit'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
