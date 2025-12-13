const express = require('express');
const router = express.Router();
const db = require('../connection.cjs');
const { getAllCharacters, getCharacterById, createCharacter, updateCharacter, deleteCharacter } = require('../controllers/characterController.js');
const multer = require('multer');

const storage = multer.diskStorage({
    destination: (req, file, callback) => {
        callback(null, '../frontend/public/characters'); // Mihin kansioon ladataan
    },
    filename: (req, file, callback) => {
        callback(null, file.originalname);  // Millä tiedostonimellä
    }
});

const upload = multer({ storage: storage })

router.get('/', getAllCharacters);
router.get('/:id', getCharacterById);
router.post('/', upload.single('imageUrl'), createCharacter);
router.put('/:id', upload.single('imageUrl'), updateCharacter);
router.delete('/:id', deleteCharacter);

module.exports = router;