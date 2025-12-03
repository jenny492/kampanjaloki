import { Box, Typography } from '@mui/material';

function Error({ ErrorMsg }) {

    const { message = ErrorMsg } = location.state || {};

    return (
        <Box className="maincontent">
            <Typography>{message}</Typography>
        </Box>
    );
}

export default Error;