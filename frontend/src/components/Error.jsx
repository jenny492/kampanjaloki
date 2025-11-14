import { Box, Typography } from '@mui/material';
import { useLocation } from 'react-router';

// Komponentille voi viedä virheviestin tai staten
// const Viesti täytetään joko viestillä, joka on välitetty komponentille propsina tai statella navigoitumisen mukana

function Error({ virheviesti }) {
    const location = useLocation();
    const { viesti = virheviesti } = location.state || {};

    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            justifyContent: 'center',
            alignItems: 'center',
            mt: 1
        }}>
            <Typography>{viesti}</Typography>
        </Box>
    );
}

export default Error;