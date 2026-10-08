package resources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST pour la gestion des Options.
 * Base URL : /rest/options
 */
@Path("options")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OptionResource {

    // Instance unique partagée (données en mémoire statiques)
    private static final OptionBusiness optionBusiness = new OptionBusiness();

    /**
     * POST /options
     * Ajouter une nouvelle option.
     * @param option l'option à ajouter (JSON dans le body)
     * @return 200 OK si ajoutée, 404 Not Found sinon
     */
    @POST
    public Response addOption(Option option) {
        boolean added = optionBusiness.addOption(option);
        if (added) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * GET /options  ou  GET /options?domaine=Mathématiques
     * Retourne toutes les options, ou filtrées par domaine si le paramètre est présent.
     * @param domaine paramètre optionnel de filtrage par domaine
     * @return 200 OK + liste JSON
     */
    @GET
    public Response getOptions(@QueryParam("domaine") String domaine) {
        List<Option> result;
        if (domaine != null && !domaine.isEmpty()) {
            // Filtrage par domaine
            result = optionBusiness.getOptionsByDomaine(domaine);
        } else {
            // Toutes les options
            result = optionBusiness.getListeOptions();
        }
        return Response.ok(result).build();
    }

    /**
     * GET /options/{code}
     * Retourne une option par son code.
     * @param code le code de l'option
     * @return 200 OK + JSON option, ou 404 Not Found
     */
    @GET
    @Path("{code}")
    public Response getOptionByCode(@PathParam("code") int code) {
        Option option = optionBusiness.getOptionByCode(code);
        if (option != null) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * DELETE /options/{code}
     * Supprime une option par son code.
     * @param code le code de l'option à supprimer
     * @return 204 No Content si supprimée, 404 Not Found sinon
     */
    @DELETE
    @Path("{code}")
    public Response deleteOption(@PathParam("code") int code) {
        boolean deleted = optionBusiness.deleteOption(code);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * PUT /options/{code}
     * Met à jour une option existante.
     * @param code le code de l'option à mettre à jour
     * @param option les nouvelles données (JSON dans le body)
     * @return 200 OK si mise à jour, 404 Not Found sinon
     */
    @PUT
    @Path("{code}")
    public Response updateOption(@PathParam("code") int code, Option option) {
        boolean updated = optionBusiness.updateOption(code, option);
        if (updated) {
            return Response.ok(option).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
