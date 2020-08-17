package caci.certificats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_produit", nullable = false)
    private String nomProduit;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "marque")
    private String marque;

    @Column(name = "hs_code")
    private String hsCode;

    @NotNull
    @Column(name = "qte", nullable = false)
    private String qte;

    @NotNull
    @Column(name = "unite_mesure", nullable = false)
    private String uniteMesure;

    @NotNull
    @Column(name = "nbr_coli", nullable = false)
    private String nbrColi;

    @NotNull
    @Column(name = "poid_net", nullable = false)
    private String poidNet;

    @NotNull
    @Column(name = "poid_reel", nullable = false)
    private String poidReel;

    @NotNull
    @Column(name = "date_facture", nullable = false)
    private LocalDate dateFacture;

    @Column(name = "numero_facture")
    private String numeroFacture;

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Entreprise entreprise;

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private CertificatOrigine certificatOrigine;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public Produit nomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
        return this;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public Produit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public Produit marque(String marque) {
        this.marque = marque;
        return this;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getHsCode() {
        return hsCode;
    }

    public Produit hsCode(String hsCode) {
        this.hsCode = hsCode;
        return this;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getQte() {
        return qte;
    }

    public Produit qte(String qte) {
        this.qte = qte;
        return this;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public Produit uniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
        return this;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public String getNbrColi() {
        return nbrColi;
    }

    public Produit nbrColi(String nbrColi) {
        this.nbrColi = nbrColi;
        return this;
    }

    public void setNbrColi(String nbrColi) {
        this.nbrColi = nbrColi;
    }

    public String getPoidNet() {
        return poidNet;
    }

    public Produit poidNet(String poidNet) {
        this.poidNet = poidNet;
        return this;
    }

    public void setPoidNet(String poidNet) {
        this.poidNet = poidNet;
    }

    public String getPoidReel() {
        return poidReel;
    }

    public Produit poidReel(String poidReel) {
        this.poidReel = poidReel;
        return this;
    }

    public void setPoidReel(String poidReel) {
        this.poidReel = poidReel;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public Produit dateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
        return this;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public Produit numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Produit entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public CertificatOrigine getCertificatOrigine() {
        return certificatOrigine;
    }

    public Produit certificatOrigine(CertificatOrigine certificatOrigine) {
        this.certificatOrigine = certificatOrigine;
        return this;
    }

    public void setCertificatOrigine(CertificatOrigine certificatOrigine) {
        this.certificatOrigine = certificatOrigine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", nomProduit='" + getNomProduit() + "'" +
            ", description='" + getDescription() + "'" +
            ", marque='" + getMarque() + "'" +
            ", hsCode='" + getHsCode() + "'" +
            ", qte='" + getQte() + "'" +
            ", uniteMesure='" + getUniteMesure() + "'" +
            ", nbrColi='" + getNbrColi() + "'" +
            ", poidNet='" + getPoidNet() + "'" +
            ", poidReel='" + getPoidReel() + "'" +
            ", dateFacture='" + getDateFacture() + "'" +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            "}";
    }
}
