import './App.css'
import CampaignForm from './components/CampaignForm'
import CampaignList from './components/CampaignList'
import CampaignSearch from './components/CampaignSearch'

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
    <>
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
    </>
  )
}

export default App
