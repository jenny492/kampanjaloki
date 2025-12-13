const db = require('../connection.cjs');

exports.getAllEvents = (req, res) => {
    db.all('select * from events', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
};

exports.getEventById = (req, res) => {
    let id = req.params.id;

    db.get('select * from events where id = ?', [id], (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (typeof (result) == 'undefined') {
            return res.status(404).json({ message: 'Haettua tapahtumaa ei ole' });
        }

        return res.status(200).json(result);
    });
};

exports.createEvent = (req, res) => {
    let event = req.body;

    db.run('insert into events (session_id,title,order_index,content,details) values (?, ?, ?, ?, ?)',
        [event.sessionid, event.title, event.orderIndex, event.content, event.details], (error) => {

            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }

            return res.status(200).json({ count: 1 });
        });
};

exports.deleteEvent = (req, res) => {
    let id = req.params.id;

    db.run('delete from events where id = ?', [id], function (error) {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (this.changes === 0) {
            console.log('Ei poistettavaa');
            return res.status(404).json({ message: 'Ei poistettavaa tapahtumaa' });
        }

        return res.status(200).json({ count: this.changes });
    });
};