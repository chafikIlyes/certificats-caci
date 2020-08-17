import { IGerant } from 'app/shared/model/gerant.model';
import { IChargeExport } from 'app/shared/model/charge-export.model';
import { IUser } from 'app/core/user/user.model';
import { IImportateur } from 'app/shared/model/importateur.model';
import { IProduit } from 'app/shared/model/produit.model';
import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';

export interface IEntreprise {
  id?: number;
  raisonSocial?: string;
  formeJuridique?: string;
  secterActivite?: string;
  rcContentType?: string;
  rc?: any;
  nifContentType?: string;
  nif?: any;
  nisContentType?: string;
  nis?: any;
  codeActivite?: string;
  codeActiviteExport?: string;
  mobile?: string;
  telFix?: string;
  email?: string;
  siteWeb?: string;
  adresse?: string;
  typeExportateur?: string;
  fax?: string;
  soldeCertif?: string;
  gerant?: IGerant;
  chargeExport?: IChargeExport;
  user?: IUser;
  importateurs?: IImportateur[];
  produits?: IProduit[];
  certificatOrigines?: ICertificatOrigine[];
}

export class Entreprise implements IEntreprise {
  constructor(
    public id?: number,
    public raisonSocial?: string,
    public formeJuridique?: string,
    public secterActivite?: string,
    public rcContentType?: string,
    public rc?: any,
    public nifContentType?: string,
    public nif?: any,
    public nisContentType?: string,
    public nis?: any,
    public codeActivite?: string,
    public codeActiviteExport?: string,
    public mobile?: string,
    public telFix?: string,
    public email?: string,
    public siteWeb?: string,
    public adresse?: string,
    public typeExportateur?: string,
    public fax?: string,
    public soldeCertif?: string,
    public gerant?: IGerant,
    public chargeExport?: IChargeExport,
    public user?: IUser,
    public importateurs?: IImportateur[],
    public produits?: IProduit[],
    public certificatOrigines?: ICertificatOrigine[]
  ) {}
}
