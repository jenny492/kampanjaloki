import { Box, Button, Card, CardContent, CardMedia, Container, Typography } from "@mui/material";
import { useParams, Link } from "react-router";
import Error from "../../components/Error";
import PostAddIcon from '@mui/icons-material/PostAdd';

function CampaignDetail({ campaigns, sessions, events }) {

    let { id } = useParams();
    id = Number(id);
    let found = campaigns.find(c => c.campaignid === id);

    if (!found) {
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
                {found.imageUrl ?
                    <CardMedia sx={{ width: 250 }} component='img' image={'/' + found.imageUrl} />
                    :
                    <CardMedia sx={{ width: 250 }} component='img' image={'/campaigns/default.jpg'} />
                }
                <Box sx={{ flex: 1, textAlign: 'left' }}>
                    <Typography variant="h3">{found.name}</Typography>
                    <Typography >{found.description}</Typography>
                </Box>

            </Box>

            <Box sx={{ marginTop: 4 }}>

                <Typography variant="h4">Seikkailut</Typography>

                <Button component={Link} to={"/session/" + found.campaignid}
                    variant='contained'
                    sx={{ marginTop: 2, marginBottom: 2 }}
                    startIcon={<PostAddIcon />} >Luo uusi seikkailu</Button>

                {
                    sessions.filter(s => s.campaign.campaignid === id).map(s => (

                        <Box key={s.sessionid} sx={{ gap: 4, marginTop: 4, padding: 2 }}>

                            <Box sx={{ flex: 1, textAlign: 'left' }}>
                                <Typography variant="h5">{s.title}</Typography>
                                <Typography>{s.content}</Typography>
                            </Box>

                            <Box sx={{ flex: 1, textAlign: 'left', marginTop: 2 }}>
                                <Typography variant="h5">Tapahtumat</Typography>

                                {
                                    events.filter(e => e.session.sessionid === s.sessionid).map(e => (

                                        <Box key={e.eventid} sx={{ marginTop: 2, paddingLeft: 2, borderLeft: '4px solid grey' }}>
                                            <Typography>{e.content}</Typography>
                                        </Box>
                                    ))}
                                <Button component={Link} to={"/event/" + found.campaignid}
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