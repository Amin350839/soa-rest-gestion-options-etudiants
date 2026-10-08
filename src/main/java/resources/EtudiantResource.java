package resources;

import dto.EtudiantXml;
import dto.EtudiantsXml;
import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Ressource REST pour la gestion des Etudiants.
 * Base URL : /rest/etudiants
 */
@Path("etudiants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EtudiantResource {

    // Instances uniques partagées (données en mémoire statiques)
    private static final EtudiantBusiness etudiantBusiness = new EtudiantBusiness();
    private static final OptionBusiness optionBusiness = new OptionBusiness();

    /**
     * POST /etudiants
     * Ajouter un étudiant. Le body JSON doit contenir l'option avec son codeOption.
     * @param etudiant l'étudiant à ajouter
     * @return 200 OK si ajouté, 404 Not Found si l'option n'existe pas
     */
    @POST
    public Response addEtudiant(Etudiant etudiant) {
        // Vérifier que l'option existe
        if (etudiant.getOption() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean added = etudiantBusiness.addEtudiant(etudiant);
        if (added) {
            return Response.ok(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * GET /etudiants
     * Retourne la liste de tous les étudiants (JSON).
     * @return 200 OK + liste JSON
     */
    @GET
    public Response getAllEtudiants() {
        List<Etudiant> etudiants = etudiantBusiness.getAllEtudiants();
        return Response.ok(etudiants).build();
    }

    /**
     * GET /etudiants/option?codeOption=1
     * Retourne les étudiants d'une option donnée au format XML (sans l'objet option).
     * IMPORTANT : ce path littéral est déclaré AVANT le path template {identifiant}
     * pour éviter le shadowing.
     * @param codeOption le code de l'option
     * @return 200 OK + XML, ou 404 si l'option n'existe pas
     */
    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {
        // Vérifier que l'option existe
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // Récupérer les étudiants de cette option
        List<Etudiant> etudiants = etudiantBusiness.getEtudiantsByOption(option);
        // Convertir en DTOs XML (sans l'option)
        List<EtudiantXml> dtos = new ArrayList<>();
        for (Etudiant e : etudiants) {
            dtos.add(new EtudiantXml(
                e.getIdentifiant(),
                e.getNom(),
                e.getPrenom(),
                e.getAnneeEtude(),
                e.getEmail()
            ));
        }
        EtudiantsXml wrapper = new EtudiantsXml(dtos);
        return Response.ok(wrapper).build();
    }

    /**
     * GET /etudiants/{identifiant}
     * Retourne un étudiant par son identifiant (JSON).
     * @param identifiant l'identifiant de l'étudiant
     * @return 200 OK + JSON, ou 404 Not Found
     */
    @GET
    @Path("{identifiant}")
    public Response getEtudiantByIdentifiant(@PathParam("identifiant") String identifiant) {
        Etudiant etudiant = etudiantBusiness.getEtudiantByIdentifiant(identifiant);
        if (etudiant != null) {
            return Response.ok(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * DELETE /etudiants/{identifiant}
     * Supprime un étudiant par son identifiant.
     * @param identifiant l'identifiant de l'étudiant à supprimer
     * @return 204 No Content si supprimé, 404 Not Found sinon
     */
    @DELETE
    @Path("{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant) {
        boolean deleted = etudiantBusiness.deleteEtudiant(identifiant);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * PUT /etudiants/{identifiant}
     * Met à jour un étudiant existant.
     * @param identifiant l'identifiant de l'étudiant à mettre à jour
     * @param etudiant les nouvelles données (JSON dans le body)
     * @return 200 OK si mis à jour, 404 Not Found sinon
     */
    @PUT
    @Path("{identifiant}")
    public Response updateEtudiant(@PathParam("identifiant") String identifiant, Etudiant etudiant) {
        boolean updated = etudiantBusiness.updateEtudiant(identifiant, etudiant);
        if (updated) {
            return Response.ok(etudiant).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
