router.get('/campaigns/all', (req, res) => {
    db.all('select * from campaigns', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
});

router.get('/campaigns/one/:id', (req, res) => {
    let id = req.params.id;

    db.get('select * from campaigns where id = ?', [id], (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        // Jos haku ei tuottanut yhtään riviä
        if (typeof (result) == 'undefined') {
            return res.status(404).json({ message: 'Haettua kampanjaa ei ole' });
        }

        return res.status(200).json(result);
    });
});

router.get('/campaigns/kuvat', (req, res) => {
    db.all('select kuva  from campaigns where kuva IS NOT NULL', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
});

router.delete('/campaigns/delete/:id', (req, res) => {
    let id = req.params.id;

    // Huomaa, että ei nuolinotaatiofunktioina kuten muissa kohdassa
    db.run('delete from campaigns where id = ?', [id], function (error) {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (this.changes === 0) {
            console.log('Ei poistettavaa');
            return res.status(404).json({ message: 'Ei poistettavaa campaignsa' });
        }

        return res.status(200).json({ count: this.changes });
    });
});

// Jos ei ole multeria käytössä, niin jotain pitää tehdä eri tavalla
const multer = require('multer');

const storage = multer.diskStorage({
    destination: (req, file, callback) => {
        callback(null, './images'); // Mihin kansioon ladataan
    },
    filename: (req, file, callback) => {
        callback(null, file.originalname);  // Millä tiedostonimellä
    }
});

const upload = multer({ storage: storage })
// Postin voi tehdä postmanin kautta form-datalla
router.post('/campaigns/add', upload.single('kuva'), (req, res) => {
    let campaigns = req.body;

    let kuvaNimi = null;
    if (req.file) {
        kuvaNimi = req.file.originalname;
    }

    db.run('insert into campaigns (otsikko,paiva,paikka,saa,kuva,kuvaus, arvio) values (?, ?, ?, ?, ?, ?, ?)',
        [campaigns.otsikko, campaigns.paiva, campaigns.paikka, campaigns.saa, kuvaNimi, campaigns.kuvaus, campaigns.arvio], (error) => {

            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }

            return res.status(200).json({ count: 1 });
        });
});

router.get('/download/:nimi', (req, res) => {
    let file = './images/' + req.params.nimi;
    res.download(file);
});