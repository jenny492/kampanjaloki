# Tietomalli

## Versio 1

`app_users`
- user_id
- username
- password_hash
- created_at

`user_roles`
- user_id
- role_id

`roles`
- role_id
- name

`campaigns`
- campaign_id (PK)
- name (TEXT, UNIQUE)
- description (TEXT, nullable)
- image_url (TEXT, nullable)
- created_at (INTEGER/ISO)
- owner_user_id (INTEGER)

`campaign_characters`
- character_id
- campaing_id

`characters`
- character_id (PK)
- name (TEXT)
- description (TEXT)
- image_url (TEXT, nullable)
- link (TEXT)

`sessions`
- session_id (PK)
- campaign_id (FK → campaigns.id)
- title (TEXT)
- session_date (TEXT, ISO yyyy-mm-dd)
- content_md (TEXT)
- created_at (INTEGER/ISO)
- updated_at (INTEGER/ISO)

`events`
- event_id
- session_id
- title
- order_index
- summary
- details_md

### Myöhemmin laajennettavat 

`npcs`
- npc_id (PK)
- campaign_id (FK)
- name (TEXT)
- summary 
- details_md
- image_url
- tags (TEXT, esim. "shadow,ally")

`locations`
- location_id
- campaign_id
- name
- summary
- details_md
- image_url
- tags (TEXT)

### Taulujen kuvaukset

#### Campaigns
Sisältää kaikki kampanjat. Kampanjalla on nimi ja sillä voi olla kuvaus ja kuva. Kampanja luodaan tiettynä hetkenä ja tallennushetki tallennetaan. 

#### Sessions
Kampanjaan kuuluu sessioita ja niitä voi olla useita. Sessiolla on otsikko, päivämäärä ja sisältö. Lisäksi tallennetaan tallennusajankohta sekä mahdollinen päivitysaika. 

#### Events
Yhdelle sessiolle voi kirjata useita tapahtumia. 

#### Characters
Hahmolla on nimi ja sillä voi olla kuvaus ja kuva. Hahmolla on myös linkki sen tarkempiin tietoihin toisella sivustolla. 

#### Campaign characters
Kampanjaan voi kuulua hahmoja. Hahmo voi kuulua useampaan eri kampanjaan, joten se liitetään kampanjaan välitaulun avulla.


quickdatabasediagrams.com

users
-
user_id PK int
username string UNIQUE 
password_hash string
created_at int
role string

campaign_members
-
campaign_member_id PK
user_id int FK >- users.user_id INDEX
campaign_id int FK >- campaigns.campaign_id INDEX
role string

campaigns
-
campaign_id PK int
name string UNIQUE 
description NULL string
image_url NULL string
created_at int
owner int FK >- users.user_id INDEX

campaign_characters
-
campaign_character_id PK
character_id int FK >- characters.character_id INDEX
campaign_id int FK >- campaigns.campaign_id INDEX

characters
-
character_id PK int
name string
description string
image_url NULL string
link string

sessions
-
session_id PK int
campaign_id int FK >- campaigns.campaign_id INDEX
title string
session_date string
content_md string
created_at int
updated_at int

events
-
event_id PK int
session_id int FK >- sessions.session_id INDEX
title string
order_index int
summary string
details_md string