import { IImportateur } from 'app/shared/model/importateur.model';
import { IProduit } from 'app/shared/model/produit.model';
import { IEntreprise } from 'app/shared/model/entreprise.model';

export interface ICertificatOrigine {
  id?: number;
  nomExportateur?: string;
  adresseExportateur?: string;
  nomProducteur?: string;
  adresseProducteur?: string;
  nomImportateur?: string;
  adresseImportateur?: string;
  payOrigine?: string;
  autreOrigine?: boolean;
  payAutreOrigine?: string;
  detailTransport?: string;
  observation?: string;
  etatCertificat?: string;
  typeCertificats?: string;
  nomSignataire?: string;
  prenomSignataire?: string;
  emailSignataire?: string;
  importateur?: IImportateur;
  produits?: IProduit[];
  entreprise?: IEntreprise;
}

export class CertificatOrigine implements ICertificatOrigine {
  constructor(
    public id?: number,
    public nomExportateur?: string,
    public adresseExportateur?: string,
    public nomProducteur?: string,
    public adresseProducteur?: string,
    public nomImportateur?: string,
    public adresseImportateur?: string,
    public payOrigine?: string,
    public autreOrigine?: boolean,
    public payAutreOrigine?: string,
    public detailTransport?: string,
    public observation?: string,
    public etatCertificat?: string,
    public typeCertificats?: string,
    public nomSignataire?: string,
    public prenomSignataire?: string,
    public emailSignataire?: string,
    public importateur?: IImportateur,
    public produits?: IProduit[],
    public entreprise?: IEntreprise
  ) {
    this.autreOrigine = this.autreOrigine || false;
  }
}
