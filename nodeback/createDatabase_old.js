const sqlite3 = require('sqlite3').verbose();

const campaigns = [
  {
    id: 1,
    name: 'Beneath the black rose, tosi pitkä nimi, joka ei mahdu',
    description: 'Seikkailijat ovat kuolleet ja heränneet henkiin kummitustalossa, joka on täynnä salaisuuksia ja vaaroja. Heidän on selvitettävä, miksi heidät on herätetty ja miten he voivat paeta talosta ennen kuin on liian myöhäistä.',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: { id: 1 }
  },
  {
    id: 2,
    name: 'Kampanja 2',
    description: 'kampanjan kuvaus 2',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: { id: 1 }
  },
  {
    id: 3,
    name: 'Kampanja 3',
    description: 'kampanjan kuvaus 3',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: { id: 1 }
  },
  {
    id: 4,
    name: 'Kampanja 4',
    description: 'kampanjan kuvaus 3',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: { id: 1 }
  },
  {
    id: 5,
    name: 'Kampanja 5',
    description: 'kampanjan kuvaus 3',
    imageUrl: '',
    createdAt: '',
    owner: { id: 1 }
  }
];

const characters = [
  {
    id: 1,
    name: 'Hahmo 1',
    description: 'Hahmon kuvaus 1',
    imageUrl: '',
    link: '',
    owner: { id: 1 }
  },
  {
    id: 2,
    name: 'Hahmo 2',
    description: 'Hahmon kuvaus 2',
    imageUrl: 'characters/default.jpg',
    link: '',
    owner: { id: 2 }
  },
  { 
    id: 3,
    name: 'Hahmo 3',
    description: 'Hahmon kuvaus 3',
    imageUrl: 'characters/default.jpg',
    link: '',
    owner: { id: 2 }
  }
];

const sessions = [
  {
    id: 1,
    campaign: { id: 1 },
    title: '20.3.2025 - Session zero',
    content: 'Käytiin läpi säännöt ja luodaan hahmot. Pelinjohtaja esittelee kampanjan taustatarinan ja pelaajat suunnittelevat yhdessä hahmojensa välisiä suhteita. Lisäksi päästään tutustumaan pelimaailmaan ja sen tärkeimpiin paikkoihin.',
    session_date: '2025-11-02T15:36:11.077831',
    created_at: '2025-11-13T13:33:33.938184',
    updated_at: '2025-11-13T13:33:33.938184'
  },
  {
    id: 2,
    campaign: { id: 1 },
    title: 'Seikkailu 2',
    content: 'Ensimmäisen seikkailun jälkeen pelaajat jatkoivat matkaa kohti pohjoista, missä he kohtasivat uusia haasteita ja vihollisia. Heidän on myös löydettävä kadonnut kaupunki.', 
    session_date: '2025-11-12T15:02:23.090729',
    created_at: '2025-11-02T15:36:11.077831',
    updated_at: '2025-11-02T15:36:11.077831'
  },
  {
    id: 3,
    campaign: { id: 2 },
    title: 'Seikkailu 1',
    content: 'Toisen kampanjan ensimmäinen seikkailu. Pelaajat tutkivat muinaista luolastoa ja löysivät aarteita.',
    session_date: '2025-11-12T15:02:23.090729',
    created_at: '2025-11-02T15:36:11.077831',
    updated_at: '2025-11-02T15:36:11.077831'
  }
];

const events = [
  {
    id: 1,
    session: { id: 1 },
    title: '',
    orderIndex: 1,
    content: 'Pelaajat saapuvat metsään, jossa lohikäärmeen huhutaan asuvan. He valmistautuvat kohtaamiseen.',
    details: 'Jotakin lisätietoa'
  }, 
  {
    id: 2,
    session: { id: 1 },
    title: '',
    orderIndex: 2,
    content: 'Lohikäärme hyökkää yllättäen! Pelaajat taistelevat urheasti ja onnistuvat voittamaan sen.',
    details: 'Jotakin lisätietoa'
  },
  {
    id: 3,
    session: { id: 2 },
    title: '',
    orderIndex: 1,
    content: 'Lohikäärmeen kohtaamisen jälkeen pelaajat jatkavat matkaa kohti pohjoista. Heidän on tarkoitus löytää kadonnut kaupunki.',
    details: 'Jotakin lisätietoa'
  },
  { 
    id: 6,
    session: { id: 2 },
    title: '',
    orderIndex: 2,
    content: 'Matkalla pelaajat kohtaavat ryöväreitä, jotka yrittävät varastaa heidän aarteensa. Pelaajat onnistuvat torjumaan hyökkäyksen.',
    details: 'Jotakin lisätietoa'
  },
  {
    id: 7,
    session: { id: 2 },
    title: '',
    orderIndex: 3,
    content: 'Lopulta pelaajat löytävät kadonneen kaupungin raunioineen. He tutkivat paikkaa ja löytävät muinaisia salaisuuksia.',
    details: 'Jotakin lisätietoa'
  },
  {
    id: 4,
    session: { id: 3 },
    title: '',
    orderIndex: 1,
    content: 'Pelaajat saapuvat muinaiseen luolastoon. He tutkivat ympäristöä ja löytävät aarteita.',
    details: 'Jotakin lisätietoa'
  },
  {
    id: 5,
    session: { id: 3 },
    title: '',  
    orderIndex: 2,
    content: 'Yhtäkkiä luolaston vartija herää henkiin ja hyökkää pelaajien kimppuun. Pelaajat taistelevat ja onnistuvat voittamaan vartijan.',
    details: 'Jotakin lisätietoa'
  }
];


