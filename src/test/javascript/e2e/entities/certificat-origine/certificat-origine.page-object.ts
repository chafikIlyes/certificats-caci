import { element, by, ElementFinder } from 'protractor';

export class CertificatOrigineComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-certificat-origine div table .btn-danger'));
  title = element.all(by.css('jhi-certificat-origine div h2#page-heading span')).first();
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

export class CertificatOrigineUpdatePage {
  pageTitle = element(by.id('jhi-certificat-origine-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomExportateurInput = element(by.id('field_nomExportateur'));
  adresseExportateurInput = element(by.id('field_adresseExportateur'));
  nomProducteurInput = element(by.id('field_nomProducteur'));
  adresseProducteurInput = element(by.id('field_adresseProducteur'));
  nomImportateurInput = element(by.id('field_nomImportateur'));
  adresseImportateurInput = element(by.id('field_adresseImportateur'));
  payOrigineInput = element(by.id('field_payOrigine'));
  autreOrigineInput = element(by.id('field_autreOrigine'));
  payAutreOrigineInput = element(by.id('field_payAutreOrigine'));
  detailTransportInput = element(by.id('field_detailTransport'));
  observationInput = element(by.id('field_observation'));
  etatCertificatInput = element(by.id('field_etatCertificat'));
  typeCertificatsInput = element(by.id('field_typeCertificats'));
  nomSignataireInput = element(by.id('field_nomSignataire'));
  prenomSignataireInput = element(by.id('field_prenomSignataire'));
  emailSignataireInput = element(by.id('field_emailSignataire'));

  importateurSelect = element(by.id('field_importateur'));
  entrepriseSelect = element(by.id('field_entreprise'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomExportateurInput(nomExportateur: string): Promise<void> {
    await this.nomExportateurInput.sendKeys(nomExportateur);
  }

  async getNomExportateurInput(): Promise<string> {
    return await this.nomExportateurInput.getAttribute('value');
  }

  async setAdresseExportateurInput(adresseExportateur: string): Promise<void> {
    await this.adresseExportateurInput.sendKeys(adresseExportateur);
  }

  async getAdresseExportateurInput(): Promise<string> {
    return await this.adresseExportateurInput.getAttribute('value');
  }

  async setNomProducteurInput(nomProducteur: string): Promise<void> {
    await this.nomProducteurInput.sendKeys(nomProducteur);
  }

  async getNomProducteurInput(): Promise<string> {
    return await this.nomProducteurInput.getAttribute('value');
  }

  async setAdresseProducteurInput(adresseProducteur: string): Promise<void> {
    await this.adresseProducteurInput.sendKeys(adresseProducteur);
  }

  async getAdresseProducteurInput(): Promise<string> {
    return await this.adresseProducteurInput.getAttribute('value');
  }

  async setNomImportateurInput(nomImportateur: string): Promise<void> {
    await this.nomImportateurInput.sendKeys(nomImportateur);
  }

  async getNomImportateurInput(): Promise<string> {
    return await this.nomImportateurInput.getAttribute('value');
  }

  async setAdresseImportateurInput(adresseImportateur: string): Promise<void> {
    await this.adresseImportateurInput.sendKeys(adresseImportateur);
  }

  async getAdresseImportateurInput(): Promise<string> {
    return await this.adresseImportateurInput.getAttribute('value');
  }

  async setPayOrigineInput(payOrigine: string): Promise<void> {
    await this.payOrigineInput.sendKeys(payOrigine);
  }

  async getPayOrigineInput(): Promise<string> {
    return await this.payOrigineInput.getAttribute('value');
  }

  getAutreOrigineInput(): ElementFinder {
    return this.autreOrigineInput;
  }

  async setPayAutreOrigineInput(payAutreOrigine: string): Promise<void> {
    await this.payAutreOrigineInput.sendKeys(payAutreOrigine);
  }

  async getPayAutreOrigineInput(): Promise<string> {
    return await this.payAutreOrigineInput.getAttribute('value');
  }

  async setDetailTransportInput(detailTransport: string): Promise<void> {
    await this.detailTransportInput.sendKeys(detailTransport);
  }

  async getDetailTransportInput(): Promise<string> {
    return await this.detailTransportInput.getAttribute('value');
  }

  async setObservationInput(observation: string): Promise<void> {
    await this.observationInput.sendKeys(observation);
  }

  async getObservationInput(): Promise<string> {
    return await this.observationInput.getAttribute('value');
  }

  async setEtatCertificatInput(etatCertificat: string): Promise<void> {
    await this.etatCertificatInput.sendKeys(etatCertificat);
  }

  async getEtatCertificatInput(): Promise<string> {
    return await this.etatCertificatInput.getAttribute('value');
  }

  async setTypeCertificatsInput(typeCertificats: string): Promise<void> {
    await this.typeCertificatsInput.sendKeys(typeCertificats);
  }

  async getTypeCertificatsInput(): Promise<string> {
    return await this.typeCertificatsInput.getAttribute('value');
  }

  async setNomSignataireInput(nomSignataire: string): Promise<void> {
    await this.nomSignataireInput.sendKeys(nomSignataire);
  }

  async getNomSignataireInput(): Promise<string> {
    return await this.nomSignataireInput.getAttribute('value');
  }

  async setPrenomSignataireInput(prenomSignataire: string): Promise<void> {
    await this.prenomSignataireInput.sendKeys(prenomSignataire);
  }

  async getPrenomSignataireInput(): Promise<string> {
    return await this.prenomSignataireInput.getAttribute('value');
  }

  async setEmailSignataireInput(emailSignataire: string): Promise<void> {
    await this.emailSignataireInput.sendKeys(emailSignataire);
  }

  async getEmailSignataireInput(): Promise<string> {
    return await this.emailSignataireInput.getAttribute('value');
  }

  async importateurSelectLastOption(): Promise<void> {
    await this.importateurSelect.all(by.tagName('option')).last().click();
  }

  async importateurSelectOption(option: string): Promise<void> {
    await this.importateurSelect.sendKeys(option);
  }

  getImportateurSelect(): ElementFinder {
    return this.importateurSelect;
  }

  async getImportateurSelectedOption(): Promise<string> {
    return await this.importateurSelect.element(by.css('option:checked')).getText();
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

export class CertificatOrigineDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-certificatOrigine-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-certificatOrigine'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
