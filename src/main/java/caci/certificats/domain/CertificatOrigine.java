package caci.certificats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CertificatOrigine.
 */
@Entity
@Table(name = "certificat_origine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CertificatOrigine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_exportateur", nullable = false)
    private String nomExportateur;

    @NotNull
    @Column(name = "adresse_exportateur", nullable = false)
    private String adresseExportateur;

    @NotNull
    @Column(name = "nom_producteur", nullable = false)
    private String nomProducteur;

    @NotNull
    @Column(name = "adresse_producteur", nullable = false)
    private String adresseProducteur;

    @NotNull
    @Column(name = "nom_importateur", nullable = false)
    private String nomImportateur;

    @NotNull
    @Column(name = "adresse_importateur", nullable = false)
    private String adresseImportateur;

    @NotNull
    @Column(name = "pay_origine", nullable = false)
    private String payOrigine;

    @Column(name = "autre_origine")
    private Boolean autreOrigine;

    @Column(name = "pay_autre_origine")
    private String payAutreOrigine;

    @NotNull
    @Column(name = "detail_transport", nullable = false)
    private String detailTransport;

    @Column(name = "observation")
    private String observation;

    @Column(name = "etat_certificat")
    private String etatCertificat;

    @Column(name = "type_certificats")
    private String typeCertificats;

    @Column(name = "nom_signataire")
    private String nomSignataire;

    @Column(name = "prenom_signataire")
    private String prenomSignataire;

    @Column(name = "email_signataire")
    private String emailSignataire;

    @OneToOne
    @JoinColumn(unique = true)
    private Importateur importateur;

    @OneToMany(mappedBy = "certificatOrigine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "certificatOrigines", allowSetters = true)
    private Entreprise entreprise;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomExportateur() {
        return nomExportateur;
    }

    public CertificatOrigine nomExportateur(String nomExportateur) {
        this.nomExportateur = nomExportateur;
        return this;
    }

    public void setNomExportateur(String nomExportateur) {
        this.nomExportateur = nomExportateur;
    }

    public String getAdresseExportateur() {
        return adresseExportateur;
    }

    public CertificatOrigine adresseExportateur(String adresseExportateur) {
        this.adresseExportateur = adresseExportateur;
        return this;
    }

    public void setAdresseExportateur(String adresseExportateur) {
        this.adresseExportateur = adresseExportateur;
    }

    public String getNomProducteur() {
        return nomProducteur;
    }

    public CertificatOrigine nomProducteur(String nomProducteur) {
        this.nomProducteur = nomProducteur;
        return this;
    }

    public void setNomProducteur(String nomProducteur) {
        this.nomProducteur = nomProducteur;
    }

    public String getAdresseProducteur() {
        return adresseProducteur;
    }

    public CertificatOrigine adresseProducteur(String adresseProducteur) {
        this.adresseProducteur = adresseProducteur;
        return this;
    }

    public void setAdresseProducteur(String adresseProducteur) {
        this.adresseProducteur = adresseProducteur;
    }

    public String getNomImportateur() {
        return nomImportateur;
    }

    public CertificatOrigine nomImportateur(String nomImportateur) {
        this.nomImportateur = nomImportateur;
        return this;
    }

    public void setNomImportateur(String nomImportateur) {
        this.nomImportateur = nomImportateur;
    }

    public String getAdresseImportateur() {
        return adresseImportateur;
    }

    public CertificatOrigine adresseImportateur(String adresseImportateur) {
        this.adresseImportateur = adresseImportateur;
        return this;
    }

    public void setAdresseImportateur(String adresseImportateur) {
        this.adresseImportateur = adresseImportateur;
    }

    public String getPayOrigine() {
        return payOrigine;
    }

    public CertificatOrigine payOrigine(String payOrigine) {
        this.payOrigine = payOrigine;
        return this;
    }

    public void setPayOrigine(String payOrigine) {
        this.payOrigine = payOrigine;
    }

    public Boolean isAutreOrigine() {
        return autreOrigine;
    }

    public CertificatOrigine autreOrigine(Boolean autreOrigine) {
        this.autreOrigine = autreOrigine;
        return this;
    }

    public void setAutreOrigine(Boolean autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getPayAutreOrigine() {
        return payAutreOrigine;
    }

    public CertificatOrigine payAutreOrigine(String payAutreOrigine) {
        this.payAutreOrigine = payAutreOrigine;
        return this;
    }

    public void setPayAutreOrigine(String payAutreOrigine) {
        this.payAutreOrigine = payAutreOrigine;
    }

    public String getDetailTransport() {
        return detailTransport;
    }

    public CertificatOrigine detailTransport(String detailTransport) {
        this.detailTransport = detailTransport;
        return this;
    }

    public void setDetailTransport(String detailTransport) {
        this.detailTransport = detailTransport;
    }

    public String getObservation() {
        return observation;
    }

    public CertificatOrigine observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getEtatCertificat() {
        return etatCertificat;
    }

    public CertificatOrigine etatCertificat(String etatCertificat) {
        this.etatCertificat = etatCertificat;
        return this;
    }

    public void setEtatCertificat(String etatCertificat) {
        this.etatCertificat = etatCertificat;
    }

    public String getTypeCertificats() {
        return typeCertificats;
    }

    public CertificatOrigine typeCertificats(String typeCertificats) {
        this.typeCertificats = typeCertificats;
        return this;
    }

    public void setTypeCertificats(String typeCertificats) {
        this.typeCertificats = typeCertificats;
    }

    public String getNomSignataire() {
        return nomSignataire;
    }

    public CertificatOrigine nomSignataire(String nomSignataire) {
        this.nomSignataire = nomSignataire;
        return this;
    }

    public void setNomSignataire(String nomSignataire) {
        this.nomSignataire = nomSignataire;
    }

    public String getPrenomSignataire() {
        return prenomSignataire;
    }

    public CertificatOrigine prenomSignataire(String prenomSignataire) {
        this.prenomSignataire = prenomSignataire;
        return this;
    }

    public void setPrenomSignataire(String prenomSignataire) {
        this.prenomSignataire = prenomSignataire;
    }

    public String getEmailSignataire() {
        return emailSignataire;
    }

    public CertificatOrigine emailSignataire(String emailSignataire) {
        this.emailSignataire = emailSignataire;
        return this;
    }

    public void setEmailSignataire(String emailSignataire) {
        this.emailSignataire = emailSignataire;
    }

    public Importateur getImportateur() {
        return importateur;
    }

    public CertificatOrigine importateur(Importateur importateur) {
        this.importateur = importateur;
        return this;
    }

    public void setImportateur(Importateur importateur) {
        this.importateur = importateur;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public CertificatOrigine produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public CertificatOrigine addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setCertificatOrigine(this);
        return this;
    }

    public CertificatOrigine removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setCertificatOrigine(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public CertificatOrigine entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CertificatOrigine)) {
            return false;
        }
        return id != null && id.equals(((CertificatOrigine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CertificatOrigine{" +
            "id=" + getId() +
            ", nomExportateur='" + getNomExportateur() + "'" +
            ", adresseExportateur='" + getAdresseExportateur() + "'" +
            ", nomProducteur='" + getNomProducteur() + "'" +
            ", adresseProducteur='" + getAdresseProducteur() + "'" +
            ", nomImportateur='" + getNomImportateur() + "'" +
            ", adresseImportateur='" + getAdresseImportateur() + "'" +
            ", payOrigine='" + getPayOrigine() + "'" +
            ", autreOrigine='" + isAutreOrigine() + "'" +
            ", payAutreOrigine='" + getPayAutreOrigine() + "'" +
            ", detailTransport='" + getDetailTransport() + "'" +
            ", observation='" + getObservation() + "'" +
            ", etatCertificat='" + getEtatCertificat() + "'" +
            ", typeCertificats='" + getTypeCertificats() + "'" +
            ", nomSignataire='" + getNomSignataire() + "'" +
            ", prenomSignataire='" + getPrenomSignataire() + "'" +
            ", emailSignataire='" + getEmailSignataire() + "'" +
            "}";
    }
}
