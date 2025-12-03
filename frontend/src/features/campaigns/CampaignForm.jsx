import { useState } from 'react';
import { useNavigate } from 'react-router';
import { Box, Button, Container, InputLabel, Paper, TextField, Typography } from '@mui/material';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CreateIcon from '@mui/icons-material/Create';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';


function CampaignForm() {

  const navigate = useNavigate();

  const [campaign, setValues] = useState({
    name: '',
    description: '',
    imageUrl: '',
    createdAt: ''
  });

  const [msg, setMsg] = useState('');

  const changeCampaign = (e) => {
    setValues(
      {
        ...campaign,
        [e.target.name]: e.target.value
      }
    );
    setMsg('');
  }

  const addCampaign = () => {
    if (!campaign.name || !campaign.description) {
      setMsg('Täytä pakolliset kentät');
      return;
    }
    setValues({
      name: '',
      description: '',
      imageUrl: ''
    });
    setMsg("Kampanja lisätty")
  }

  const changeImage = (e) => {
    setValues({
      ...campaign,
      imageUrl: e.target.files[0]
    });

    setMsg('');
  }

  let imageName = '';
  if (campaign.imageUrl !== null) {
    imageName = campaign.imageUrl.name;
  }

  return (

    <Container className='maincontent'>

      <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>
        <Typography variant="h3">Uusi kampanja</Typography>

        <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

          <TextField label="Kampanjan nimi"
            name='name' value={campaign.name} onChange={(e) => changeCampaign(e)} required fullWidth />

          <TextField label="Kuvaus" multiline rows={4}
            name='description' value={campaign.description} onChange={(e) => changeCampaign(e)} required fullWidth />

          <input accept='image/*' name='image' id='image' type='file'
            onChange={(e) => changeImage(e)} hidden />

          <InputLabel htmlFor='image'>
            <Typography sx={{ display: 'inline' }}>Kuva</Typography>
            <Button component='span'>
              <AttachmentIcon />
            </Button>
            <Typography sx={{ display: 'inline' }}>{imageName}</Typography>
          </InputLabel>

          <Box>
            <Button onClick={() => addCampaign()} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >Lisää</Button>
            <Button onClick={() => navigate('/campaigns')} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />}>Takaisin</Button>
          </Box>

        </Box>
        {<Typography>{msg}</Typography>}
      </Box>
    </Container>
  );

}

export default CampaignForm;