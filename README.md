# api-artist

API REST Java (Spring Boot) déployée avec une base de données **MariaDB**, le tout conteneurisé via **Docker Compose**.

## Stack technique

| Composant | Version |
|---|---|
| Java | 17 |
| Framework | Spring Boot 3.4.2 |
| Base de données | MariaDB 10.x |
| Build | Gradle 8.x |
| Documentation | OpenAPI (Swagger) 2.3.0 |

## Structure du projet

```
api-artist/
├── 📁 src
│   └── 📁 main
│       ├── 📁 java
│       │   └── 📁 com
│       │       ├── 📁 controllers
│       │       │   └── ☕ ArtistController.java
│       │       ├── 📁 dtos
│       │       │   └── ☕ ArtistDto.java
│       │       ├── 📁 entities
│       │       │   └── ☕ Artist.java
│       │       ├── 📁 mappers
│       │       │   └── ☕ ArtistMapper.java
│       │       ├── 📁 repositories
│       │       │   └── ☕ ArtistRepository.java
│       │       ├── 📁 services
│       │       │   ├── 📁 impl
│       │       │   │   └── ☕ ArtistServiceImpl.java
│       │       │   └── ☕ ArtistService.java
│       │       └── ☕ App.java
│       └── 📁 resources
│           ├── 📄 application.properties
│           └── ⚙️ openapi.yaml
├── ⚙️ .gitignore
├── 📝 README.md
├── 🐳 Dockerfile
├── ⚙️ docker-compose.yaml
├── 🐘 gradlew
└── 🐘 build.gradle
```

## Lancer l'application

### Prérequis

- Docker Desktop installé et démarré

### Démarrage

1. **Lancer la base de données** :

```bash
docker compose up -d
```

Cette commande démarre le conteneur **MariaDB** (`artiste-api`) sur le port 3306.

2. **Lancer l'application Spring Boot** :

Vous devez exécuter la classe principale Java :
`src/main/java/com/App.java`

Ou via Gradle :

```bash
./gradlew bootRun
```

L'API sera alors accessible sur : **http://localhost:8080**  
La documentation Swagger UI est accessible sur : **http://localhost:8080/swagger-ui.html**

### Arrêt

```bash
docker compose down
```

Pour supprimer aussi le volume de données MariaDB (pour repartir d'une base vide) :

```bash
docker compose down -v
```

## Endpoints disponibles

| Méthode | Route | Description |
|---|---|---|
| GET | `/api/artistes` | Liste tous les artistes |
| GET | `/api/artistes/{id}` | Récupère un artiste par son id |
| POST | `/api/artistes` | Crée un artiste |
| PUT | `/api/artistes/{id}` | Modifie le nom, le prenom ou l'age d'un artiste |
| DELETE | `/api/artistes/{id}` | Supprime un atiste |

### Exemples curl

```bash
# Lister les artistes
curl http://localhost:8080/api/artistes

# Récupérer un artiste
curl http://localhost:8080/api/artistes/1

# Ajouter un artiste (ex: Keanu Reeves)
curl -X POST http://localhost:8080/api/artistes \
     -H "Content-Type: application/json" \
     -d '{"nom": "Reeves", "prenom": "Keanu", "age": 62
}'

# Modifier un artiste
curl -X PUT http://localhost:8080/api/artistes/1 \
     -H "Content-Type: application/json" \
     -d '{"age": 63}'

# Supprimer un artiste
curl -X DELETE http://localhost:8080/api/artistes/1
```

### Exemples de requêtes (Powershell)

```ps
# Lister les artiste
Invoke-WebRequest -Uri "http://localhost:8080/api/artistes" -Method GET -UseBasicParsing

# Récupérer un artiste
Invoke-WebRequest -Uri "http://localhost:8080/api/artistes/1" -Method GET -UseBasicParsing

# Ajouter un artiste (ex: Keanu Reeves)
Invoke-WebRequest -Uri "http://localhost:8080/api/artistes" -Method POST -ContentType "application/json" -Body '{"nom": "Reeves", "prenom": "Keanu", "age": 62}' -UseBasicParsing

# Modifier un artiste
Invoke-WebRequest -Uri "http://localhost:8080/api/artistes/1" -Method PUT -ContentType "application/json" -Body '{"nom": "Reeves", "prenom": "Keanu", "age": 63}' -UseBasicParsing

# Supprimer un artiste
Invoke-WebRequest -Uri "http://localhost:8080/api/artistes/1" -Method DELETE -UseBasicParsing
```

## Configuration

### Base de données

Les paramètres de connexion sont définis dans `application.properties` et synchronisés avec `docker-compose.yaml` :

| Propriété | Variable Env Docker (Service DB) | Valeur par défaut |
|---|---|---|
| `spring.datasource.url` | - | `jdbc:mariadb://localhost:3306/test` |
| `spring.datasource.password` | `MARIADB_ROOT_PASSWORD` | `monMotDePasseSuperSecret` |
| - | `MARIADB_DATABASE` | `test` |

## Développement sans Docker

Il est possible de tester localement en lançant une instance MariaDB sur votre machine (port 3306), puis en exécutant l'application via Gradle ou votre IDE. Assurez-vous que les identifiants dans `application.properties` correspondent à votre base locale.
