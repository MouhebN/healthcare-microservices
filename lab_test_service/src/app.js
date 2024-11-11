const express = require('express');
const Eureka = require('eureka-js-client').Eureka;
const connectDB = require('./config/db');
const labTestRoutes = require('./routes/labTestRoutes');
const dotenv = require('dotenv');

dotenv.config();
connectDB();

const app = express();
app.use(express.json());
app.use('/api/lab-tests', labTestRoutes);

const client = new Eureka({
    // application instance information
    instance: {
        app: 'lab-test-service',  // Service name
        hostName: 'localhost',    // Hostname of the service
        ipAddr: '127.0.0.1',      // IP address of the service
        port: {
            '$': 3000,               // Port your service is running on
            '@enabled': true         // Enable this port
        },
        vipAddress: 'lab-test-service', // Virtual hostname for the service
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo', // Class for the data center info
            name: 'MyOwn'  // Data center name (you can use "MyOwn" or any other valid name)
        },
        healthCheckUrl: '/health',  // Health check URL
        homePageUrl: 'http://127.0.0.1:3000', // Home page URL
        statusPageUrl: 'http://127.0.0.1:3000/status', // Status page URL
        secureHealthCheckUrl: ''  // Optional: if using HTTPS for health check
    },
    eureka: {
        // Eureka server host / port
        host: '127.0.0.1',  // Eureka server address
        port: 8761          // Eureka server port (default is 8761)
    }
});

// Start Eureka client and register the service
client.start(error => {
    if (error) {
        console.error('Error connecting to Eureka:', error);
    } else {
        console.log('Connected to Eureka');
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
