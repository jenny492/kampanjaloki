import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router';
import CampaignForm from './features/campaigns/CampaignForm'
import CampaignList from './features/campaigns/CampaignList'
import CampaignSearch from './features/campaigns/CampaignSearch'
import CharacterList from './features/characters/CharacterList'
import Frontpage from './components/Frontpage'
import DrawerMenu from './components/DrawerMenu'
import AppBarMenu from './components/AppBarMenu'
import Error from './components/Error'
import { createTheme, ThemeProvider } from '@mui/material';
import CampaignDetail from './features/campaigns/CampaignDetail';
import SessionForm from './features/sessions/SessionForm';
import EventForm from './features/events/EventForm';
import CampaignEditForm from './features/campaigns/CampaignEditForm';
import Dashboard from './features/dashboard/Dashboard';

const campaigns = [
  {
    campaignid: 1,
    name: 'Beneath the black rose, tosi pitkä nimi, joka ei mahdu',
    description: 'Seikkailijat ovat kuolleet ja heränneet henkiin kummitustalossa, joka on täynnä salaisuuksia ja vaaroja. Heidän on selvitettävä, miksi heidät on herätetty ja miten he voivat paeta talosta ennen kuin on liian myöhäistä.',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: {userid: 1}
  },
  {
    campaignid: 2,
    name: 'Kampanja 2',
    description: 'kampanjan kuvaus 2',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: {userid: 1}
  },
  {
    campaignid: 3,
    name: 'Kampanja 3',
    description: 'kampanjan kuvaus 3',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: {userid: 1}
  },
  {
    campaignid: 4,
    name: 'Kampanja 4',
    description: 'kampanjan kuvaus 3',
    imageUrl: 'campaigns/default.jpg',
    createdAt: '',
    owner: {userid: 1}
  },
  {
    campaignid: 5,
    name: 'Kampanja 5',
    description: 'kampanjan kuvaus 3',
    imageUrl: '',
    createdAt: '',
    owner: {userid: 1}
  }
]

const characters = [
  {
    characterid: 1,
    name: 'Hahmo 1',
    description: 'Hahmon kuvaus 1',
    imageUrl: '',
    link: '',
    owner: {userid: 1}
  },
  {
    characterid: 2,
    name: 'Hahmo 2',
    description: 'Hahmon kuvaus 2',
    imageUrl: 'characters/default.jpg',
    link: '',
    owner: {userid: 2}
  },
  { 
    characterid: 3,
    name: 'Hahmo 3',
    description: 'Hahmon kuvaus 3',
    imageUrl: 'characters/default.jpg',
    link: '',
    owner: {userid: 2}
  }
]

const sessions = [
  {
    sessionid: 1,
    campaign: {campaignid: 1},
    title: '20.3.2025 - Session zero',
    content: 'Käytiin läpi säännöt ja luodaan hahmot. Pelinjohtaja esittelee kampanjan taustatarinan ja pelaajat suunnittelevat yhdessä hahmojensa välisiä suhteita. Lisäksi päästään tutustumaan pelimaailmaan ja sen tärkeimpiin paikkoihin.',
    session_date: '2025-11-02T15:36:11.077831',
    created_at: '2025-11-13T13:33:33.938184',
    updated_at: '2025-11-13T13:33:33.938184'
  },
  {
    sessionid: 2,
    campaign: {campaignid: 1},
    title: 'Seikkailu 2',
    content: 'Ensimmäisen seikkailun jälkeen pelaajat jatkoivat matkaa kohti pohjoista, missä he kohtasivat uusia haasteita ja vihollisia. Heidän on myös löydettävä kadonnut kaupunki.', 
    session_date: '2025-11-12T15:02:23.090729',
    created_at: '2025-11-02T15:36:11.077831',
    updated_at: '2025-11-02T15:36:11.077831'
  },
  {
    sessionid: 3,
    campaign: {campaignid: 2},
    title: 'Seikkailu 1',
    content: 'Toisen kampanjan ensimmäinen seikkailu. Pelaajat tutkivat muinaista luolastoa ja löysivät aarteita.',
    session_date: '2025-11-12T15:02:23.090729',
    created_at: '2025-11-02T15:36:11.077831',
    updated_at: '2025-11-02T15:36:11.077831'
  }
]

