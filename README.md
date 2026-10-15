# Kampanjaloki

Kampanjaloki is a web application designed for documenting tabletop role-playing game (TTRPG) campaigns, sessions, events, and characters.

The different components of the application were developed as course projects for frontend and backend development courses.

This repository contains a React frontend and two separate backend implementations: a Node.js/SQLite server currently connected to the frontend and a separate Spring Boot application.

## Project Structure

| Directory | Description |
| --- | --- |
| `frontend/` | React 19 frontend, Vite development server, and Axios API requests |
| `nodeback/` | Express API and SQLite database; the frontend's current local backend |
| `spring-backend/` | Separate Spring Boot application with REST APIs and Thymeleaf views |
| `relaatiomalli.drawio`, `tietomalli.md` | Database model drafts |

The React frontend currently makes API requests using the `VITE_API_URL` environment variable and the Node.js server routes (`/campaigns`, `/characters`, `/sessions`, `/events`).

The Spring Boot REST endpoints use the `/api/` prefix, meaning Spring Boot is not currently the frontend's default backend. The backends are independent applications and do not automatically share a database.

## Requirements

- Node.js and npm (the frontend uses Vite 7; use a compatible Node.js version)
- Java 17 to run the Spring Boot application
- (Docker to build a container image for the Spring Boot application)

## Local Development

Start each component in a separate terminal.

### React Frontend

```powershell
cd frontend
npm ci
```

If necessary, create a `frontend/.env.local` file and configure the API URL:

```dotenv
VITE_API_URL=http://localhost:8080/
```

Start Vite:

```powershell
npm run dev
```

Vite will display the local URL where the application can be accessed in a browser (usually `http://localhost:5173`).

### Node.js/SQLite API

```powershell
cd nodeback
npm ci
node server.cjs
```

The server listens on port `8080`.

Start the server from the `nodeback/` directory because the paths to the SQLite database and uploaded images are relative to the current working directory.

The `nodeback/createDatabase.js` script generates sample data. It clears the existing campaigns, characters, sessions, and events tables before populating them.

### Spring Boot

The Spring Boot application can be developed independently in the `spring-backend/` directory.

Spring Boot reads a local `.env` file from the `spring-backend/` working directory. A local PostgreSQL configuration can look like this:

```dotenv
ENV_MODE=dev
DB_USER=your_database_user
DB_PASSWORD=your_database_password
DB_URL=jdbc:postgresql://localhost:5432/your_database
```

With `ENV_MODE=dev`, the current `application-dev.properties` uses an in-memory H2 database. The `DB_USER`, `DB_PASSWORD`, and `DB_URL` values above are therefore not used by that profile. To use the PostgreSQL settings, select the `prod` profile with `ENV_MODE=prod`; make sure the database exists and the credentials and JDBC URL are valid. The `rahti` profile uses its own deployment-specific environment variables.

Run the application from `spring-backend/` so Spring can find `.env`:

```powershell
cd spring-backend
.\mvnw.cmd spring-boot:run
```

The Spring Boot application will then be available at `http://localhost:8081`.

This does not change the React frontend's backend. The frontend will continue using its configured `VITE_API_URL` and the Node.js API routes. Spring Boot uses separate `/api/...` endpoints.

A Docker image can be built from the Spring Boot project directory:

```powershell
cd spring-backend
docker build -t kampanjaloki-spring .
```

The container listens on port `8080`. Production deployment requires configuring an appropriate application profile and database settings.

## Product Specification and Data Model

The following sections describe the intended functionality and data model of the application.

They do not imply that all features have been implemented or that both backends use the same data model.

### Introduction

Kampanjaloki is designed for recording and managing tabletop role-playing game sessions.

The application provides information about campaigns and their participating players. Users can create summaries of individual adventures (game sessions) and review the events that occurred during each session.

## System Specification

### Features

- Log in as a player, Dungeon Master (DM), or administrator.
- Create a campaign and add a description and image as a DM.
- Allow the DM to record individual adventures (sessions) within a campaign.
- Allow the DM to record multiple events within each adventure.
- Allow players to view campaign information, adventures, events, and characters.
- Restrict the visibility of information based on whether the user is a DM or a player.

### Possible Future Features

- Allow players to view campaign information, sessions, and events that have not been restricted from their view.
- Select participating characters when creating a session log.
- Assign a location to a session.
- Add non-player characters (NPCs) encountered during gameplay to a session.
- Retrieve data from the D&D 5e SRD API: https://5e-bits.github.io/docs/
- Generate AI-powered summaries.

