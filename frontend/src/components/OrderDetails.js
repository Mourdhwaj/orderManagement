import React, { useState, useEffect } from 'react';
import {
  Container,
  Paper,
  Typography,
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Alert,
} from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import { orderService } from '../services/api';

function OrderDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [order, setOrder] = useState(null);
  const [error, setError] = useState(null);
  const [newStatus, setNewStatus] = useState('');

  useEffect(() => {
    fetchOrder();
  }, [id]);

  const fetchOrder = async () => {
    try {
      const data = await orderService.getOrderById(id);
      setOrder(data);
      setNewStatus(data.status);
    } catch (error) {
      setError('Failed to fetch order details');
      console.error('Error fetching order:', error);
    }
  };

  const handleStatusChange = async () => {
    try {
      const updatedOrder = await orderService.updateOrderStatus(id, newStatus);
      setOrder(updatedOrder);
    } catch (error) {
      setError('Failed to update order status');
      console.error('Error updating order:', error);
    }
  };

  const handleCancelOrder = async () => {
    try {
      const updatedOrder = await orderService.cancelOrder(id);
      setOrder(updatedOrder);
    } catch (error) {
      setError('Failed to cancel order');
      console.error('Error cancelling order:', error);
    }
  };

  if (!order) {
    return (
      <Container maxWidth="md" sx={{ mt: 4 }}>
        <Typography>Loading...</Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="md" sx={{ mt: 4 }}>
      <Paper sx={{ p: 4 }}>
        {error && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {error}
          </Alert>
        )}

        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 4 }}>
          <Typography variant="h4" component="h1">
            Order Details
          </Typography>
          <Button
            variant="outlined"
            onClick={() => navigate('/')}
          >
            Back to Orders
          </Button>
        </Box>

        <Box sx={{ mb: 4 }}>
          <Typography variant="subtitle1" gutterBottom>
            Order ID: {order.id}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Status: {order.status}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Created At: {new Date(order.createdAt).toLocaleString()}
          </Typography>
        </Box>

        <Box sx={{ mb: 4 }}>
          <Typography variant="h6" gutterBottom>
            Update Status
          </Typography>
          <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>
            <FormControl sx={{ minWidth: 200 }}>
              <InputLabel>New Status</InputLabel>
              <Select
                value={newStatus}
                label="New Status"
                onChange={(e) => setNewStatus(e.target.value)}
              >
                <MenuItem value="PENDING">Pending</MenuItem>
                <MenuItem value="PROCESSING">Processing</MenuItem>
                <MenuItem value="COMPLETED">Completed</MenuItem>
              </Select>
            </FormControl>
            <Button
              variant="contained"
              onClick={handleStatusChange}
              disabled={newStatus === order.status}
            >
              Update Status
            </Button>
          </Box>
        </Box>

        {order.status === 'PENDING' && (
          <Box sx={{ mb: 4 }}>
            <Button
              variant="contained"
              color="error"
              onClick={handleCancelOrder}
            >
              Cancel Order
            </Button>
          </Box>
        )}

        <Typography variant="h6" gutterBottom>
          Order Items
        </Typography>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Product ID</TableCell>
                <TableCell>Quantity</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {order.items.map((item, index) => (
                <TableRow key={index}>
                  <TableCell>{item.productId}</TableCell>
                  <TableCell>{item.quantity}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
    </Container>
  );
}

export default OrderDetails; 