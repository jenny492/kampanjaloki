# Kampanjaloki

## Kuvaus
Kampanjalokia käytetään pöytäroolipelien sessioiden kirjaamiseen. Lokista löytyvät kampanjan tiedot ja siihen kuuluvat pelaajat. Lokiin pystyy luomaan yhteenvetoja kampanjan sessioista, tarkistaa tapahtumat ja saadut tavarat. 

### Ominaisuudet:
- kirjautuminen joko pelinjohtajana tai pelaajana
- kampanjan luominen ja kuvauksen, kuvan ja pelaajien lisääminen
- tietojen näkymisen rajaaminen pelinjohtajien ja pelaajan näkymiin
- yksittäisen session kirjaus
- tietojen hakeminen D&D 5e SRD API:sta https://5e-bits.github.io/docs/
- pelaajien lisääminen ja listaus


## Tietomalli

`campaigns`
- id (PK)
- name (TEXT, UNIQUE)
- description (TEXT, nullable)
- image_url (TEXT, nullable)
- created_at (INTEGER/ISO)

`sessions`
- id (PK)
- campaign_id (FK → campaigns.id)
- title (TEXT)
- session_date (TEXT, ISO yyyy-mm-dd)
- content_md (TEXT)
- created_at (INTEGER/ISO)
- updated_at (INTEGER/ISO)

`characters`
- id (PK)
- name (TEXT)
- description (TEXT)
- image_url (TEXT, nullable)
- link (TEXT)

`character in campaign`
- id (PK)
- character_id (FK)
- campaing_id (FK)

npcs
- id, campaign_id, name, summary, details_md, image_url, tags (TEXT, esim. "shadow,ally")

items
- id, campaign_id, name, rarity, details_md, tags (TEXT)

locations
- id, campaign_id, name, summary, details_md, image_url, tags (TEXT)

### Taulujen kuvaukset

#### Campaigns
Sisältää kaikki kampanjat. Kampanjalla on nimi ja sillä voi olla kuvaus ja kuva. Kampanja luodaan tiettynä hetkenä ja tallennushetki tallennetaan. 

#### Sessions
Kampanjaan kuuluu sessioita ja niitä voi olla useita. Sessiolla on otsikko, päivämäärä ja sisältö. Lisäksi tallennetaan tallennusajankohta sekä mahdollinen päivitysaika. 

#### Characters
Hahmolla on nimi ja sillä voi olla kuvaus ja kuva. Hahmolla on myös linkki sen tarkempiin tietoihin toisella sivustolla. 

#### Characters in campaign
Kampanjaan voi kuulua hahmoja. Hahmo voi kuulua useampaan eri kampanjaan, joten se liitetään kampanjaan välitaulun avulla.