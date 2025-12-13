const db = require('../connection.cjs');

exports.getAllCharacters = (req, res) => {
    db.all('select * from characters', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
};

exports.getCharacterById = (req, res) => {
    let id = req.params.id;

    db.get('select * from characters where id = ?', [id], (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (typeof (result) == 'undefined') {
            return res.status(404).json({ message: 'Haettua hahmoa ei ole' });
        }

        return res.status(200).json(result);
    });
};

exports.createCharacter = (req, res) => {
    let character = req.body;

    let imageUrl = null;
    if (req.file) {
        imageUrl = '/characters/' + req.file.filename;
    } else if (character.imageUrl) {
        imageUrl = character.imageUrl;
    } else {
        imageUrl = '/characters/default.jpg';
    }

    db.run('insert into characters (name,description,image_url,link,owner_id) values (?, ?, ?, ?, ?)',
        [character.name, character.description, imageUrl, character.link, character.ownerid], (error) => {

            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }

            return res.status(200).json({ count: 1 });
        });
};

exports.updateCharacter = (req, res) => {
    const id = req.params.id;
    const character = req.body;
    const ownerid = character.owner_id || character.ownerId || character.ownerid;

    let imageUrl = null;
    if (req.file) {
        imageUrl = '/characters/' + req.file.filename;
    } else if (character.imageUrl === null) {
        imageUrl = '/characters/default.jpg';
    }

    db.run('update characters set name = ?, description = ?, image_url = ?, link = ?, owner_id = ? where id = ?',
        [character.name, character.description, imageUrl, character.link, ownerid, id], function (error) {
            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }
            if (this.changes === 0) {
                console.log('Ei päivitettävää');
                return res.status(404).json({ message: 'Ei päivitettävää kampanjaa' });
            }
            return res.status(200).json({ count: this.changes });
        });
};

exports.deleteCharacter = (req, res) => {
    let id = req.params.id;

    db.run('delete from characters where id = ?', [id], function (error) {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (this.changes === 0) {
            console.log('Ei poistettavaa');
            return res.status(404).json({ message: 'Ei poistettavaa hahmoa' });
        }

        return res.status(200).json({ count: this.changes });
    });
};