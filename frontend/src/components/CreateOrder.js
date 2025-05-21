import React, { useState } from 'react';
import {
  Container,
  Paper,
  Typography,
  TextField,
  Button,
  Box,
  IconButton,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';
import { orderService } from '../services/api';

function CreateOrder() {
  const [items, setItems] = useState([]);
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState('');
  const navigate = useNavigate();

  const handleAddItem = () => {
    if (productId && quantity) {
      setItems([
        ...items,
        {
          productId,
          quantity: parseInt(quantity, 10),
        },
      ]);
      setProductId('');
      setQuantity('');
    }
  };

  const handleRemoveItem = (index) => {
    setItems(items.filter((_, i) => i !== index));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const order = await orderService.createOrder(items);
      navigate(`/orders/${order.id}`);
    } catch (error) {
      console.error('Error creating order:', error);
    }
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4 }}>
      <Paper sx={{ p: 4 }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Create New Order
        </Typography>

        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Box sx={{ display: 'flex', gap: 2, mb: 3 }}>
            <TextField
              label="Product ID"
              value={productId}
              onChange={(e) => setProductId(e.target.value)}
              required
            />
            <TextField
              label="Quantity"
              type="number"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
              required
              inputProps={{ min: 1 }}
            />
            <Button
              variant="contained"
              onClick={handleAddItem}
              disabled={!productId || !quantity}
            >
              Add Item
            </Button>
          </Box>

          <List>
            {items.map((item, index) => (
              <ListItem key={index}>
                <ListItemText
                  primary={`Product ID: ${item.productId}`}
                  secondary={`Quantity: ${item.quantity}`}
                />
                <ListItemSecondaryAction>
                  <IconButton
                    edge="end"
                    aria-label="delete"
                    onClick={() => handleRemoveItem(index)}
                  >
                    <DeleteIcon />
                  </IconButton>
                </ListItemSecondaryAction>
              </ListItem>
            ))}
          </List>

          <Box sx={{ mt: 3, display: 'flex', justifyContent: 'flex-end', gap: 2 }}>
            <Button
              variant="outlined"
              onClick={() => navigate('/')}
            >
              Cancel
            </Button>
            <Button
              type="submit"
              variant="contained"
              disabled={items.length === 0}
            >
              Create Order
            </Button>
          </Box>
        </Box>
      </Paper>
    </Container>
  );
}

export default CreateOrder; 