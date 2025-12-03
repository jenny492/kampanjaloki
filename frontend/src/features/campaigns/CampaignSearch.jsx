import { useState } from 'react';
import { Box, Button, Paper, TextField, Typography } from '@mui/material';
import { Link } from 'react-router';

function CampaignSearch({ campaigns }) {
    const [name, setName] = useState('');
    const [list, setList] = useState('');

    const change = (e) => {
        setName(e.target.value);
    }

    const search = () => {
        let result = campaigns.filter(campaign => campaign.name.toUpperCase().includes(name.toUpperCase()))
            .map(campaign => (
                <Box key={campaign.campaignid} sx={{ mb: 3 }}>
                    <Typography component={Link} to={'/campaign/' + campaign.campaignid} variant='h5'>{campaign.name}</Typography>
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
