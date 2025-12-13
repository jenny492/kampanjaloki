import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { Card, CardActions, CardContent, CardMedia, Button, IconButton, Typography } from '@mui/material';
import { Link, useNavigate } from 'react-router';
import { deleteCampaign } from './Campaigns';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

function CampaignCard({ campaign }) {

    const navigate = useNavigate();

    const handleDelete = async (id) => {
        const res = await deleteCampaign(id);
        if (res.status === 200) {
            window.location.reload();
        } else {
            navigate('/error', { state: { errorMsg: 'Poisto ei onnistunut' } });
        }
    };

    const submit = () => {
        confirmAlert({
            title: "Haluatko poistaa kampanjan?",
            buttons: [
                {
                    label: "Kyllä",
                    onClick: () => handleDelete(campaign.id)
                },
                {
                    label: "Ei"
                }
            ]
        });
    };

    const imageSrc = campaign.imageUrl || campaign.image_url || '/campaigns/default.jpg';

    return (

        <Card >
            <CardMedia sx={{ height: 140 }} component='img' image={imageSrc} />

            <CardContent>
                <Typography component={Link} to={'/campaign/' + campaign.id} >{campaign.name.length <= 30 ? campaign.name : (campaign.name.substr(0, 30) + "...")}</Typography>
            </CardContent>

            <CardActions sx={{ justifyContent: 'center' }}>
                <Button component={Link} to={'/campaign/' + campaign.id}
                    size="small"
                    startIcon={<VisibilityIcon />}
                    variant='contained'>
                    Näytä</Button>
                <Button component={Link} to={'/campaignedit/' + campaign.id}
                    size="small"
                    startIcon={<EditIcon />}
                    variant='contained'>
                    Muokkaa</Button>
                <IconButton onClick={() => submit(campaign.id)}><DeleteIcon color='warning' /></IconButton>
            </CardActions>
        </Card>

    );
}
export default CampaignCard;