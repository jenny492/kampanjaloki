const express = require('express');
const router = express.Router();
const { getAllCampaigns, getCampaignById, createCampaign, updateCampaign, deleteCampaign } = require('../controllers/campaignController.js');
const multer = require('multer');

const storage = multer.diskStorage({
    destination: (req, file, callback) => {
        callback(null, '../frontend/public/campaigns'); // Mihin kansioon ladataan
    },
    filename: (req, file, callback) => {
        callback(null, file.originalname);  // Millä tiedostonimellä
    }
});

const upload = multer({ storage: storage })

router.get('/', getAllCampaigns);
router.get('/:id', getCampaignById);
router.post('/', upload.single('imageUrl'), createCampaign);
router.put('/:id', upload.single('imageUrl'), updateCampaign);
router.delete('/:id', deleteCampaign);

module.exports = router;


