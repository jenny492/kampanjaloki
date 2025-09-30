import { useState } from 'react';

function CampaignSearch({ campaigns }) {
    const [name, setName] = useState(''); 
    const [list, setList] = useState('');

    const change = (e) => {
        setName(e.target.value); 
    }

    const search = () => {
        let result = campaigns.filter(campaign => campaign.name.toUpperCase().includes(name.toUpperCase()))
            .map(campaign => (
                <p key={campaign.id}>
                    {campaign.name}<br />
                    Kuvaus: {campaign.description}<br />
                </p>
            ));
      
        if (result.length === 0) {
            result = <p>Kampanjaa ei ole</p>;
        }

        setList(result);
    }

    return (
        <>
        <h2>Kampanjahaku</h2>
            <form>
                <label>Nimi &nbsp;
                    <input type='text' name='name' value={name} onChange={(e) => change(e)} />&nbsp;
                </label>
                <input type='button' value='Hae' onClick={() => search()} />
            </form>

            {list}
        </>
    )
}
export default CampaignSearch;
