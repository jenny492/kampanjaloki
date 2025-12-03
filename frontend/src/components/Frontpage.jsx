import { Box, Typography } from '@mui/material';

function Frontpage() {

    return (
       <Box className="maincontent">
        
        <img src="dndrulebook.svg" className="logo"></img>
        <Typography variant="h3" gutterBottom>Kampanjaloki</Typography>
        <Typography>Roolipelin muistiinpanot, hahmot, kartat ja tavarat kaikki yhdessä paikassa. Kampanjalokin avulla kaikki kampanjan tiedot pysyvät tallessa, jotta voit keskittyä siihen tärkeimpään: pelaamiseen.</Typography>


      </Box>


    )
}

export default Frontpage;