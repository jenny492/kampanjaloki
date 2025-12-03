const sqlite3 = require('sqlite3');
const db = new sqlite3.Database('kampanjaloki.db', (error) => {
    if (error) {
        console.log(error.message);
        return ({ message: 'Kantaa ei voida avata ' + error.message });
    }
});

module.exports = db;