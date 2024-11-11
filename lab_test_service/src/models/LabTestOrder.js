const mongoose = require('mongoose');

const LabTestOrderSchema = new mongoose.Schema({
    patientId: { type: String, required: true },
    patientFirstName: { type: String, required: true },
    patientLastName: { type: String, required: true },
    testType: {
        type: String,
        required: true,
        enum: ['Blood Test', 'X-Ray', 'MRI', 'Urine Test', 'CT Scan', 'Other']
    },
    status: {
        type: String,
        enum: ['Ordered', 'In Progress', 'Completed', 'Cancelled'],
        default: 'Ordered'
    },
    orderDate: { type: Date, default: Date.now },
    resultDate: { type: Date },
    results: { type: String },
    notes: { type: String },
    physicianId: { type: String, required: true },
    physicianName: { type: String, required: true },
});

module.exports = mongoose.model('LabTestOrder', LabTestOrderSchema);
