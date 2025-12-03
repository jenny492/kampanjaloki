// Ensin asennukset
const express = require('express');
const app = express();

let helmet = require('helmet');
app.use(helmet({ crossOriginResourcePolicy: false }))

app.use(express.urlencoded({ limit: '5mb', extended: true }));

// Corsin avulla pystytään ottamaan palvelimeen yhteyttä myös ulkopuolelta
const cors = require('cors');
app.use(cors());

app.use(express.json());

// Otetaan sql-lite käyttöön


// Reititykset
const campaignsRouter = require('./routes/campaigns.cjs');
const charactersRouter = require('./routes/characters.cjs');
const eventsRouter = require('./routes/events.cjs');
const sessionsRouter = require('./routes/sessions.cjs');
app.use('/campaigns', campaignsRouter);
app.use('/characters', charactersRouter);
app.use('/events', eventsRouter);
app.use('/sessions', sessionsRouter);

app.listen(8080, () => {
    console.log('Node toimii localhost:8080');
});

app.get('/', (req, res) => {
    return res.status(200).json({ message: 'Toimii' });
});

app.get('/*splat', (req, res) => {
    return res.status(404).json({ message: 'Ei pyydettyä palvelua' });
});
