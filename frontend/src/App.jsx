import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router';
import CampaignForm from './features/campaigns/CampaignForm'
import CampaignList from './features/campaigns/CampaignList'
import CampaignSearch from './features/campaigns/CampaignSearch'
import Frontpage from './components/Frontpage'
import Menu from './components/Menu'
import Error from './components/Error'

const campaigns = [
  {
    id: 1,
    name: 'Kampanja1',
    description: 'kampanjan kuvaus 1',
    image_url: '',
    created_at: ''
  },
  {
    id: 2,
    name: 'Kampanja2',
    description: 'kampanjan kuvaus 2',
    image_url: '',
    created_at: ''
  },
  {
    id: 3,
    name: 'Kampanja3',
    description: 'kampanjan kuvaus 3',
    image_url: '',
    created_at: ''
  }
]

function App() {

  return (
   <BrowserRouter>

      <Routes>
        <Route path='/' element={<Menu />} >
          <Route index element={<Frontpage />} />
          <Route path='/campaignform' element={<CampaignForm />} />
          <Route path='/campaigns' element={<CampaignList campaigns={campaigns} />} />
          <Route path='/search' element={<CampaignSearch campaigns={campaigns} />} />
          
          <Route path='/error' element={<Error />} />
          <Route path='*' element={<Error virheviesti='Kyseistä sivua ei ole' />} /> 
        </Route>
      </Routes>
    </BrowserRouter>
   
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