const events = [
  {
    eventid: 1,
    session: {sessionid: 1},
    title: '',
    orderIndex: 1,
    content: 'Pelaajat saapuvat metsään, jossa lohikäärmeen huhutaan asuvan. He valmistautuvat kohtaamiseen.',
    details: 'Jotakin lisätietoa'
}, 
{
    eventid: 2,
    session: {sessionid: 1},
    title: '',
    orderIndex: 2,
    content: 'Lohikäärme hyökkää yllättäen! Pelaajat taistelevat urheasti ja onnistuvat voittamaan sen.',
    details: 'Jotakin lisätietoa'
},
{
    eventid: 3,
    session: {sessionid: 2},
    title: '',
    orderIndex: 1,
    content: 'Lohikäärmeen kohtaamisen jälkeen pelaajat jatkavat matkaa kohti pohjoista. Heidän on tarkoitus löytää kadonnut kaupunki.',
    details: 'Jotakin lisätietoa'
},
{ 
    eventid: 6,
    session: {sessionid: 2},
    title: '',
    orderIndex: 2,
    content: 'Matkalla pelaajat kohtaavat ryöväreitä, jotka yrittävät varastaa heidän aarteensa. Pelaajat onnistuvat torjumaan hyökkäyksen.',
    details: 'Jotakin lisätietoa'
},
{
    eventid: 7,
    session: {sessionid: 2},
    title: '',
    orderIndex: 3,
    content: 'Lopulta pelaajat löytävät kadonneen kaupungin raunioineen. He tutkivat paikkaa ja löytävät muinaisia salaisuuksia.',
    details: 'Jotakin lisätietoa'
},
  {
    eventid: 4,
    session: {sessionid: 3},
    title: '',
    orderIndex: 1,
    content: 'Pelaajat saapuvat muinaiseen luolastoon. He tutkivat ympäristöä ja löytävät aarteita.',
    details: 'Jotakin lisätietoa'
  },
  {
    eventid: 5,
    session: {sessionid: 3},
    title: '',  
    orderIndex: 2,
    content: 'Yhtäkkiä luolaston vartija herää henkiin ja hyökkää pelaajien kimppuun. Pelaajat taistelevat ja onnistuvat voittamaan vartijan.',
    details: 'Jotakin lisätietoa'
  }
]


const theme = createTheme({
  typography: {
    h3: {
      marginBottom: 10,
    }
  },
  palette: {
    primary: {
      main: '#e8eaf6',
    },
    secondary: {
      main: '#c5cae9',
    },
  },
});

function App() {

  return (
    <ThemeProvider theme={theme} >
    <BrowserRouter>

      <Routes>
        <Route path='/' element={<AppBarMenu />} >
          <Route index element={<Frontpage />} />
          <Route path='/campaignform' element={<CampaignForm />} />
          <Route path='/campaigns' element={<CampaignList campaigns={campaigns} />} />
          <Route path='/search' element={<CampaignSearch campaigns={campaigns} />} />
          <Route path='/characters' element={<CharacterList characters={characters} />} />
          <Route path='/campaign/:id' element={<CampaignDetail campaigns={campaigns} sessions={sessions} events={events} />} />
          <Route path='/session/:id' element={<SessionForm />} />
          <Route path='/event/:id' element={<EventForm />} />
          <Route path='/campaignedit/:id' element={<CampaignEditForm campaigns={campaigns} />} />
          <Route path='/dashboard' element={<Dashboard />} />

          <Route path='/error' element={<Error />} />
          <Route path='*' element={<Error ErrorMsg='Kyseistä sivua ei ole' />} />
        </Route>
      </Routes>
    </BrowserRouter>
    </ThemeProvider>

    /*  <>
      <div>
        <img src="dndrulebook.svg" className="logo"></img>
        <h1>Kampanjaloki</h1>
        Roolipelin muistiinpanot, hahmot, kartat ja tavarat kaikki yhdessä paikassa. Kampanjalokin avulla kaikki kampanjan tiedot pysyvät tallessa, jotta voit keskittyä siihen tärkeimpään: pelaamiseen.  
      </div>
      <div>
        <CampaignList campaigns={campaigns} />
      </div>
      <div>
        <CampaignForm />
      </div>
      <div>
        <CampaignSearch campaigns={campaigns} />
      </div>
    </> */
  )
}

export default App;
