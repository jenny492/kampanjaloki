const express = require('express');
const router = express.Router();
const db = require('../connection.cjs');
const { getAllEvents, getEventById, createEvent, deleteEvent } = require('../controllers/eventController.js');

router.get('/', getAllEvents);
router.get('/:id', getEventById);
router.post('/', createEvent);
// router.put('/:id', updateEvent);
router.delete('/:id', deleteEvent);

module.exports = router;