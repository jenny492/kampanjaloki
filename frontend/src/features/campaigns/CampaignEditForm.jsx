import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import { Box, Button, Container, InputLabel, TextField, Typography } from '@mui/material';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CreateIcon from '@mui/icons-material/Create';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { getCampaigns, updateCampaign } from './Campaigns';



function CampaignEditForm() {

  const navigate = useNavigate();
  let { id } = useParams();
  id = Number(id);

  const [campaigns, setCampaigns] = useState([]);
  const [loading, setLoading] = useState(true);
  const [msg, setMsg] = useState('');
  const [campaign, setValues] = useState({
    name: '',
    description: '',
    imageUrl: ''
  });

  const getAllCampaigns = async () => {
    try {
      const response = await getCampaigns();
      if (response.status === 200) {
        setCampaigns(response.data);
      } else {
        navigate('/error', {
          state: { errorMsg: 'Listaus ei onnistunut' }
        })
      }
    } catch (error) {
      navigate('/error', {
        state: { errorMsg: 'Listaus ei onnistunut' }
      })
    } finally {
      setLoading(false);
    }
  }
  useEffect(() => { getAllCampaigns() }, [])

  useEffect(() => {
    if (loading) return;

    if (!campaigns || campaigns.length === 0) {
      navigate('/error', {
        state: { errorMsg: 'Listaus ei onnistunut' }
      })
    }

    const foundCampaign = campaigns.find(
      (c) => c.id === id
    );

    if (!foundCampaign) {
      navigate('/error', {
        state: { errorMsg: 'Listaus ei onnistunut' }
      })
    }

    setValues({
      name: foundCampaign.name ?? '',
      description: foundCampaign.description ?? '',
      imageUrl: foundCampaign.imageUrl ?? null,
    });
  }, [loading, campaigns, id]);

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

  const editCampaign = async () => {
    if (!campaign.name || !campaign.description) {
      setMsg('Täytä pakolliset kentät');
      return;
    }
    const formData = new FormData();

    formData.append('name', campaign.name);
    formData.append('description', campaign.description);
    formData.append('imageUrl', campaign.imageUrl);

    try {
      const response = await updateCampaign(id, formData);

      if (response.status === 200) {
        navigate('/campaigns/');
      }
      else {
        navigate('/error', { state: { ErrorMsg: 'Lisäys ei onnistunut' } });
      }
    } catch (error) {
      navigate('/error', { state: { ErrorMsg: 'Lisäys ei onnistunut' } });
    }
  }

  let imageName = '';
  if (campaign.imageUrl !== null) {
    imageName = campaign.imageUrl.name;
  }

  if (campaigns.length == 0) {
    return (<Typography>Haetaan</Typography>)
  }

  if (typeof campaigns === 'undefined' || campaigns.length === 0) {
    return (<Error errorMsg='Kampanjoita ei ole' />)
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