package utilities;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Classe d'activation JAX-RS.
 * Configure le chemin de base pour tous les services REST : /rest
 * URL de base : http://localhost:8080/Gestion_Options_Etudiants/rest
 */
@ApplicationPath("rest")
public class RestActivator extends Application {
    // Jersey scanne automatiquement les classes annotées @Path
}
