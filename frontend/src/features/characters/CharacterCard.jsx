import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { Card, CardActions, CardContent, CardHeader, CardMedia, IconButton, Typography } from '@mui/material';
import { Link } from 'react-router';

function CharacterCard({ character }) {

    return (
        <Card sx={{ width: 250 }}>
            
            {character.imageUrl ?
                <CardMedia sx={{}} component='img' image={character.imageUrl} />
                :
                <CardMedia sx={{}} component='img' image={'/characters/default.jpg'} />
            }
            <CardContent>
                <Typography>{character.name}</Typography>

                <Typography variant='body2'>{character.description}</Typography>

            </CardContent>

{/*             <CardActions sx={{ display: 'flex', justifyContent: 'center' }}>
                <IconButton component={Link} to={'/edit/' + character.characterid}><EditIcon color='primary' /></IconButton>
                <IconButton><DeleteIcon color='secondary' /></IconButton>
            </CardActions> */}
        </Card>
    );
}

export default CharacterCard;