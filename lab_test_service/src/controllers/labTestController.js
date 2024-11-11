const LabTestOrder = require('../models/LabTestOrder');

exports.createLabTestOrder = async (req, res) => {
    try {
        const labTestOrder = new LabTestOrder(req.body);
        const savedOrder = await labTestOrder.save();
        res.status(201).json(savedOrder);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

exports.getLabTestOrders = async (req, res) => {
    try {
        const orders = await LabTestOrder.find();
        res.json(orders);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.getLabTestOrderById = async (req, res) => {
    try {
        const order = await LabTestOrder.findById(req.params.id);
        if (!order) return res.status(404).json({ message: 'Order not found' });
        res.json(order);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

exports.updateLabTestOrder = async (req, res) => {
    try {
        const updatedOrder = await LabTestOrder.findByIdAndUpdate(
            req.params.id,
            req.body,
            { new: true }
        );
        if (!updatedOrder) return res.status(404).json({ message: 'Order not found' });
        res.json(updatedOrder);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
};

exports.deleteLabTestOrder = async (req, res) => {
    try {
        const deletedOrder = await LabTestOrder.findByIdAndDelete(req.params.id);
        if (!deletedOrder) return res.status(404).json({ message: 'Order not found' });
        res.json({ message: 'Order deleted successfully' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};
