import PostAddIcon from '@mui/icons-material/PostAdd';
import { Box, Button, CardMedia, Container, Typography } from "@mui/material";
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router";
import Error from "../../components/Error";
import { getCampaignById, getCampaigns } from './Campaigns';
import { getSessions } from '../sessions/Sessions';
import { getEvents } from '../events/Events';


function CampaignDetail() {

    const navigate = useNavigate(); 

    const [campaigns, setCampaigns] = useState([]);
    const [campaign, setCampaign] = useState(null);
    const [sessions, setSessions] = useState([]);
    const [events, setEvents] = useState([]);

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

    let { id } = useParams();
    id = Number(id);

    const getOneCampaign = async () => { 
        try {
            const response = await getCampaignById(id);
            if (response.status === 200) {
                setCampaign(response.data);
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
    useEffect(() => { getOneCampaign() }, [])

    const getAllSessions = async () => {
        try {
            const response = await getSessions();
            if (response.status === 200) {
                setSessions(response.data);
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
    useEffect(() => { getAllSessions() }, [])

    const getAllEvents = async () => {
        try {
            const response = await getEvents();
            if (response.status === 200) {
                setEvents(response.data);
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
    useEffect(() => { getAllEvents() }, [])

    if (campaigns.length == 0) {
        return (<Typography>Haetaan</Typography>)
    }

    if (!campaign) {
        return (<Error ErrorMsg="Kampanjaa ei löydy" />)
    }

    return (
        <Container className="maincontent">

            <Box sx={{
                display: 'flex',
                justifyContent: 'space-between',
                gap: 4,
                flexDirection: { xs: 'column', md: 'row' }
            }}>
                {campaign.imageUrl ?
                    <CardMedia sx={{ width: 250 }} component='img' image={'/' + campaign.imageUrl} />
                    :
                    <CardMedia sx={{ width: 250 }} component='img' image={'/campaigns/default.jpg'} />
                }
                <Box sx={{ flex: 1, textAlign: 'left' }}>
                    <Typography variant="h3">{campaign.name}</Typography>
                    <Typography >{campaign.description}</Typography>
                </Box>

            </Box>

            <Box sx={{ marginTop: 4 }}>

                <Typography variant="h4">Seikkailut</Typography>

                <Button component={Link} to={"/session/" + campaign.id}
                    variant='contained'
                    sx={{ marginTop: 2, marginBottom: 2 }}
                    startIcon={<PostAddIcon />} >Luo uusi seikkailu</Button>

                {
                    sessions.filter(s => s.campaign_id === id).map(s => (

                        <Box key={s.id} sx={{ gap: 4, marginTop: 4, padding: 2 }}>

                            <Box sx={{ flex: 1, textAlign: 'left' }}>
                                <Typography variant="h5">{s.title}</Typography>
                                <Typography>{s.content}</Typography>
                            </Box>

                            <Box sx={{ flex: 1, textAlign: 'left', marginTop: 2 }}>
                                <Typography variant="h5">Tapahtumat</Typography>

                                {
                                    events.filter(e => e.session_id === s.id).map(e => (

                                        <Box key={e.id} sx={{ marginTop: 2, paddingLeft: 2, borderLeft: '4px solid grey' }}>
                                            <Typography>{e.content}</Typography>
                                        </Box>
                                    ))}
                                <Button component={Link} to={"/event/" + campaign.id}
                                    variant='contained'
                                    sx={{ marginTop: 2, marginBottom: 2 }}
                                    startIcon={<PostAddIcon />} >Lisää tapahtuma</Button>
                            </Box>
                        </Box>
                    ))
                }
            </Box>

        </Container>
    );
}

export default CampaignDetail;