const db = new sqlite3.Database('./kampanjaloki.db');

db.serialize(() => {
  db.run('PRAGMA foreign_keys = ON');

  // Luo taulut
  db.run(`
    CREATE TABLE IF NOT EXISTS campaigns (
      id           INTEGER PRIMARY KEY,
      name         TEXT NOT NULL,
      description  TEXT,
      image_url    TEXT,
      created_at   TEXT,
      owner_id INTEGER
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS characters (
      id  INTEGER PRIMARY KEY,
      name         TEXT NOT NULL,
      description  TEXT,
      image_url    TEXT,
      link         TEXT,
      owner_id INTEGER
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS sessions (
      id    INTEGER PRIMARY KEY,
      campaign_id   INTEGER NOT NULL,
      title        TEXT NOT NULL,
      content      TEXT,
      session_date TEXT,
      created_at   TEXT,
      updated_at   TEXT,
      FOREIGN KEY (campaign_id) REFERENCES campaigns(campaign_id)
    )
  `);

  db.run(`
    CREATE TABLE IF NOT EXISTS events (
      id      INTEGER PRIMARY KEY,
      session_id    INTEGER NOT NULL,
      title        TEXT,
      order_index  INTEGER,
      content      TEXT,
      details      TEXT,
      FOREIGN KEY (session_id) REFERENCES sessions(session_id)
    )
  `);

  // Tyhjennä taulut (jos haluat "puhtaan seedauksen" joka kerta)
  db.run('DELETE FROM events');
  db.run('DELETE FROM sessions');
  db.run('DELETE FROM characters');
  db.run('DELETE FROM campaigns');

  // --- Insertoidaan DATA ---

  // CAMPAIGNS
  const insertCampaign = db.prepare(`
    INSERT INTO campaigns (campaignid, name, description, image_url, created_at, owner_userid)
    VALUES (?, ?, ?, ?, ?, ?)
  `);

  campaigns.forEach(c => {
    insertCampaign.run(
      c.campaignid,
      c.name,
      c.description,
      c.imageUrl || null,
      c.createdAt || null,
      c.owner?.userid ?? null
    );
  });

  insertCampaign.finalize();

  // CHARACTERS
  const insertCharacter = db.prepare(`
    INSERT INTO characters (characterid, name, description, image_url, link, owner_userid)
    VALUES (?, ?, ?, ?, ?, ?)
  `);

  characters.forEach(ch => {
    insertCharacter.run(
      ch.characterid,
      ch.name,
      ch.description,
      ch.imageUrl || null,
      ch.link || null,
      ch.owner?.userid ?? null
    );
  });

  insertCharacter.finalize();

  // SESSIONS
  const insertSession = db.prepare(`
    INSERT INTO sessions (sessionid, campaignid, title, content, session_date, created_at, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, ?)
  `);

  sessions.forEach(s => {
    insertSession.run(
      s.sessionid,
      s.campaign?.campaignid ?? null,
      s.title,
      s.content,
      s.session_date || null,
      s.created_at || null,
      s.updated_at || null
    );
  });

  insertSession.finalize();

  // EVENTS
  const insertEvent = db.prepare(`
    INSERT INTO events (eventid, sessionid, title, order_index, content, details)
    VALUES (?, ?, ?, ?, ?, ?)
  `);

  events.forEach(e => {
    insertEvent.run(
      e.eventid,
      e.session?.sessionid ?? null,
      e.title || null,
      e.orderIndex ?? null,
      e.content,
      e.details || null
    );
  });

  insertEvent.finalize();

  console.log('Tietokanta luotu ja tiedot lisätty!');
});

db.close();