### User Roles

User roles are defined in the `users` table. The system supports two user roles: `USER` and `ADMIN`.

The `campaign_members` table additionally defines whether a user participates in a campaign as a player or Dungeon Master (DM).

Using Kampanjaloki requires authentication. No application data should be accessible without logging in.

#### User

Users can participate in campaigns as either players or Dungeon Masters.

**Player**

Players use Kampanjaloki to follow campaign events.

A player can view campaign information, participating characters, individual adventures (sessions), and the events associated with each adventure.

Players can also create their own characters in the system and edit or delete them.

**Dungeon Master (DM)**

Dungeon Masters use Kampanjaloki to document campaign events.

A DM can create campaigns, add characters to campaigns, and create adventures representing individual game sessions.

Each adventure can contain multiple events.

The DM also has permission to edit and delete the associated campaign, adventures, and events.

#### Admin

Administrators have permission to view, create, edit, and delete the following data:

- Campaigns
- Adventures (sessions)
- Events
- Users
- Characters

## Database

### `app_users`

- `user_id` (PK)
- `username` (TEXT, UNIQUE)
- `password_hash` (TEXT NOT NULL)
- `created_at` (INTEGER)
- `role` (TEXT NOT NULL)

### `campaign_members`

- `campaign_member_id` (PK)
- `user_id` (FK → users.user_id)
- `campaign_id` (FK → campaigns.campaign_id)
- `role`

### `campaigns`

- `campaign_id` (PK)
- `name` (TEXT, UNIQUE)
- `description` (TEXT, nullable)
- `image_url` (TEXT, nullable)
- `created_at` (INTEGER/ISO)
- `owner` (FK → users.user_id)

### `campaign_characters`

- `campaign_character_id` (PK)
- `character_id` (FK → characters.character_id)
- `campaign_id` (FK → campaigns.campaign_id)

### `player_characters`

- `character_id` (PK)
- `name` (TEXT)
- `description` (TEXT)
- `image_url` (TEXT, nullable)
- `link` (TEXT)
- `owner` (FK → users.user.id)

### `sessions`

- `session_id` (PK)
- `campaign_id` (FK → campaigns.campaign_id)
- `title` (TEXT)
- `session_date` (TEXT, ISO yyyy-mm-dd)
- `content_md` (TEXT)
- `created_at` (INTEGER/ISO)
- `updated_at` (INTEGER/ISO)

### `events`

- `event_id` (PK)
- `session_id` (FK → sessions.session_id)
- `title` (TEXT)
- `order_index` (INTEGER)
- `summary` (TEXT)
- `details_md` (TEXT)

### Planned Database Extensions

#### `sessions`

- `is_dm_only` (BOOLEAN)

#### `events`

- `is_dm_only` (BOOLEAN)

#### `session_characters`

- `session_id` (FK → sessions.session_id)
- `character_id` (FK → characters.character_id)

#### `npcs`

- `npc_id` (PK)
- `campaign_id` (FK)
- `name` (TEXT)
- `summary`
- `details_md`
- `image_url`
- `tags` (TEXT, e.g., "shadow,ally")

#### `locations`

- `location_id`
- `campaign_id`
- `name`
- `summary`
- `details_md`
- `image_url`
- `tags` (TEXT)

### Database Table Descriptions

#### Users

Stores Kampanjaloki users, including their usernames, passwords, and assigned roles (`USER` or `ADMIN`).

The date and time of user creation are also recorded.

#### Campaign Members

A user can participate in a campaign as either a player or a Dungeon Master, represented by the roles `PLAYER` and `DM`.

#### Campaigns

Stores all campaigns.

Each campaign has a name and may include a description and an image.

The creation timestamp is recorded when a campaign is created.

Each campaign also has an owner who has permission to edit the campaign and its associated adventures and events.

#### Campaign Characters

Campaigns can contain multiple characters.

A character can participate in several different campaigns, so characters and campaigns are linked through a junction table.

The campaign owner, defined in the `campaigns` table, can add characters to or remove characters from the campaign.

#### Characters

Each character has a name and may include a description and an image.

A character also has a link to more detailed information on an external website and an assigned owner.

#### Sessions

A campaign consists of one or more adventures (game sessions).

Each session has a title, date, and content.

The system also records when the session was created and, if applicable, when it was last updated.

#### Events

Each session can contain multiple events.

An event may have a title and must contain content.

Events also have an ordering index that determines their sequence within a session.