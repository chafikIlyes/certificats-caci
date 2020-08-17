import { Moment } from 'moment';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { ICertificatOrigine } from 'app/shared/model/certificat-origine.model';

export interface IProduit {
  id?: number;
  nomProduit?: string;
  description?: string;
  marque?: string;
  hsCode?: string;
  qte?: string;
  uniteMesure?: string;
  nbrColi?: string;
  poidNet?: string;
  poidReel?: string;
  dateFacture?: Moment;
  numeroFacture?: string;
  entreprise?: IEntreprise;
  certificatOrigine?: ICertificatOrigine;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public nomProduit?: string,
    public description?: string,
    public marque?: string,
    public hsCode?: string,
    public qte?: string,
    public uniteMesure?: string,
    public nbrColi?: string,
    public poidNet?: string,
    public poidReel?: string,
    public dateFacture?: Moment,
    public numeroFacture?: string,
    public entreprise?: IEntreprise,
    public certificatOrigine?: ICertificatOrigine
  ) {}
}
