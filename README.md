# Kampanjaloki

## Johdanto

Kampanjalokia käytetään pöytäroolipelien sessioiden kirjaamiseen. Lokista löytyvät kampanjan tiedot ja siihen kuuluvat pelaajat. Lokiin pystyy luomaan yhteenvetoja kampanjan seikkailuista eli yhdestä pelikerrasta ja tarkistaa yhden seikkailun tapahtumat. 

Kampanjaloki toteutetaan käyttäen --
- Palvelinpuolen ratkaisut
- Käyttöliittymä
    - Kampanjalokia voi käyttää selaimen kautta älypuhelimella, tabletilla ja tietokoneella

## Järjestelmän määrittely

### Toiminnot

- kirjautuminen pelaajana, pelinjohtajana tai admin-käyttäjänä
- kampanjan luominen ja kuvauksen ja kuvan lisääminen pelinjohtajana
- pelinjohtaja pystyy kirjaamaan kampanjalle yksittäisen seikkailun
- pelinjohtaja pystyy kirjaamaan seikkailulle useita tapahtumia
- pelaaja pystyy tarkastelemaan kampanjan tietoja, seikkailuja, tapahtumia ja hahmoja
- tietojen näkymistä voi rajata pelinjohtajien ja pelaajan näkymiin

### Mahdollisia laajennuksia

- pelaaja pystyy tarkastelemaan kampanjan tietoja, sessioita ja tapahtumia, joita ei ole rajattu pois hänen näkymästään
- session kirjauksen alussa hahmojen valinta
- sessiolle voi valita tapahtumapaikan
- sessiolle voi lisätä pelin aikana tavatut NPC:t
- tietojen hakeminen D&D 5e SRD API:sta https://5e-bits.github.io/docs/
- tekoälypohjainen tiivistelmän luominen

### Käyttäjäroolit

Käyttäjäroolit määritellään users-taulussa. Käyttäjärooleja ovat user- ja admin-roolit Lisäksi campaign_members-taulussa on määritelty onko käyttäjä (user) kampanjan pelaaja vai pelinjohtaja. Kampanjalokin käyttö vaatii kirjautumisen ja mitään tietoja ei voi näyttää ilman kirjautumista. 

> #### User
> 
> Käyttäjiä voi olla pelaajan ja pelinjohtajan roolissa. 
> 
> **Pelaaja** Pelaaja käyttää Kampanjalokia tapahtumien seuraamiseen. Hän pystyy tarkastelemaan kampanjan tietoja ja siihen kuuluvia hahmoja, yksittäisiä seikkailuja ja seikkailuun kuuluvia tapahtumia. Pelaaja pystyy luomaan itselleen hahmon järjestelmään ja hän pystyy muokkaamaan ja poistamaan sen. 
> 
> **Pelinjohtaja** Pelinjohtaja käyttää Kampanjalokia kampanjan tapahtumien tallentamiseen. Hän pystyy luomaan kampanjan ja lisäämään sille hahmoja ja luomaan  seikkailuja eli yksittäisiä pelikertoja. Yhteen seikkailuun hän pystyy luomaan useita tapahtumia. Pelinjohtajalla on myös oikeudet muokata ja poistaa oheisia tietoja: kampanja, seikkailu ja tapahtuma. 

> #### Admin
> 
> Admin-käyttäjällä on oikeudet näyttää, luoda, muokata ja poistaa oheisia tietoja: kampanja, seikkailu, tapahtuma, käyttäjä ja hahmo. 

## Tietokanta

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
- owner (FK -> users.user.id)

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

#### Users

Kampanjalokin käyttäjät, joilla on käyttäjänimi ja salasana sekä käyttäjäroolina 'USER' tai 'ADMIN'. Lisäksi tallennetaan käyttäjän luomisen ajankohta.

#### Campaign members

Käyttäjä voi olla kampanjassa joko pelaajana tai pelinjohtajana, joka määritellään roolina 'PLAYER' tai 'DM'. 

#### Campaigns

Sisältää kaikki kampanjat. Kampanjalla on nimi ja sillä voi olla kuvaus ja kuva. Kampanja luodaan tiettynä hetkenä ja tallennushetki tallennetaan. Kampanjalle tallennetaan omistaja, jolla on muokkausoikeudet kampanjan ja sen sisältämien seikkailujen ja tapahtumien tietoihin. 

#### Campaign characters

Kampanjaan voi kuulua hahmoja. Hahmo voi kuulua useampaan eri kampanjaan, joten se liitetään kampanjaan välitaulun avulla. Kampanja-taulussa määritetty omistaja voi lisätä ja poistaa kampanjaan kuuluvia hahmoja. 

#### Characters

Hahmolla on nimi ja sillä voi olla kuvaus ja kuva. Hahmolla on myös linkki sen tarkempiin tietoihin toisella sivustolla sekä omistaja. 

#### Sessions

Kampanjaan kuuluu seikkailuja ja niitä voi olla useita. Seikkailulla on otsikko, päivämäärä ja sisältö. Lisäksi tallennetaan tallennusajankohta sekä mahdollinen päivitysaika. 

#### Events

Yhdelle seikkailulle voi kirjata useita tapahtumia. Tapahtumalla voi olla otsikko ja sillä on oltava sisältö. Tapahtumalla on myös järjestyksen kertova avain, jonka avulla tapahtumat saa järjestettyä. 




