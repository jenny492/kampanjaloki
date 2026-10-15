import { useEffect, useState } from 'react';
import { Box, Button, Paper, TextField, Typography } from '@mui/material';
import { Link, useNavigate } from 'react-router';
import { getCampaigns } from './Campaigns';

function CampaignSearch() {

    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [list, setList] = useState('');
    const [campaigns, setCampaigns] = useState([]);

    const getAllCampaigns = async () => {
        try {
            const response = await getCampaigns();
            if (response.status === 200) {
                setCampaigns(response.data);
            } else {
                navigate('/error', {
                    state: { viesti: 'Listaus ei onnistunut' }
                })
            }
        } catch (error) {
            navigate('/error', {
                state: { viesti: 'Listaus ei onnistunut' }
            })
        }
    }
    useEffect(() => { getAllCampaigns() }, [])

    if (campaigns.length == 0) {
        return (<Typography>Haetaan</Typography>)
    }

    const change = (e) => {
        setName(e.target.value);
    }

    const search = () => {
        let result = campaigns.filter(campaign => campaign.name.toUpperCase().includes(name.toUpperCase()))
            .map(campaign => (
                <Box key={campaign.id} sx={{ mb: 3 }}>
                    <Typography component={Link} to={'/campaign/' + campaign.id} variant='h5'>{campaign.name}</Typography>
                    <Typography>{campaign.description}</Typography>
                </Box>
            ));

        if (result.length === 0) {
            result = <Typography>Kampanjaa ei ole</Typography>;
        }

        setList(result);
    }

    return (
        <Box className="maincontent">
            <Paper sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>
                <Typography variant="h3">Haku</Typography>
                <Box component='form' sx={{ display: 'flex', justifyContent: 'center', gap: 2, marginTop: 4, marginBottom: 4 }} >
                    <TextField label="Kampanjan nimi"
                        name='name' value={name} onChange={(e) => change(e)} />
                    <Button onClick={() => search()} variant='contained' >Hae</Button>
                </Box>
                {list}
            </Paper>
        </Box>
    )
}
export default CampaignSearch;
