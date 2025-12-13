const express = require('express');
const router = express.Router();
const db = require('../connection.cjs');
const { getAllSessions, getSessionById, createSession, deleteSession } = require('../controllers/sessionController.js');

router.get('/', getAllSessions);
router.get('/:id', getSessionById);
router.post('/', createSession);
// router.put('/:id', updateSession);
router.delete('/:id', deleteSession);

module.exports = router;