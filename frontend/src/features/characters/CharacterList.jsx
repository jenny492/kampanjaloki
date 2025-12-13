import AddIcon from '@mui/icons-material/Add';
import { Box, Button, Grid, Typography } from '@mui/material';
import { Link } from 'react-router';
import CharacterCard from './CharacterCard';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import { getCharacters } from '../characters/Characters';

function CharacterList() {

    const navigate = useNavigate(); 

    const [characters, setCharacters] = useState([]);

    const getAllCharacters = async () => { 
        try {
            const response = await getCharacters();
            if (response.status === 200) {
                setCharacters(response.data);
            } else {
                navigate('/virhe', {
                    state: { viesti: 'Listaus ei onnistunut' }
                })
            }
        } catch (error) {
            navigate('/virhe', {
                state: { viesti: 'Listaus ei onnistunut' }
            })
        }
    }
    useEffect(() => { getAllCharacters() }, [])

    if (characters.length == 0) {
        return (<Typography>Haetaan</Typography>)
    }
    
    return (
        <Box className="maincontent">
            <Typography variant="h3">Hahmot</Typography>

            <Button component={Link} to={"/characterform"}
                variant='contained'
                startIcon={<AddIcon />}
                sx={{ marginTop: 4, marginBottom: 4 }} >
                Luo uusi hahmo</Button>

            <Grid container spacing={2} sx={{ marginTop: 2, marginBottom: 2, justifyContent: 'center' }}>
                {
                    characters.map(character => {
                        return (
                            <Grid item key={character.id}>
                                <CharacterCard character={character} />
                            </Grid>
                        )
                    })   
                }
                
        </Grid>
        </Box >
    );
}

export default CharacterList;