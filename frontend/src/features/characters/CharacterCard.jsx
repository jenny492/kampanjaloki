import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Fab from '@mui/material/Fab';
import { Card, CardActions, CardContent, CardMedia, Typography } from '@mui/material';
import { Link, useNavigate } from 'react-router';
import { deleteCharacter } from './Characters';
import { confirmAlert } from 'react-confirm-alert';
import Tooltip from '@mui/material/Tooltip';
import 'react-confirm-alert/src/react-confirm-alert.css';

function CharacterCard({ character }) {

    const navigate = useNavigate();

    const handleDelete = async (id) => {
        const res = await deleteCharacter(id);
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
                    onClick: () => handleDelete(character.id)
                },
                {
                    label: "Ei"
                }
            ]
        });
    };

    const imageSrc = character.imageUrl || character.image_url || '/characters/default.jpg';

    return (
        <Card sx={{ width: 250 }}>

            <CardMedia sx={{}} component='img' image={imageSrc} />

            <CardContent>
                <Typography>{character.name}</Typography>

                <Typography variant='body2'>{character.description}</Typography>

            </CardContent>

            <CardActions sx={{ display: 'flex', justifyContent: 'center', gap: 2, mb: 1 }}>
                <Tooltip disableFocusListener disableTouchListener title="Muokkaa">
                <Fab size="small" component={Link} to={'/characteredit/' + character.id}><EditIcon color='black' /></Fab>
                </Tooltip>
                <Tooltip disableFocusListener disableTouchListener title="Poista">
                <Fab size="small" onClick={() => submit(character.id)}><DeleteIcon color='warning' /></Fab>
                </Tooltip>
            </CardActions>
        </Card>
    );
}

export default CharacterCard;