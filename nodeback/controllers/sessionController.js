const db = require('../connection.cjs');

exports.getAllSessions = (req, res) => {
    db.all('select * from sessions', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
};

exports.getSessionById = (req, res) => {
    let id = req.params.id;

    db.get('select * from sessions where id = ?', [id], (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (typeof (result) == 'undefined') {
            return res.status(404).json({ message: 'Haettua seikkailua ei ole' });
        }

        return res.status(200).json(result);
    });
};

exports.createSession = (req, res) => {
    let session = req.body;

    let now = new Date();
    //session.createdAt = now.toISOString();
    const campaignid = session.campaignid || session.campaign_id || session.id;

    db.run('insert into sessions (campaign_id,title,content,session_date,created_at,updated_at) values (?, ?, ?, ?, ?, ?)',
        [campaignid, session.title, session.content, session.sessionDate, now.toISOString(), session.updatedAt], (error) => {

            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }

            return res.status(200).json({ count: 1 });
        });
};

exports.deleteSession = (req, res) => {
    let id = req.params.id;

    db.run('delete from sessions where id = ?', [id], function (error) {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (this.changes === 0) {
            console.log('Ei poistettavaa');
            return res.status(404).json({ message: 'Ei poistettavaa seikkailua' });
        }

        return res.status(200).json({ count: this.changes });
    });
};