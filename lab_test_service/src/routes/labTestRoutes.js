const express = require('express');
const {
    createLabTestOrder,
    getLabTestOrders,
    getLabTestOrderById,
    updateLabTestOrder,
    deleteLabTestOrder
} = require('../controllers/labTestController');
const router = express.Router();

router.post('/', createLabTestOrder);
router.get('/', getLabTestOrders);
router.get('/:id', getLabTestOrderById);
router.put('/:id', updateLabTestOrder);
router.delete('/:id', deleteLabTestOrder);

module.exports = router;
