# Gestion Options Étudiants - API REST (JAX-RS)

> Projet universitaire ESPRIT — Atelier REST n°1 (SOA)

## Description

Application REST pour la gestion des **options** et des **étudiants** d'une université.  
Développée avec **JAX-RS (Jersey 2.27)** et déployée sur **Apache Tomcat 9.0.75**.

## Technologies

- Java 8
- JAX-RS 2.1 (Jersey 2.27, namespace `javax.*`)
- Jackson (JSON) + JAXB (XML)
- Maven (packaging WAR)
- Apache Tomcat 9.0.75

## Structure du projet

```
src/main/java/
├── entities/          # Entités métier (Option, Etudiant)
├── metiers/           # Classes métier (OptionBusiness, EtudiantBusiness)
├── resources/         # Ressources REST (OptionResource, EtudiantResource)
├── dto/               # DTOs XML (EtudiantXml, EtudiantsXml)
└── utilities/         # Configuration JAX-RS (RestActivator)
```

## Comment exécuter

### Prérequis
- JDK 8+
- Maven
- Apache Tomcat 9.0.75

### Build
```bash
mvn clean package
```

### Déploiement sur Tomcat

#### Méthode 1 : Copie du WAR
1. Copier `target/Gestion_Options_Etudiants.war` dans le dossier `webapps/` de Tomcat
2. Démarrer Tomcat : `bin/startup.bat` (Windows) ou `bin/startup.sh` (Linux/Mac)
3. L'application est accessible à : `http://localhost:8080/Gestion_Options_Etudiants/rest`

#### Méthode 2 : IntelliJ IDEA
1. **Run → Edit Configurations → + → Tomcat Server → Local**
2. Onglet **Server** : sélectionner le répertoire de Tomcat 9.0.75
3. Onglet **Deployment** : cliquer **+** → **Artifact** → choisir `Gestion_Options_Etudiants:war exploded`
4. **Application context** : `/Gestion_Options_Etudiants`
5. Cliquer **Run** (ou **Debug**)

### URL de base
```
http://localhost:8080/Gestion_Options_Etudiants/rest
```

## Endpoints REST

### Options (`/rest/options`)

| Méthode | URL | Description | Content-Type | Code Réponse |
|---------|-----|-------------|--------------|--------------|
| `POST` | `/rest/options` | Ajouter une option | JSON → JSON | 200 / 404 |
| `GET` | `/rest/options` | Lister toutes les options | → JSON | 200 |
| `GET` | `/rest/options?domaine=X` | Filtrer par domaine | → JSON | 200 |
| `GET` | `/rest/options/{code}` | Obtenir une option | → JSON | 200 / 404 |
| `PUT` | `/rest/options/{code}` | Modifier une option | JSON → JSON | 200 / 404 |
| `DELETE` | `/rest/options/{code}` | Supprimer une option | — | 204 / 404 |

### Étudiants (`/rest/etudiants`)

| Méthode | URL | Description | Content-Type | Code Réponse |
|---------|-----|-------------|--------------|--------------|
| `POST` | `/rest/etudiants` | Ajouter un étudiant | JSON → JSON | 200 / 404 |
| `GET` | `/rest/etudiants` | Lister tous les étudiants | → JSON | 200 |
| `GET` | `/rest/etudiants/{id}` | Obtenir un étudiant | → JSON | 200 / 404 |
| `PUT` | `/rest/etudiants/{id}` | Modifier un étudiant | JSON → JSON | 200 / 404 |
| `DELETE` | `/rest/etudiants/{id}` | Supprimer un étudiant | — | 204 / 404 |
| `GET` | `/rest/etudiants/option?codeOption=X` | Étudiants par option | → **XML** | 200 / 404 |

## Données initiales (en mémoire)

### Options
| Code | Libellé | Domaine | Responsable | Crédits | Semestre | Capacité |
|------|---------|---------|-------------|---------|----------|----------|
| 1 | Informatique | Informatique | M. Responsable Informatique | 30 | 1 | 30 |
| 2 | Mathématiques | Mathématiques | Mme Responsable Mathématiques | 25 | 1 | 25 |
| 3 | Physique | Physique | M. Responsable Physique | 20 | 2 | 20 |
| 4 | Infographie | Infographie | Mme Responsable Infographie | 15 | 1 | 15 |
| 5 | Chimie | Chimie | M. Responsable Chimie | 20 | 2 | 20 |

### Étudiants
| ID | Nom | Prénom | Option | Année | Email |
|----|-----|--------|--------|-------|-------|
| I001 | Doe | Jean | Informatique (1) | 2023 | jean.doe@example.com |
| I002 | Smith | Alice | Informatique (1) | 2022 | alice.smith@example.com |
| I003 | Durand | Pierre | Mathématiques (2) | 2023 | pierre.durand@example.com |

## Tests

Les requêtes de test se trouvent dans [`tests/REST_tests.http`](tests/REST_tests.http).  
Vous pouvez les exécuter directement dans IntelliJ (HTTP Client) ou Postman.

## Screenshots

Les captures d'écran des 12 endpoints testés se trouvent dans le dossier `screenshots/` :

| # | Endpoint | Fichier |
|---|----------|---------|
| 1 | POST /options | `screenshots/option_post.png` |
| 2 | GET /options | `screenshots/option_get_all.png` |
| 3 | GET /options?domaine= | `screenshots/option_get_by_domaine.png` |
| 4 | GET /options/{code} | `screenshots/option_get_by_code.png` |
| 5 | PUT /options/{code} | `screenshots/option_put.png` |
| 6 | DELETE /options/{code} | `screenshots/option_delete.png` |
| 7 | POST /etudiants | `screenshots/etudiant_post.png` |
| 8 | GET /etudiants | `screenshots/etudiant_get_all.png` |
| 9 | GET /etudiants/{id} | `screenshots/etudiant_get_by_id.png` |
| 10 | PUT /etudiants/{id} | `screenshots/etudiant_put.png` |
| 11 | DELETE /etudiants/{id} | `screenshots/etudiant_delete.png` |
| 12 | GET /etudiants/option?codeOption= | `screenshots/etudiant_get_by_option_xml.png` |

## Auteur

Projet réalisé dans le cadre du cours **SOA** — ESPRIT
