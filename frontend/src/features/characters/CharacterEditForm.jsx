import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router';
import { Box, Button, Container, InputLabel, Paper, TextField, Typography } from '@mui/material';
import AttachmentIcon from '@mui/icons-material/Attachment';
import CreateIcon from '@mui/icons-material/Create';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { getCharacters, updateCharacter } from './Characters';



function CharacterEditForm() {

  const navigate = useNavigate();
  let { id } = useParams();
  id = Number(id);

  const [characters, setCharacters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [msg, setMsg] = useState('');
  const [character, setValues] = useState({
    name: '',
    description: '',
    imageUrl: ''
  });

  const getAllCharacters = async () => {
    try {
      const response = await getCharacters();
      if (response.status === 200) {
        setCharacters(response.data);
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
  useEffect(() => { getAllCharacters() }, [])

  useEffect(() => {
    if (loading) return;

    if (!characters || characters.length === 0) {
      navigate('/error', {
        state: { errorMsg: 'Listaus ei onnistunut' }
      })
    }

    const foundCharacter = characters.find(
      (c) => c.id === id
    );

    if (!foundCharacter) {
      navigate('/error', {
        state: { errorMsg: 'Listaus ei onnistunut' }
      })
    }

    setValues({
      name: foundCharacter.name ?? '',
      description: foundCharacter.description ?? '',
      imageUrl: foundCharacter.imageUrl ?? null,
    });
  }, [loading, characters, id]);

  const edit = (e) => {
    setValues({
      ...character,
      [e.target.name]: e.target.value
    });
  }

  const changeImage = (e) => {
    setValues({
      ...character,
      imageUrl: e.target.files[0]
    });
  }

  const editCharacter = async () => {
    if (!character.name || !character.description) {
      setMsg('Täytä pakolliset kentät');
      return;
    }
    const formData = new FormData();

    formData.append('name', character.name);
    formData.append('description', character.description);
    formData.append('imageUrl', character.imageUrl);

    try {
      const response = await updateCharacter(id, formData);

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

  let imageName = '';
  if (character.imageUrl !== null) {
    imageName = character.imageUrl.name;
  }

  if (characters.length == 0) {
    return (<Typography>Haetaan</Typography>)
  }

  if (typeof characters === 'undefined' || characters.length === 0) {
    return (<Error errorMsg='Hahmoja ei ole' />)
  }

  return (

    <Container className='maincontent'>
      <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>

        <Typography variant="h3">Muokkaa hahmoa</Typography>

        <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

          <TextField label="Hahmon nimi"
            name='name' value={character.name} onChange={edit} required fullWidth />

          <TextField label="Kuvaus" multiline rows={4}
            name='description' value={character.description} onChange={edit} required fullWidth />

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
            <Button onClick={editCharacter} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >Muokkaa</Button>
            <Button onClick={() => navigate('/characters')} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />}>Takaisin</Button>
          </Box>

        </Box>
        {<Typography>{msg}</Typography>}
      </Box>
    </Container>
  );

}

export default CharacterEditForm;