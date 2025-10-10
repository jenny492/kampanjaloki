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


