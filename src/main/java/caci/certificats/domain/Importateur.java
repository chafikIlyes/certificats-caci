package caci.certificats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Importateur.
 */
@Entity
@Table(name = "importateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Importateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "raison_social", nullable = false)
    private String raisonSocial;

    @NotNull
    @Column(name = "forme_juridique", nullable = false)
    private String formeJuridique;

    @NotNull
    @Column(name = "secter_activite", nullable = false)
    private String secterActivite;

    @NotNull
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @NotNull
    @Column(name = "tel_fix", nullable = false)
    private String telFix;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "site_web")
    private String siteWeb;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "type_importateur")
    private String typeImportateur;

    @Column(name = "fax")
    private String fax;

    @ManyToOne
    @JsonIgnoreProperties(value = "importateurs", allowSetters = true)
    private Entreprise entreprise;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public Importateur raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public Importateur formeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
        return this;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getSecterActivite() {
        return secterActivite;
    }

    public Importateur secterActivite(String secterActivite) {
        this.secterActivite = secterActivite;
        return this;
    }

    public void setSecterActivite(String secterActivite) {
        this.secterActivite = secterActivite;
    }

    public String getMobile() {
        return mobile;
    }

    public Importateur mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelFix() {
        return telFix;
    }

    public Importateur telFix(String telFix) {
        this.telFix = telFix;
        return this;
    }

    public void setTelFix(String telFix) {
        this.telFix = telFix;
    }

    public String getEmail() {
        return email;
    }

    public Importateur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public Importateur siteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
        return this;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getAdresse() {
        return adresse;
    }

    public Importateur adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeImportateur() {
        return typeImportateur;
    }

    public Importateur typeImportateur(String typeImportateur) {
        this.typeImportateur = typeImportateur;
        return this;
    }

    public void setTypeImportateur(String typeImportateur) {
        this.typeImportateur = typeImportateur;
    }

    public String getFax() {
        return fax;
    }

    public Importateur fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Importateur entreprise(Entreprise entreprise) {
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
        if (!(o instanceof Importateur)) {
            return false;
        }
        return id != null && id.equals(((Importateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Importateur{" +
            "id=" + getId() +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", formeJuridique='" + getFormeJuridique() + "'" +
            ", secterActivite='" + getSecterActivite() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", telFix='" + getTelFix() + "'" +
            ", email='" + getEmail() + "'" +
            ", siteWeb='" + getSiteWeb() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", typeImportateur='" + getTypeImportateur() + "'" +
            ", fax='" + getFax() + "'" +
            "}";
    }
}
