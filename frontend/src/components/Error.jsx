import { Box, Typography } from '@mui/material';

function Error({ ErrorMsg }) {

    const state = location.state || {};
    const message = state.message || state.viesti || state.errorMsg || ErrorMsg;

    return (
        <Box className="maincontent">
            <Typography>{message}</Typography>
        </Box>
    );
}

export default Error;