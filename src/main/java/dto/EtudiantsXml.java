package dto;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Wrapper XML pour une liste d'étudiants.
 * Produit : <etudiants><etudiant>...</etudiant>...</etudiants>
 */
@XmlRootElement(name = "etudiants")
@XmlAccessorType(XmlAccessType.FIELD)
public class EtudiantsXml {

    @XmlElement(name = "etudiant")
    private List<EtudiantXml> etudiants;

    public EtudiantsXml() {}

    public EtudiantsXml(List<EtudiantXml> etudiants) {
        this.etudiants = etudiants;
    }

    public List<EtudiantXml> getEtudiants() { return etudiants; }
    public void setEtudiants(List<EtudiantXml> etudiants) { this.etudiants = etudiants; }
}
