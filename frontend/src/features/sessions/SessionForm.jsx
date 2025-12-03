import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import CreateIcon from '@mui/icons-material/Create';
import { Box, Button, Container, TextField, Typography } from '@mui/material';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router';

import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import fi from 'date-fns/locale/fi';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';

function SessionForm() {

    let { id } = useParams();
    id = Number(id);

    const navigate = useNavigate();

    const [session, setValues] = useState({
        title: '',
        content: '',
        session_date: ''
    });

    const [msg, setMsg] = useState('');

    const changeSession = (e) => {
        setValues(
            {
                ...session,
                [e.target.name]: e.target.value
            }
        );
        setMsg('');
    }

    const addSession = () => {
        if (!session.content) {
            setMsg('Täytä pakolliset kentät');
            return;
        }
        setValues({
            title: '',
            content: '',
            session_date: ''
        });
        setMsg("Seikkailu lisätty")
    }

    const changeDate = (e) => {
        setValues({
            ...session,
            session_date: e
        });

        setMsg('');
    };

    return (

        <Container className='maincontent'>
            <Box sx={{ maxWidth: 600, margin: "0 auto", padding: 3 }}>

                <Typography variant="h3">Uusi seikkailu</Typography>

                <Box component='form' autoComplete='off' sx={{ '& .MuiTextField-root': { marginBottom: 4 } }} >

                    <TextField label="Otsikko"
                        name='title' value={session.title} onChange={(e) => changeSession(e)} fullWidth />

                    <TextField label="Sisältö" multiline rows={4}
                        name='content' value={session.content} onChange={(e) => changeSession(e)} fullWidth required />


                    <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={fi}>
                        <DesktopDatePicker label='Seikkailun päivämäärä' name='session_date' value={session.session_date} onChange={changeDate} sx={{ width: '100%' }} />
                    </LocalizationProvider>

                    <Box>
                        <Button onClick={() => addSession()} variant='contained' sx={{ marginRight: 3 }} startIcon={<CreateIcon />} >Lisää</Button>
                        <Button onClick={() => navigate('/campaign/' + id)} variant='contained' sx={{ marginRight: 3 }} startIcon={<ArrowBackIcon />}>Takaisin</Button>
                    </Box>

                </Box>
                {<Typography>{msg}</Typography>}
            </Box>
        </Container>
    );



}

export default SessionForm;