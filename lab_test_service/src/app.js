const express = require('express');
const connectDB = require('./config/db');
const labTestRoutes = require('./routes/labTestRoutes');
const dotenv = require('dotenv');

dotenv.config();
connectDB();

const app = express();
app.use(express.json());
app.use('/api/lab-tests', labTestRoutes);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
