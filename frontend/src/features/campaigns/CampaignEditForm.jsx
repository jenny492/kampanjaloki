import { useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import { Box, Button, Container, InputLabel, Paper, TextField, Typography } from '@mui/material';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CreateIcon from '@mui/icons-material/Create';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';


function CampaignEditForm({ campaigns }) {

  if (typeof campaigns === 'undefined' || campaigns.length === 0) {
    return (<Error errorMsg='Kampanjoita ei ole' />)
  }

  let { id } = useParams();
  id = Number(id);
  let foundCampaign = campaigns.find((campaign) => campaign.campaignid === id);

  if (!foundCampaign) {
    return (<Error errorMsg='Kampanjaa ei ole' />)
  }

  const navigate = useNavigate();

  const [campaign, setValues] = useState({
    name: foundCampaign.name,
    description: foundCampaign.description,
    imageUrl: foundCampaign.imageUrl,
    createdAt: foundCampaign.createdAt
  });

  const [msg, setMsg] = useState('');

  const edit = (e) => {
    setValues({
      ...campaign,
      [e.target.name]: e.target.value
    });
  }

  const changeImage = (e) => {
    setValues({
      ...campaign,
      imageUrl: e.target.files[0]
    });
  }

  const editCampaign = () => {
    setMsg('Kampanjaa muokattu');
  }

  let imageName = '';
  if (campaign.imageUrl !== null) {
    imageName = campaign.imageUrl.name;
  }

  return (

    <Container className='maincontent'>
      <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>

        <Typography variant="h3">Muokkaa kampanjaa</Typography>

        <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

          <TextField label="Kampanjan nimi"
            name='name' value={campaign.name} onChange={edit} required fullWidth />

          <TextField label="Kuvaus" multiline rows={4}
            name='description' value={campaign.description} onChange={edit} required fullWidth />

          <input accept='image/*' name='image' id='image' type='file'
            onChange={changeImage} hidden />

          <InputLabel htmlFor='image'>
            <Typography sx={{ display: 'inline' }}>Kuva</Typography>
            <Button component='span'>
              <AttachmentIcon />
            </Button>
            <Typography sx={{ display: 'inline' }}>{imageName}</Typography>
          </InputLabel>

          <Box>
            <Button onClick={editCampaign} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >Muokkaa</Button>
            <Button onClick={() => navigate('/campaigns')} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />}>Takaisin</Button>
          </Box>

        </Box>
        {<Typography>{msg}</Typography>}
      </Box>
    </Container>
  );

}

export default CampaignEditForm;