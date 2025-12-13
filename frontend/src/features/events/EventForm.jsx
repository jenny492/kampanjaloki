import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import CreateIcon from '@mui/icons-material/Create';
import { Box, Button, Container, TextField, Typography } from '@mui/material';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import { createEvent } from './Events';


function EventForm() {

    let { id } = useParams();
    id = Number(id);

    const navigate = useNavigate();

    const [event, setValues] = useState({
        title: '',
        content: '',
    });

    const [msg, setMsg] = useState('');

    const changeEvent = (e) => {
        setValues({
            ...event,
            [e.target.name]: e.target.value
        });
        setMsg('');
    };

    const addEvent = async () => {
        const data = {
            sessionid: id,
            title: event.title,
            content: event.content,
        };

        try {
            const response = await createEvent(data);

            if (response.status === 200) {
                navigate('/campaign/' + id); // ohjataan selain eteenpäin, jos lisäys onnistui
            }
            else {
                // Virheiden käsittely
                navigate('/error', { state: { errorMsg: 'Lisäys ei onnistunut' } });
            }
        } catch (error) {
            navigate('/error', { state: { ErrorMsg: 'Lisäys ei onnistunut' } });
        }


        if (!event.content) {
            setMsg('Täytä pakolliset kentät');
            return;
        }
        setValues({
            title: '',
            content: '',
        });
        setMsg("Tapahtuma lisätty");
    };

    return (
        <Container className='maincontent'>
            <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>

                <Typography variant="h3">Uusi tapahtuma</Typography>

                <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

                    <TextField label="Otsikko" name="title" value={event.title} onChange={changeEvent} fullWidth />

                    <TextField label="Sisältö" multiline rows={4} name="content" value={event.content} onChange={changeEvent} fullWidth required />

                    <Box>
                        <Button onClick={() => addEvent()} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >
                            Lisää
                        </Button>

                        <Button onClick={() => navigate('/campaign/' + id)} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />} >
                            Takaisin
                        </Button>
                    </Box>

                </Box>

                {msg && <Typography>{msg}</Typography>}
            </Box>
        </Container>
    );

}

export default EventForm;
