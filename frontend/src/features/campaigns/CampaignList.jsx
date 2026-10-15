import AddIcon from '@mui/icons-material/Add';
import { Box, Button, Grid, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import { getCampaigns } from './Campaigns';
import { Link, useNavigate } from 'react-router';
import CampaignCard from './CampaignCard';

function CampaignList() {

    const navigate = useNavigate(); 

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
    };

    useEffect(() => { getAllCampaigns() }, []);

    if (campaigns.length == 0) {
        return (<Typography>Haetaan</Typography>)
    };

    return (

        <Box className="maincontent">
            <Typography variant="h3">Kampanjat</Typography>

            <Button component={Link} to={"/campaignform"}
                variant='contained'
                startIcon={<AddIcon />}
                sx={{ marginTop: 4, marginBottom: 4 }} >
                Luo uusi kampanja</Button>

            <Grid
                container
                spacing={{ xs: 2, md: 3 }}
                columns={{ xs: 4, sm: 8, md: 12 }}
                sx={{ marginTop: 2, marginBottom: 2, justifyContent: 'center' }}>
                {
                    campaigns.map(campaign => {
                        return (
                            <Grid key={campaign.id} xs={3} sm={4} md={4}>
                                <CampaignCard campaign={campaign} />
                            </Grid>
                        )
                    })
                }

            </Grid>
        </Box >
    );
}

export default CampaignList;