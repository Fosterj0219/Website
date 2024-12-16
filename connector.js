const express = require('express');
const mysql = require('mysql');
const cors = require('cors');

const app = express();
const port = 3000;

// Enable CORS
app.use(cors());
app.use(express.json());

// MySQL Database Connection
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root', // Replace with your MySQL username
  password: '', // Replace with your MySQL password
  database: 'midtermproj', // Your database name
});

// Connect to the database
db.connect((err) => {
  if (err) {
    console.error('Error connecting to the database:', err);
  } else {
    console.log('Connected to the database.');
  }
});

// Route to fetch books based on a search query
app.get('/books', (req, res) => {
  const searchQuery = req.query.q || '';
  const sql = `SELECT * FROM longlista WHERE title LIKE ?`;
  const values = [`%${searchQuery}%`];

  db.query(sql, values, (err, results) => {
    if (err) {
      res.status(500).json({ error: 'Error fetching books' });
    } else {
      res.json(results);
    }
  });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
