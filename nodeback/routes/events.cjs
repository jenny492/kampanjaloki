const express = require('express');
const router = express.Router();
const db = require('../connection.cjs');


router.get('/all', (req, res) => {
  db.all('SELECT * FROM events', (err, rows) => {
    if (err) return res.status(500).json({ message: err.message });
    res.json(rows);
  });
});

// Lisää muita reittejä tänne...

// TÄRKEÄ!!!
module.exports = router;