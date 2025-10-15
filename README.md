# Kampanjaloki

## Kuvaus
Kampanjalokia käytetään pöytäroolipelien sessioiden kirjaamiseen. Lokista löytyvät kampanjan tiedot ja siihen kuuluvat pelaajat. Lokiin pystyy luomaan yhteenvetoja kampanjan sessioista, tarkistaa tapahtumat ja saadut tavarat. 

### Ominaisuudet
- kirjautuminen joko pelinjohtajana tai pelaajana
- kirjautuminen user tai admin -käyttäjänä
- kampanjan luominen ja kuvauksen ja kuvan lisääminen pelinjohtajana
- pelinjohtaja pystyy kirjaamaan kampanjalle yksittäisen session
- pelinjohtaja pystyy kirjaamaan sessiolle useita tapahtumia
- tietojen näkymistä voi rajata pelinjohtajien ja pelaajan näkymiin

### Mahdollisia laajennuksia
- pelaaja pystyy tarkastelemaan kampanjan tietoja, sessioita ja tapahtumia, joita ei ole rajattu pois hänen näkymästään
- session kirjauksen alussa hahmojen valinta
- sessiolle voi valita tapahtumapaikan
- sessiolle voi lisätä pelin aikana tavatut NPC:t
- tietojen hakeminen D&D 5e SRD API:sta https://5e-bits.github.io/docs/
- tekoälypohjainen tiivistelmän luominen


## Tietomalli

`users`
- user_id (PK)
- username (TEXT, UNIQUE) 
- password_hash (TEXT NOT NULL)
- created_at (INTEGER)
- role (TEXT NOT NULL)

`campaign_members`
- campaign_member_id (PK)
- user_id (FK -> users.user_id)
- campaign_id (FK -> campaigns.campaign_id)
- role 

`campaigns`
- campaign_id (PK)
- name (TEXT, UNIQUE)
- description (TEXT, nullable)
- image_url (TEXT, nullable)
- created_at (INTEGER/ISO)
- owner (FK -> users.user_id)

`campaign_characters`
- campaign_character_id (PK)
- character_id (FK -> characters.character_id)
- campaign_id (FK -> campaigns.campaign_id)

`characters`
- character_id (PK)
- name (TEXT)
- description (TEXT)
- image_url (TEXT, nullable)
- link (TEXT)

`sessions`
- session_id (PK)
- campaign_id (FK -> campaigns.campaign_id)
- title (TEXT)
- session_date (TEXT, ISO yyyy-mm-dd)
- content_md (TEXT)
- created_at (INTEGER/ISO)
- updated_at (INTEGER/ISO)

`events`
- event_id (PK)
- session_id (FK -> sessions.session_id)
- title (TEXT)
- order_index (INTEGER)
- summary (TEXT)
- details_md (TEXT)


### Myöhemmin laajennettavat 

`sessions`
- is_dm_only BOOLEAN

`events`
- is_dm_only BOOLEAN

`session_characters`
- session_id (FK -> sessions.session_id)
- character_id (FK -> characters.character_id)

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

#