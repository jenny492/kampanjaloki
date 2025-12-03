import AddIcon from '@mui/icons-material/Add';
import { Box, Button, Grid, Typography } from '@mui/material';
import { Link } from 'react-router';
import CharacterCard from './CharacterCard';

function CharacterList({ characters }) {
    
    return (
        <Box className="maincontent">
            <Typography variant="h3">Hahmot</Typography>

            <Grid container spacing={2} sx={{ marginTop: 2, marginBottom: 2, justifyContent: 'center' }}>
                {
                    characters.map(character => {
                        return (
                            <Grid key={character.characterid}>
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