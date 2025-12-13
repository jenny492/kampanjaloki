import { useState } from 'react';
import { useNavigate } from 'react-router';
import { Box, Button, Container, InputLabel, Paper, TextField, Typography } from '@mui/material';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CreateIcon from '@mui/icons-material/Create';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { createCharacter } from './Characters';


function CharacterForm() {

  const navigate = useNavigate();

  const [character, setValues] = useState({
    name: '',
    description: '',
    imageUrl: '',
    createdAt: ''
  });

  const [msg, setMsg] = useState('');

  const changeCharacter = (e) => {
    setValues(
      {
        ...character,
        [e.target.name]: e.target.value
      }
    );
    setMsg('');
  }

  const addCharacter = async () => {
    if (!character.name || !character.description) {
      setMsg('Täytä pakolliset kentät');
      return;
    }
    const formData = new FormData();

    formData.append('name', character.name);
    formData.append('description', character.description);
    formData.append('imageUrl', character.imageUrl);

    try {
      const response = await createCharacter(formData);

      if (response.status === 200) {
        navigate('/characters/');
      }
      else {
        navigate('/error', { state: { ErrorMsg: 'Lisäys ei onnistunut' } });
      }
    } catch (error) {
      navigate('/error', { state: { ErrorMsg: 'Lisäys ei onnistunut' } });
    }
  }

  const changeImage = (e) => {
    setValues({
      ...character,
      imageUrl: e.target.files[0]
    });

    setMsg('');
  }

  let imageName = '';
  if (character.imageUrl !== null) {
    imageName = character.imageUrl.name;
  }

  return (

    <Container className='maincontent'>

      <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>
        <Typography variant="h3">Uusi hahmo</Typography>

        <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

          <TextField label="Hahmon nimi"
            name='name' value={character.name} onChange={(e) => changeCharacter(e)} required fullWidth />

          <TextField label="Kuvaus" multiline rows={4}
            name='description' value={character.description} onChange={(e) => changeCharacter(e)} required fullWidth />

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
            <Button onClick={() => addCharacter()} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >Lisää</Button>
            <Button onClick={() => navigate('/characters')} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />}>Takaisin</Button>
          </Box>

        </Box>
        {<Typography>{msg}</Typography>}
      </Box>
    </Container>
  );

}

export default CharacterForm;