package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * DTO XML pour Etudiant (sans l'option).
 * Utilisé uniquement pour le endpoint GET /etudiants/option qui retourne du XML.
 * L'ordre des champs correspond à l'ordre attendu dans le XML.
 */
@XmlRootElement(name = "etudiant")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"anneeEtude", "email", "identifiant", "nom", "prenom"})
public class EtudiantXml {
    private int anneeEtude;
    private String email;
    private String identifiant;
    private String nom;
    private String prenom;

    public EtudiantXml() {}

    public EtudiantXml(String identifiant, String nom, String prenom, int anneeEtude, String email) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.anneeEtude = anneeEtude;
        this.email = email;
    }

    // Getters et Setters
    public int getAnneeEtude() { return anneeEtude; }
    public void setAnneeEtude(int anneeEtude) { this.anneeEtude = anneeEtude; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
