# TpFoyer

Description détaillée (français)

## Présentation
Ce projet Spring Boot (Java + Maven) est une application de gestion d'un foyer universitaire. Il gère des entités principales : blocs, chambres, étudiants, foyers, réservations et universités. L'application expose une API REST pour effectuer les opérations CRUD et contient des services métiers, des repositories JPA, ainsi qu'un scheduler qui publie des rapports périodiques (ex : pourcentage de chambres par type, places disponibles).

## Fonctionnalités principales
- Gestion des blocs (`Bloc`) et leurs chambres (`Chambre`).
- Gestion des étudiants (`Etudiant`) et des réservations (`Reservation`).
- Gestion des foyers (`Foyer`) et des universités (`Universite`).
- Calcul périodique (scheduler) : listes et statistiques sur les chambres.
- Mappage DTO / entités (ex : `BlocMapper`).

## Technologies
- Java 17+ (ou version configurée dans le `pom.xml`).
- Spring Boot (Web, Data JPA, Scheduling, Lombok).
- Maven (utilisation du wrapper `mvnw` / `mvnw.cmd`).
- Base de données configurée via `application.properties` (H2, PostgreSQL, MySQL, etc.).

## Pré-requis
- JDK 17+ installé et `JAVA_HOME` correctement configuré.
- Maven n'est pas strictement nécessaire si vous utilisez le wrapper fourni (`mvnw.cmd` sous Windows).
- (Optionnel) Docker si vous voulez lancer une base distante via conteneur.

## Installation & exécution (Windows PowerShell)
1. Ouvrir PowerShell dans le dossier du projet (`TpFoyer`).
2. Pour compiler et exécuter l'application avec le wrapper Maven :

```powershell
# Compiler
./mvnw.cmd clean package -DskipTests

# Lancer l'application
./mvnw.cmd spring-boot:run
```

3. Pour exécuter les tests :

```powershell
./mvnw.cmd test
```

Remarque : si vous préférez Maven installé globalement, remplacez `./mvnw.cmd` par `mvn`.

## Configuration
- Les propriétés de l'application sont dans `src/main/resources/application.properties`.
- Configurez la datasource (JDBC URL, username, password) selon la base utilisée.
- Si vous voulez une base en mémoire pour tests/démo, ajoutez H2 dans `application.properties`.

Exemple minimal pour H2 (à ajouter/modifier) :

```properties
spring.datasource.url=jdbc:h2:mem:tpfoyerdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Scheduler
Le service `ChambreSchedulerService` contient plusieurs tâches planifiées (`@Scheduled`) :
- Liste des chambres par bloc (exécution toutes les minutes dans la version fournie).
- Pourcentage des chambres par type (SIMPLE/DOUBLE/TRIPLE) toutes les 5 minutes.
- Nombre de places disponibles par chambre pour l'année en cours toutes les 5 minutes.

Assurez-vous d'activer le scheduling si ce n'est pas déjà fait dans `TpFoyerApplication` en ajoutant `@EnableScheduling` :

```java
@SpringBootApplication
@EnableScheduling
public class TpFoyerApplication { ... }
```

## Structure du projet (points saillants)
- `src/main/java/tn/esprit/tpfoyer/TpFoyerApplication.java` : point d'entrée Spring Boot.
- `controllers/` : classes REST (ex : `BlocController`, `ChambreController`, `EtudiantController`, `FoyerController`, `ReservationController`, `UniversiteController`).
- `entities/` : définitions JPA (`Bloc`, `Chambre`, `Etudiant`, `Foyer`, `Reservation`, `TypeChambre`, `Universite`).
- `repositories/` : interfaces Spring Data JPA (ex : `ChambreRepository`, `BlocRepository`, etc.).
- `services/` : implémentations métier et services (ex : `ChambreSchedulerService`, `ChambreServiceImpl`...).
- `mappers/` : mappers MapStruct (ex : `BlocMapper` et son implémentation générée).
- `dto/` : objets de transfert (ex : `BlocDTO`, `FoyerDTO`).

## API (exemples de routes REST attendues)
Les controllers suivent des conventions REST courantes ; adaptez selon implémentation concrète.
- `GET /api/blocs` — lister les blocs
- `GET /api/blocs/{id}` — obtenir un bloc
- `POST /api/blocs` — créer un bloc
- `PUT /api/blocs/{id}` — mettre à jour un bloc
- `DELETE /api/blocs/{id}` — supprimer un bloc

- `GET /api/chambres` — lister les chambres
- `GET /api/etudiants`, `POST /api/reservations`, `GET /api/foyers`, `GET /api/universites`, etc.

Exemple cURL (GET) :

```powershell
curl -Uri "http://localhost:8080/api/blocs" -UseBasicParsing
```

Exemple cURL (POST) en PowerShell :

```powershell
$body = @{ nomBloc = "Bloc A"; capaciteBloc = 100 } | ConvertTo-Json
curl -Uri "http://localhost:8080/api/blocs" -Method Post -Body $body -ContentType "application/json"
```

Note : adaptez les champs JSON au DTO attendu par vos controllers.

## Tests
- Tests unitaires d'exemple sont dans `src/test/java/...`.
- Lancez les tests avec `./mvnw.cmd test`.

## Débogage et recommendations
- Activez les logs Spring Boot pour diagnostiquer les problèmes de démarrage ou de JPA.
- Vérifiez la génération MapStruct : la classe `generated-sources/annotations/tn/esprit/tpfoyer/mappers/BlocMapperImpl.java` devrait être produite lors de la compilation.
- Si vous changez des entités, relancez `./mvnw.cmd clean package` pour régénérer.

## Contribution
- Forkez le dépôt, créez une branche feature, puis ouvrez une pull request.
- Respectez les conventions de code (format, nommage) et exécutez les tests avant de soumettre.

## Prochaines étapes suggérées
- Ajouter une documentation OpenAPI/Swagger pour découvrir automatiquement les endpoints.
- Ajouter des tests d'intégration avec une base de données en mémoire (H2) ou des containers Docker.
