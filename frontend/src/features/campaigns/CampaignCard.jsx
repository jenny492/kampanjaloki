import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { Card, CardActions, CardContent, CardMedia, Button, IconButton, Typography } from '@mui/material';
import { Link } from 'react-router';



function CampaignCard({ campaign }) {

    return (
        
            <Card >
                {campaign.imageUrl ?
                    <CardMedia sx={{ height: 140 }} component='img' image={campaign.imageUrl} />
                    :
                    <CardMedia sx={{ height: 140 }} component='img' image={'/campaigns/default.jpg'} />
                }

                <CardContent>
                    <Typography component={Link} to={'/campaign/' + campaign.campaignid} >{campaign.name.length <= 30 ? campaign.name : (campaign.name.substr(0, 30) + "...")}</Typography>
                </CardContent>

                <CardActions sx={{ justifyContent: 'center' }}>
                    <Button component={Link} to={'/campaign/' + campaign.campaignid}
                        size="small"
                        startIcon={<VisibilityIcon />}
                        variant='contained'>
                        Näytä</Button>
                    <Button component={Link} to={'/campaignedit/' + campaign.campaignid}
                        size="small"
                        startIcon={<EditIcon />}
                        variant='contained'>
                        Muokkaa</Button>
                    <IconButton component={Link} to={""}><DeleteIcon color='warning' /></IconButton>


                    {/*  <IconButton component={Link} to={'/edit/' + campaign.campaignid}><EditIcon color='primary' /></IconButton>
                <IconButton><DeleteIcon color='secondary' /></IconButton> */}
                </CardActions>
            </Card>
        
    );
}
export default CampaignCard;