# api-artist

API REST Java (Spring Boot) de gestion des artistes, connectee a une base **MariaDB**.

Le module inclut :
- une API CRUD `/api/artistes`
- une documentation OpenAPI/Swagger
- une protection JWT (Bearer token) sur toutes les routes
- un `docker-compose.yaml` pour la base MariaDB

## Stack technique

| Composant | Version |
|---|---|
| Java | 17 |
| Framework | Spring Boot 3.4.2 |
| Base de données | MariaDB 10.x |
| Build | Gradle 8.x |
| Documentation | OpenAPI (Swagger) 2.3.0 |
| Securite | Spring Security + OAuth2 Resource Server (JWT HS256) |

## Structure du projet

```
api-artist/
├── 📁 src
│   └── 📁 main
│       ├── 📁 java
│       │   └── 📁 com
│       │       ├── ☕ App.java
│       │       ├── ☕ WebConfig.java
│       │       ├── 📁 config
│       │       │   └── ☕ SecurityConfig.java
│       │       ├── 📁 controllers
│       │       │   ├── ☕ ArtistController.java
│       │       │   └── ☕ GlobalExceptionHandler.java
│       │       ├── 📁 dtos
│       │       │   ├── ☕ ArtistDto.java
│       │       │   └── ☕ ErrorResponse.java
│       │       ├── 📁 entities
│       │       │   └── ☕ Artist.java
│       │       ├── 📁 mappers
│       │       │   └── ☕ ArtistMapper.java
│       │       ├── 📁 repositories
│       │       │   └── ☕ ArtistRepository.java
│       │       ├── 📁 services
│       │       │   ├── 📁 impl
│       │       │   │   └── ☕ ArtistServiceImpl.java
│       │       │   ├── ☕ ArtistService.java
│       │       │   └── ☕ DuplicateArtistException.java
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
- Java 17

### Démarrage

1. **Lancer la base de données** :

```bash
docker compose up -d
```

Cette commande demarre le conteneur **MariaDB** (`mariadb-artist`) avec le mapping de port `3307:3306`.

2. **Lancer l'application Spring Boot** :

Vous devez exécuter la classe principale Java :
`src/main/java/com/App.java`

Ou via Gradle :

```bash
./gradlew bootRun
```

Sous PowerShell (Windows) :

```powershell
.\gradlew.bat bootRun
```

L'API est accessible sur : **http://localhost:8083**

La documentation Swagger UI est accessible sur : **http://localhost:8083/swagger-ui.html**

Le JSON OpenAPI est accessible sur : **http://localhost:8083/api-docs**

### Authentification

Toutes les routes de l'API necessitent un token JWT Bearer (`Authorization: Bearer <token>`).

Exemple d'en-tete HTTP :

```http
Authorization: Bearer <votre_token_jwt>
```

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
| DELETE | `/api/artistes/{id}` | Supprime un artiste |

### Exemples curl

```bash
# Lister les artistes
curl http://localhost:8083/api/artistes \
     -H "Authorization: Bearer <TOKEN>"

# Récupérer un artiste
curl http://localhost:8083/api/artistes/1 \
     -H "Authorization: Bearer <TOKEN>"

# Ajouter un artiste (ex: Keanu Reeves)
curl -X POST http://localhost:8083/api/artistes \
     -H "Authorization: Bearer <TOKEN>" \
     -H "Content-Type: application/json" \
     -d '{"nom": "Reeves", "prenom": "Keanu", "age": 62}'

# Modifier un artiste
curl -X PUT http://localhost:8083/api/artistes/1 \
     -H "Authorization: Bearer <TOKEN>" \
     -H "Content-Type: application/json" \
     -d '{"age": 63}'

# Supprimer un artiste
curl -X DELETE http://localhost:8083/api/artistes/1 \
     -H "Authorization: Bearer <TOKEN>"
```

### Exemples de requêtes (Powershell)

```ps
# Lister les artistes
Invoke-WebRequest -Uri "http://localhost:8083/api/artistes" -Method GET -Headers @{ Authorization = "Bearer <TOKEN>" } -UseBasicParsing

# Récupérer un artiste
Invoke-WebRequest -Uri "http://localhost:8083/api/artistes/1" -Method GET -Headers @{ Authorization = "Bearer <TOKEN>" } -UseBasicParsing

# Ajouter un artiste (ex: Keanu Reeves)
Invoke-WebRequest -Uri "http://localhost:8083/api/artistes" -Method POST -Headers @{ Authorization = "Bearer <TOKEN>" } -ContentType "application/json" -Body '{"nom": "Reeves", "prenom": "Keanu", "age": 62}' -UseBasicParsing

# Modifier un artiste
Invoke-WebRequest -Uri "http://localhost:8083/api/artistes/1" -Method PUT -Headers @{ Authorization = "Bearer <TOKEN>" } -ContentType "application/json" -Body '{"nom": "Reeves", "prenom": "Keanu", "age": 63}' -UseBasicParsing

# Supprimer un artiste
Invoke-WebRequest -Uri "http://localhost:8083/api/artistes/1" -Method DELETE -Headers @{ Authorization = "Bearer <TOKEN>" } -UseBasicParsing
```

## Configuration

### Base de données

Les paramètres de connexion sont définis dans `application.properties` et synchronisés avec `docker-compose.yaml` :

| Propriété | Variable Env Docker (Service DB) | Valeur par défaut |
|---|---|---|
| `spring.datasource.url` | `DB_HOST`, `DB_PORT`, `DB_NAME` | `jdbc:mariadb://localhost:3307/test` |
| `spring.datasource.username` | `DB_USER` | `root` |
| `spring.datasource.password` | `DB_PASSWORD` | `monMotDePasseSuperSecret` |
| - | `MARIADB_DATABASE` | `test` |
| - | `MARIADB_ROOT_PASSWORD` | `monMotDePasseSuperSecret` |

Parametres applicatifs importants :

- `server.port=8083`
- `springdoc.swagger-ui.path=/swagger-ui.html`
- `springdoc.api-docs.path=/api-docs`
- `security.jwt.secret=<secret HS256 d'au moins 32 caracteres>`

## Développement sans Docker

Il est possible de tester localement en lancant une instance MariaDB sur votre machine (port `3307` par defaut pour ce projet), puis en executant l'application via Gradle ou votre IDE.

Assurez-vous :
- que les variables `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` correspondent a votre base locale
- que `security.jwt.secret` est defini avec une vraie cle secrete
