const db = require('../connection.cjs');

exports.getAllCampaigns = (req, res) => {
    db.all('select * from campaigns', (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        return res.status(200).json(result);
    });
};

exports.getCampaignById = (req, res) => {
    let id = req.params.id;

    db.get('select * from campaigns where id = ?', [id], (error, result) => {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (typeof (result) == 'undefined') {
            return res.status(404).json({ message: 'Haettua kampanjaa ei ole' });
        }

        return res.status(200).json(result);
    });
};

exports.createCampaign = (req, res) => {
    let campaign = req.body;

    let imageUrl = null;
    if (req.file) {
        imageUrl = '/campaigns/' + req.file.filename;
    } else if (campaign.imageUrl) {
        imageUrl = campaign.imageUrl;
    } else {
        imageUrl = '/campaigns/default.jpg';
    }

    let now = new Date();

    campaign.createdAt = now.toISOString();

    db.run('insert into campaigns (name,description,image_url,created_at,owner_id) values (?, ?, ?, ?, ?)',
        [campaign.name, campaign.description, imageUrl, campaign.createdAt, campaign.ownerid], (error) => {

            if (error) {
                console.log(error.message);
                return res.status(400).json({ message: error.message });
            }

            return res.status(200).json({ count: 1 });
        });
};

exports.updateCampaign = (req, res) => {
    const id = req.params.id;
    const campaign = req.body;
    const ownerid = campaign.owner_id || campaign.ownerId || campaign.ownerid;

    let imageUrl = null;
    if (req.file) {
        imageUrl = '/campaigns/' + req.file.filename;
    } else if (campaign.imageUrl === null) {
        imageUrl = '/campaigns/default.jpg';
    }

    db.run('update campaigns set name = ?, description = ?, image_url = ?, created_at = ?, owner_id = ? where id = ?',
        [campaign.name, campaign.description, imageUrl, campaign.createdAt, ownerid, id], function (error) {
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

exports.deleteCampaign = (req, res) => {
    let id = req.params.id;

    db.run('delete from campaigns where id = ?', [id], function (error) {
        if (error) {
            console.log(error.message);
            return res.status(400).json({ message: error.message });
        }

        if (this.changes === 0) {
            console.log('Ei poistettavaa');
            return res.status(404).json({ message: 'Ei poistettavaa campaignia' });
        }

        return res.status(200).json({ count: this.changes });
    });
};