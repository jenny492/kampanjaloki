import { useState } from 'react';

function CampaignForm() {

  const [campaign, setValues] = useState({
    name: '',
    description: ''
  });

  const [msg, setMsg] = useState('');

  const changeCampaign = (e) => {
    setValues(
      {
        ...campaign,
        [e.target.name]: e.target.value
      }
    );
    setMsg('');
  }

  const addCampaign = () => {
    setValues({
      name: '',
      description: ''
    });
    setMsg("Kampanja lisätty")
  }

    return (
    <>
  <h2>Lisää kampanja</h2>
    <form>
      <label>Kampanjan nimi<br />
        <input type='text' name='name' value={campaign.name} onChange={(e) => changeCampaign(e)} /><br />
      </label>

      <label>Kuvaus<br />
        <textarea type='text' name='description' value={campaign.description} onChange={(e) => changeCampaign(e)} /><br />
      </label>

      <input type='button' value='Lisää' onClick={() => addCampaign()} />
    </form>
    {<p>{msg}</p>} 
    </>
  ); 

}

export default CampaignForm;